package com.shop.ShoppingStore.Repository;

import com.shop.ShoppingStore.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;


public interface OrdersRepository extends JpaRepository<Orders, Long>{

}
