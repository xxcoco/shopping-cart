package com.xenia.shoppingcart.service.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Inventory {

    @NotNull
    @Id
    private Long productId;

    @NotNull
    private Long quantityInStock;

    @NotNull
    private Integer minimumOrder;

    public Inventory() {
    }

    public Inventory(long productId, long quantityInStock, int minimumOrder) {
        this.productId = productId;
        this.quantityInStock = quantityInStock;
        this.minimumOrder = minimumOrder;
    }
}
