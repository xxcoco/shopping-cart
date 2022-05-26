package com.xenia.shoppingcart.service;

import com.xenia.shoppingcart.controller.model.ShoppingCartItemDTO;
import com.xenia.shoppingcart.controller.model.ShoppingCartSummeryDTO;
import com.xenia.shoppingcart.controller.model.ShoppingCartSummeryItem;
import com.xenia.shoppingcart.repositories.InventoryRepository;
import com.xenia.shoppingcart.repositories.ProductRepository;
import com.xenia.shoppingcart.repositories.ShoppingCartRepository;
import com.xenia.shoppingcart.service.model.Inventory;
import com.xenia.shoppingcart.service.model.Product;
import com.xenia.shoppingcart.service.model.ShoppingCartItem;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ShoppingCartService {


    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;


    @Transactional
    public ShoppingCartItemDTO addItemToShoppingCart(ShoppingCartItemDTO requestShoppingCartItemDTO) {
        Optional<Inventory> inventoryProduct = inventoryRepository.findByProductId(requestShoppingCartItemDTO.getProductId());
        return inventoryProduct.map(inventory -> addItemToShoppingCartWhenQuantityIsInStock(requestShoppingCartItemDTO, inventory))
                .orElseGet(() -> addNoItemToShoppingCart(requestShoppingCartItemDTO));
    }

    public ShoppingCartItemDTO addItemToShoppingCartWhenQuantityIsInStock(ShoppingCartItemDTO requestShoppingCartItemDTO, Inventory inventoryProduct) {
        if(inventoryProduct.getQuantityInStock() >= requestShoppingCartItemDTO.getQuantity()) {
            ShoppingCartItem shoppingCartItem = setQuantityWithRespectToMinimumOrder(requestShoppingCartItemDTO, inventoryProduct);
            updateQuantityIfProductAlreadyExistsInShoppingCart(requestShoppingCartItemDTO, shoppingCartItem);
            reduceQuantityInInventory(inventoryProduct, shoppingCartItem);
            return new ShoppingCartItemDTO(shoppingCartRepository.save(shoppingCartItem));
        } else {
            return addNoItemToShoppingCart(requestShoppingCartItemDTO);
        }
    }

    private void updateQuantityIfProductAlreadyExistsInShoppingCart(ShoppingCartItemDTO requestShoppingCartItemDTO,
                                                                                 ShoppingCartItem shoppingCartItem) {
        Optional<ShoppingCartItem> existingShoppingCartItem = shoppingCartRepository.findByShoppingCartIdAndProductId(requestShoppingCartItemDTO.getShoppingCartId(), requestShoppingCartItemDTO.getProductId());
        existingShoppingCartItem.ifPresent(cartItem -> shoppingCartItem.setQuantity(cartItem.getQuantity() + shoppingCartItem.getQuantity()));
    }

    private ShoppingCartItem setQuantityWithRespectToMinimumOrder(ShoppingCartItemDTO requestShoppingCartItemDTO, Inventory productFromInventory) {
        if(productFromInventory.getMinimumOrder() <= requestShoppingCartItemDTO.getQuantity()) {
            return new ShoppingCartItem(requestShoppingCartItemDTO.getShoppingCartId(),
                    requestShoppingCartItemDTO.getProductId(), requestShoppingCartItemDTO.getQuantity());
        } else {
            return new ShoppingCartItem(requestShoppingCartItemDTO.getShoppingCartId(),
                    requestShoppingCartItemDTO.getProductId(), productFromInventory.getMinimumOrder());
        }
    }
    private ShoppingCartItemDTO addNoItemToShoppingCart(ShoppingCartItemDTO requestShoppingCartItemDTO) {
        return new ShoppingCartItemDTO(requestShoppingCartItemDTO.getShoppingCartId(),
                requestShoppingCartItemDTO.getProductId(), 0);
    }

    private void reduceQuantityInInventory(Inventory inventoryProduct, ShoppingCartItem responseShoppingCartItem) {
        inventoryProduct.setQuantityInStock(inventoryProduct.getQuantityInStock() - responseShoppingCartItem.getQuantity());
        inventoryRepository.save(inventoryProduct);
    }

    public ShoppingCartSummeryDTO getShoppingCartSummery(long shoppingCartId) {
        List<ShoppingCartSummeryItem> allShoppingCartSummeryItems = getAllShoppingCartSummeryItems(shoppingCartId);
        return new ShoppingCartSummeryDTO(allShoppingCartSummeryItems,
                calculateTotalPrice(allShoppingCartSummeryItems));

    }

    public List<ShoppingCartSummeryItem> getAllShoppingCartSummeryItems(long shoppingCartId) {
        List<ShoppingCartSummeryItem> shoppingCartSummeryItems  = new ArrayList<>();
        Optional<List<ShoppingCartItem>> allShoppingCartItems = shoppingCartRepository.findAllByShoppingCartId(shoppingCartId);
        allShoppingCartItems.ifPresent(shoppingCartItems -> shoppingCartItems.forEach(item -> {
            Optional<Product> product = productRepository.findById(item.getProductId());
            product.ifPresent(product1 -> shoppingCartSummeryItems.add(new ShoppingCartSummeryItem(item.getQuantity(),
                    product1.getName(), product1.getPrice())));
        }));
        return shoppingCartSummeryItems;
    }

     public double calculateTotalPrice(List<ShoppingCartSummeryItem> allShoppingCartSummeryItems) {
         double totalPrice = allShoppingCartSummeryItems.stream()
                .mapToDouble(shoppingCartSummeryItem -> shoppingCartSummeryItem.getPrice() * shoppingCartSummeryItem.getQuantity())
                .sum();
         return Precision.round(totalPrice, 2);
    }


    @Transactional
    public ShoppingCartItemDTO removeItemFromShoppingCart(long shoppingCartId, long productId, int quantity) {
        Optional<ShoppingCartItem> shoppingCartItem = shoppingCartRepository.findByShoppingCartIdAndProductId(shoppingCartId, productId);
        if (shoppingCartItem.isPresent()) {
            if(shoppingCartItem.get().getQuantity() > quantity) {
                shoppingCartItem.get().setQuantity(shoppingCartItem.get().getQuantity() - quantity);
                shoppingCartRepository.save(shoppingCartItem.get());
            } else {
                shoppingCartRepository.delete(shoppingCartItem.get());
            }
            Optional<Inventory> inventoryProduct = inventoryRepository.findByProductId(shoppingCartItem.get().getProductId());
            inventoryProduct.ifPresent(inventory -> increaseQuantityInInventory(inventory, shoppingCartItem.get()));
            return new ShoppingCartItemDTO(shoppingCartItem.get());
        }
        return new ShoppingCartItemDTO();
    }

    private void increaseQuantityInInventory(Inventory inventoryProduct, ShoppingCartItem responseShoppingCartItem) {
        inventoryProduct.setQuantityInStock(inventoryProduct.getQuantityInStock() + responseShoppingCartItem.getQuantity());
        inventoryRepository.save(inventoryProduct);
    }


    public void deleteAllItemsFromShoppingCart(long shoppingCartId) {
        Optional<List<ShoppingCartItem>> shoppingCartItems = shoppingCartRepository.findAllByShoppingCartId(shoppingCartId);
        shoppingCartItems.ifPresent(itemList -> itemList.forEach(item -> {
            Optional<Inventory> inventoryProduct = inventoryRepository.findByProductId(item.getProductId());
            inventoryProduct.ifPresent(inventory -> increaseQuantityInInventory(inventory, item));
            shoppingCartRepository.delete(item);
            }));
    }
}
