package com.shop.ShoppingStore.services;

import com.shop.ShoppingStore.Model.Categories;
import com.shop.ShoppingStore.Model.Products;
import com.shop.ShoppingStore.Repository.CategoryRepository;
import com.shop.ShoppingStore.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;
//    private ProductService (ProductsRepository productsRepository){
//        this.productsRepository = productsRepository;
//    }
    @Autowired
    private CategoryRepository categoryRepository;


    public Products addProduct(Products Product){
        return productsRepository.save(Product);
    }

    public Products getProductById(Long Id){
        return productsRepository.findById(Id).orElse(null);
    }

    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }

    public Products updateStockQuantity(Long productId, int newQuantity) {
        // searches the product by using product id.
        Products product = productsRepository.findById(productId).orElse(null);

        // if product is present then proceed.
        if (product != null) {
            product.setStock_quantity(newQuantity);
            return productsRepository.save(product);
        }
        return null;
    }

    public Products updateProduct(Long productId, Products updatedProduct) {
        // we use product ID to search the product in the Database.
        Products existingProduct = productsRepository.findById(productId).orElse(null);


        if (existingProduct != null) {
            Categories updatedCategory = updatedProduct.getCategory();

            if (updatedCategory != null) {
                // Check if the updatedCategory already exists in the database
                Categories fetchedCategory = categoryRepository.findById(updatedCategory.getCategoryId()).orElse(null);
                if (fetchedCategory != null) {
                    existingProduct.setCategory(fetchedCategory);
                } else {
                    return null;
                }
            }

            existingProduct.setProduct_name(updatedProduct.getProduct_name());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setStock_quantity(updatedProduct.getStock_quantity());
            existingProduct.setImage_Url(updatedProduct.getImage_Url());

            return productsRepository.save(existingProduct);
        }
        return null;
    }

    public List<Products> getProductsByCategoryId(Long categoryId) {
        return productsRepository.findByCategory_CategoryId(categoryId);
    }


//    @Transactional  //error prone since it is associated with the cart table.
//    public void deleteProduct(Long productId) {
//        cartItemsService.deleteByProductId(productId);
//        if (productsRepository.existsById(productId)) {
//            // Delete the product
//            productsRepository.deleteById(productId);
//        }
//    }


}
