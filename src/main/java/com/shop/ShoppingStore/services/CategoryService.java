package com.shop.ShoppingStore.services;

import com.shop.ShoppingStore.Model.Categories;
import com.shop.ShoppingStore.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Categories addCategory(Categories category) {
        return categoryRepository.save(category);
    }

    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Categories getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}

