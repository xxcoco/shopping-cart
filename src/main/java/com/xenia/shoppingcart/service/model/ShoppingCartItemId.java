package com.xenia.shoppingcart.service.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class ShoppingCartItemId implements Serializable {

    private Long shoppingCartId;

    private Long productId;
}
