package com.xenia.shoppingcart.controller.model;

import com.xenia.shoppingcart.service.model.ShoppingCartItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShoppingCartItemDTO {

    @ApiModelProperty(required = true, notes = "Id of the shopping cart")
    private long shoppingCartId;

    @ApiModelProperty(required = true, notes = "Id of the product")
    private long productId;

    @ApiModelProperty(required = true, notes = "Quantity of the product")
    private int quantity;

    public ShoppingCartItemDTO() {
    }
    public ShoppingCartItemDTO(ShoppingCartItem shoppingCartItem) {
        this.shoppingCartId = shoppingCartItem.getShoppingCartId();
        this.productId = shoppingCartItem.getProductId();
        this.quantity = shoppingCartItem.getQuantity();
    }

    public ShoppingCartItemDTO(long shoppingCartId, long productId, int quantity) {
        this.shoppingCartId = shoppingCartId;
        this.productId = productId;
        this.quantity = quantity;
    }

}
