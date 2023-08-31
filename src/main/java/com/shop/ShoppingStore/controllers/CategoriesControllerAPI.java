package com.shop.ShoppingStore.controllers;

import com.shop.ShoppingStore.Model.Categories;
import com.shop.ShoppingStore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesControllerAPI {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public Categories addCategory(@RequestBody Categories category) {
        return categoryService.addCategory(category);
    }

    @GetMapping("/getAllCategories")
    public List<Categories> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/getCategory/{categoryId}")
    public Categories getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}
