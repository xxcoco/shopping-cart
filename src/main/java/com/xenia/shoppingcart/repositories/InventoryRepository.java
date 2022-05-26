package com.xenia.shoppingcart.repositories;

import com.xenia.shoppingcart.service.model.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory,Long> {

    Optional<Inventory> findByProductId(long productId);
}
