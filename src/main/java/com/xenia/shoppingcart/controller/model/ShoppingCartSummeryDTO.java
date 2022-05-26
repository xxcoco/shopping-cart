package com.xenia.shoppingcart.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartSummeryDTO {

    @ApiModelProperty(notes = "List of all items in the shopping cart including quantity, name and price/item")
    private List<ShoppingCartSummeryItem> shoppingCartSummeryItems;

    @ApiModelProperty(notes = "Total price of all items")
    private double totalPrice;

    public ShoppingCartSummeryDTO() {
    }
    public ShoppingCartSummeryDTO(List<ShoppingCartSummeryItem> allShoppingCartSummeryItems, double totalPrice) {
        this.shoppingCartSummeryItems = allShoppingCartSummeryItems;
        this.totalPrice = totalPrice;
    }
}
