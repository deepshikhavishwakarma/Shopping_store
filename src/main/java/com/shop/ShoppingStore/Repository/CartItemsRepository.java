package com.shop.ShoppingStore.Repository;

import com.shop.ShoppingStore.Model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
//    List<CartItems> findByCart_id(Long cartId);
//@Modifying
//@Query("DELETE FROM CartItems c WHERE c.product_id.product_id = :productId")
//void deleteByProductId(Long productId);
}


