package com.xenia.shoppingcart.service.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name= "SHOPPING_CART")
@Data
@IdClass(ShoppingCartItemId.class)
public class ShoppingCartItem {

    @Id
    private Long shoppingCartId;

    @Id
    private Long productId;

    @NotNull
    private Integer quantity;

    public ShoppingCartItem() {
    }
    public ShoppingCartItem(long shoppingCartId, long productId, int quantity) {
        this.shoppingCartId = shoppingCartId;
        this.productId = productId;
        this.quantity = quantity;
    }

}
