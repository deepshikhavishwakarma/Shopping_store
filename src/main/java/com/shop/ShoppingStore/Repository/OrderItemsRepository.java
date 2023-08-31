package com.shop.ShoppingStore.Repository;

import com.shop.ShoppingStore.Model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemsRepository extends JpaRepository<OrderItems, Long>{

}
