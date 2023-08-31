package com.shop.ShoppingStore.services;

import com.shop.ShoppingStore.Model.Roles;
import com.shop.ShoppingStore.Model.Users;
import com.shop.ShoppingStore.Repository.RolesRepository;
import com.shop.ShoppingStore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;

    // Implement service methods, e.g., createUser, getUserById, etc.
    public Users createUser(Users user) {
        // this first check if the username already exists or not
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        //similarly checks if email is already existing or not
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Roles defaultRole = rolesRepository.findByName("User");
        if (defaultRole == null) {
            throw new RuntimeException("Default role 'User' not found");
        }

        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(defaultRole);
        user.setRoles(userRoles);
        return userRepository.save(user);
    }

    public boolean verifyUserPassword(String username, String password) {
        Users user = userRepository.findByUsername(username);
        if (user != null) {
            return password.equals(user.getPasswordHash());
        }
        return false;
    }

    public Users getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }


    public Users getUserByUserName(String userName){
        return userRepository.findByUsername(userName);
    }

    public String deleteUser(Long id){
        try {
            userRepository.deleteById(id);
            return "User with id :  " + id + " successfully deleted";
        }catch (Exception e){
            return "error while deleting user (USER DOES NOT EXIST";
        }
    }

    public Users addRoleToUser(Long userId, Roles role) {
        Optional<Users> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.getRoles().add(role);
            return userRepository.save(user);
        }
        return null;
    }

    public Users removeRoleFromUser(Long userId, Long roleId) {
        //finding user and role using userid and role id.

        Optional<Users> optionalUser = userRepository.findById(userId);
        Optional<Roles> optionalRole = rolesRepository.findById(roleId);

        // if both are present then only we can proceed.
        if (optionalUser.isPresent() && optionalRole.isPresent()) {
            Users user = optionalUser.get();
            Roles role = optionalRole.get();

            user.getRoles().remove(role);
            userRepository.save(user);

            return user;
        }
        return null;
    }

    public Users updateUser(Long userId, Users updatedUser) {
        //first it fetches the user from the user ID
        Optional<Users> optionalUser = userRepository.findById(userId);

        //is user is pressent then it will go forward
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            // validating if null values are passed then that column will not be updated.
            if (updatedUser.getUsername() != null && !updatedUser.getUsername().isEmpty()) {
                user.setUsername(updatedUser.getUsername());
            }

            if (updatedUser.getEmail() != null && !updatedUser.getEmail().isEmpty()) {
                user.setEmail(updatedUser.getEmail());
            }

            if (updatedUser.getPasswordHash() != null && !updatedUser.getPasswordHash().isEmpty()) {
                user.setPasswordHash(updatedUser.getPasswordHash());
            }

            if (updatedUser.getShippingAddress() != null) {
                user.setShippingAddress(updatedUser.getShippingAddress());
            }

            if (updatedUser.getBillingAddress() != null) {
                user.setBillingAddress(updatedUser.getBillingAddress());
            }

            return userRepository.save(user);
        }

        return null;
    }



}
