package com.shop.ShoppingStore.Repository;

import com.shop.ShoppingStore.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>{
    List<Products> findByCategory_CategoryId(Long categoryId);
}
