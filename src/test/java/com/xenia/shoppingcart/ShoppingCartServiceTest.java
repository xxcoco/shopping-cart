package com.xenia.shoppingcart;

import com.xenia.shoppingcart.controller.model.ShoppingCartItemDTO;
import com.xenia.shoppingcart.controller.model.ShoppingCartSummeryItem;
import com.xenia.shoppingcart.repositories.InventoryRepository;
import com.xenia.shoppingcart.repositories.ProductRepository;
import com.xenia.shoppingcart.repositories.ShoppingCartRepository;
import com.xenia.shoppingcart.service.ShoppingCartService;
import com.xenia.shoppingcart.service.model.Inventory;
import com.xenia.shoppingcart.service.model.Product;
import com.xenia.shoppingcart.service.model.ShoppingCartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ProductRepository productRepository;

    private ShoppingCartService shoppingCartService;

    @BeforeEach
    public void setup () {
        shoppingCartService = new ShoppingCartService();
    }


    @Test
    public void calculateTotalPriceShouldReturnCorrectTotalPriceOfAllItems() {
        assertEquals(55.45, shoppingCartService.calculateTotalPrice(createShoppingCartSummeryItemList()));
    }

    private List<ShoppingCartSummeryItem> createShoppingCartSummeryItemList() {
        List<ShoppingCartSummeryItem> shoppingCartSummeryItems = new ArrayList<>();
        shoppingCartSummeryItems.add(new ShoppingCartSummeryItem(2, "Rindenmulch", 16.99));
        shoppingCartSummeryItems.add(new ShoppingCartSummeryItem(1, "Schnitzelbrötchen", 3.5));
        shoppingCartSummeryItems.add(new ShoppingCartSummeryItem(3, "Blumentopf", 5.99));
        return shoppingCartSummeryItems;
    }

    /*    @Test
    public void addItemToShoppingCartShouldAddItemToShoppingCartWhenInStock() {
        when(shoppingCartRepository.findByShoppingCartIdAndProductId(anyLong(),anyLong())).thenReturn(Optional.of(createMockShoppingCartItem()));
        when(inventoryRepository.findByProductId(anyLong())).thenReturn(Optional.of(new Inventory(1001, 2, 1)));
        when(inventoryRepository.save(any(Inventory.class)))
                .thenAnswer(inventory -> inventory.getArguments()[0]);

        assertEquals(shoppingCartService.addItemToShoppingCart(createMockShoppingCartItemDTO()), createMockShoppingCartItemDTO());
    }*/

    private Optional<ShoppingCartItem> createMockShoppingCartItem() {
        return Optional.of(new ShoppingCartItem(12345, 1001, 1));
    }

    private ShoppingCartItemDTO createMockShoppingCartItemDTO() {
        return new ShoppingCartItemDTO(12345, 1001, 1);
    }


  /*   @Test
   public void getAllShoppingCartSummeryItemsShouldReturnAllItemsFromCartWithId(){
        when(shoppingCartRepository.findAllByShoppingCartId(anyLong())).thenReturn(createShoppingCartItems());
        when(productRepository.findById(1001L)).thenReturn(Optional.of(new Product(10001L, "Blumentopf", 5.99)));
        when(productRepository.findById(1002L)).thenReturn(Optional.of(new Product(10002L, "Rindenmulch", 16.99)));
        assertEquals(3, shoppingCartService.getAllShoppingCartSummeryItems(2000011).size());
    }*/

    private Optional<List<ShoppingCartItem>> createShoppingCartItems() {
        List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();
        shoppingCartItems.add(new ShoppingCartItem(2000011, 1001L, 2));
        shoppingCartItems.add(new ShoppingCartItem(2000011, 1002L, 1));
        return Optional.of(shoppingCartItems);
    }


}
