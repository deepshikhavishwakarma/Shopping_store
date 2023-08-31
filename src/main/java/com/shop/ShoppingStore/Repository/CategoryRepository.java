package com.shop.ShoppingStore.Repository;

import com.shop.ShoppingStore.Model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    // Custom queries if needed
}
