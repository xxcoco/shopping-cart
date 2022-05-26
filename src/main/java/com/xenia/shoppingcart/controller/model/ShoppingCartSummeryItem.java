package com.xenia.shoppingcart.controller.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShoppingCartSummeryItem {

    private int quantity;

    private String name;

    private double price;

    public ShoppingCartSummeryItem() {
    }
    public ShoppingCartSummeryItem(int quantity, String name, double price) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
    }
}
