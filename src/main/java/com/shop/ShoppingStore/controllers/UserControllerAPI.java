package com.shop.ShoppingStore.controllers;

import com.shop.ShoppingStore.Model.Roles;
import com.shop.ShoppingStore.Model.Users;
import com.shop.ShoppingStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserControllerAPI {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody Users user) {
        try {
            userService.createUser(user);
            return ResponseEntity.ok("User created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Users user) {
        if (userService.verifyUserPassword(user.getUsername(), user.getPasswordHash())) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
    }

    @GetMapping("/getUser/{userId}")
    public Users getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUserById(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

    @PutMapping("/updateUser/{userId}")
    public Users updateUser(@PathVariable Long userId, @RequestBody Users updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }

    @GetMapping("/getUserByUsername/{username}")
    public Users getUserByUsername(@PathVariable String username){
        return userService.getUserByUserName(username);
    }

    //this part is broken
    @PostMapping("/addRoles/{userId}")
    public Users addRolesToUser(@PathVariable Long userId, @RequestBody Roles role ) {
        return userService.addRoleToUser(userId, role);
    }

    @GetMapping("/removeRoles/{userId}/{roleId}")
    public Users removeRolesFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
        return userService.removeRoleFromUser(userId, roleId);
    }

}
