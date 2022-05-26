package com.xenia.shoppingcart.controller;

import com.xenia.shoppingcart.controller.model.ShoppingCartItemDTO;
import com.xenia.shoppingcart.controller.model.ShoppingCartSummeryDTO;
import com.xenia.shoppingcart.service.ShoppingCartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @ApiOperation("Adds a new item to the shopping cart or updates the quantity of the item if it's already in the cart")
    @PostMapping(path = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShoppingCartItemDTO> addItemToShoppingCart(@RequestBody ShoppingCartItemDTO requestShoppingCartItemDTO) {
        ShoppingCartItemDTO responseShoppingCartItemDTO = shoppingCartService.addItemToShoppingCart(requestShoppingCartItemDTO);
        if(responseShoppingCartItemDTO.getQuantity() != 0)
            return new ResponseEntity<>(responseShoppingCartItemDTO, HttpStatus.CREATED);
        else return new ResponseEntity<>(responseShoppingCartItemDTO, HttpStatus.CONFLICT);
    }

    @ApiOperation("Creates a summery of all item in the shopping cart")
    @GetMapping("/checkout")
    public ResponseEntity<ShoppingCartSummeryDTO> getShoppingCartSummery(@RequestParam long shoppingCartId) {
        return new ResponseEntity<>(shoppingCartService.getShoppingCartSummery(shoppingCartId), HttpStatus.OK);
    }

    @ApiOperation("Removes an item from the shopping cart")
    @DeleteMapping("/remove")
    public ResponseEntity<ShoppingCartItemDTO> removeItemFromShoppingCart(@RequestParam("shoppingCartId") long cartId,
                                                           @RequestParam("productId") long productId,
                                                           @RequestParam("quantity") int quantity) {
        return new ResponseEntity<>(shoppingCartService.removeItemFromShoppingCart(cartId, productId, quantity), HttpStatus.OK);
    }

    @ApiOperation("Clears the shopping cart")
    @DeleteMapping("/clear")
    public ResponseEntity<Long> clearShoppingCart(@RequestParam("shoppingCartId") long shoppingCartId) {
        shoppingCartService.deleteAllItemsFromShoppingCart(shoppingCartId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
