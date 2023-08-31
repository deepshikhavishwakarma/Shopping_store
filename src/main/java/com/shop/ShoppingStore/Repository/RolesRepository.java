package com.shop.ShoppingStore.Repository;

import com.shop.ShoppingStore.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles , Long> {
    Roles findByName(String name); //checks if the role exists in the Roles table or not.
}
