package com.shop.ShoppingStore.Repository;

import com.shop.ShoppingStore.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    Users findByUsername(String userName); //custom function to search in database by username.
    boolean existsByUsername(String username); //checks if the username already exists or not.
    boolean existsByEmail(String email); //checks if email already exists or not.
}
