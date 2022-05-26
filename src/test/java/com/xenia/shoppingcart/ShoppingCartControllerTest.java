package com.xenia.shoppingcart;

import com.xenia.shoppingcart.controller.ShoppingCartController;
import com.xenia.shoppingcart.controller.model.ShoppingCartItemDTO;
import com.xenia.shoppingcart.repositories.InventoryRepository;
import com.xenia.shoppingcart.repositories.ProductRepository;
import com.xenia.shoppingcart.repositories.ShoppingCartRepository;
import com.xenia.shoppingcart.service.ShoppingCartService;
import com.xenia.shoppingcart.service.model.Inventory;
import com.xenia.shoppingcart.service.model.ShoppingCartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartControllerTest {


    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ProductRepository productRepository;

    private ShoppingCartController shoppingCartController;

   private  ShoppingCartService shoppingCartService;

 /*   @BeforeEach
    public void setup(){
        shoppingCartController = new ShoppingCartController();
        shoppingCartService = new ShoppingCartService();
        when(shoppingCartRepository.save(any(ShoppingCartItem.class)))
                .thenAnswer(item -> item.getArguments()[0]);
    }

    @Test
    public void addItemToShoppingCartShouldAddItemToShoppingCartWhenInStock() {
        when(inventoryRepository.findByProductId(1001)).thenReturn(Optional.of(new Inventory(1001, 2, 1)));
        when(shoppingCartRepository.findByShoppingCartIdAndProductId(12345,1001)).thenReturn(Optional.of(createMockShoppingCartItem()));
        when(inventoryRepository.save(any(Inventory.class)))
                .thenAnswer(inventory -> inventory.getArguments()[0]);

        assertEquals(shoppingCartController.addItemToShoppingCart(createMockShoppingCartItemDTO()), createMockShoppingCartItem());
    }

    private ShoppingCartItem createMockShoppingCartItem() {
        return new ShoppingCartItem(12345, 1001, 1);
    }

    private ShoppingCartItemDTO createMockShoppingCartItemDTO() {
        return new ShoppingCartItemDTO(12345, 1001, 1);
    } */

}
