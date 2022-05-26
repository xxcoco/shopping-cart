package com.xenia.shoppingcart.repositories;

import com.xenia.shoppingcart.service.model.ShoppingCartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCartItem, Long> {

    Optional<ShoppingCartItem> findByShoppingCartIdAndProductId(long shoppingCartId, long productId);

    Optional<List<ShoppingCartItem>> findAllByShoppingCartId(long shoppingCartId);
}
