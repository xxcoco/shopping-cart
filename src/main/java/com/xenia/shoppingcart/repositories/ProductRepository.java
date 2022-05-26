package com.xenia.shoppingcart.repositories;

import com.xenia.shoppingcart.service.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
