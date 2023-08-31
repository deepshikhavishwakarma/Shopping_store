package com.shop.ShoppingStore.controllers;

import com.shop.ShoppingStore.Model.Roles;
import com.shop.ShoppingStore.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/roles")
public class RoleControllerAPI {
    @Autowired
    private RoleService rolesService;
    @PostMapping("/CreateRole")
    public Roles createRole(@RequestBody Roles role) {
        return rolesService.createRole(role);
    }

    @GetMapping("/GetARole/{roleId}")
    public Roles getRole(@PathVariable Long roleId) {
        return rolesService.getRoleById(roleId);
    }

    @DeleteMapping("/DeleteARole/{roleId}")
    public void deleteRole(@PathVariable Long roleId) {
        rolesService.deleteRole(roleId);
    }
}
