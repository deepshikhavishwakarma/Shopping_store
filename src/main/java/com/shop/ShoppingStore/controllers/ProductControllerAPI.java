package com.shop.ShoppingStore.controllers;

import com.shop.ShoppingStore.Model.Products;
import com.shop.ShoppingStore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductControllerAPI {
    @Autowired
    public ProductService productService;

    @PostMapping("/addProduct")
    public Products addProduct(@RequestBody Products product){
        return productService.addProduct(product);
    }

    @GetMapping("/getAllProducts")
    public List<Products> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/getProduct/{productId}")
    public Products getProductById(@PathVariable Long productId){
        return productService.getProductById(productId);
    }
    @PutMapping("/updateStock/{productId}")
    public Products updateStockQuantity(@PathVariable Long productId, @RequestBody int newQuantity) {
        return productService.updateStockQuantity(productId, newQuantity);
    }
    @PutMapping("/updateProduct/{productId}")
    public Products updateProduct(@PathVariable Long productId, @RequestBody Products updatedProduct) {
        return productService.updateProduct(productId, updatedProduct);
    }

    @GetMapping("/getProductsByCategory/{categoryId}")
    public List<Products> getProductsByCategoryId(@PathVariable Long categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }
//    @DeleteMapping("/deleteProduct/{productId}")
//    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
//        productService.deleteProduct(productId);
//        return ResponseEntity.ok("Product deleted successfully");
//    }
}
