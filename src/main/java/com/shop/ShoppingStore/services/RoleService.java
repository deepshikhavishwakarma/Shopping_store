package com.shop.ShoppingStore.services;

import com.shop.ShoppingStore.Model.Roles;
import com.shop.ShoppingStore.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RolesRepository rolesRepository;

    public Roles createRole(Roles role) {
        return rolesRepository.save(role);
    }

    public Roles getRoleById(Long roleId) {
        return rolesRepository.findById(roleId).orElse(null);
    }

    public void deleteRole(Long roleId) {
        rolesRepository.deleteById(roleId);
    }
}
