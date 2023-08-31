package com.shop.ShoppingStore.controllers;

import com.shop.ShoppingStore.Model.Products;
import com.shop.ShoppingStore.Model.Reviews;
import com.shop.ShoppingStore.Model.Users;
import com.shop.ShoppingStore.Repository.ProductsRepository;
import com.shop.ShoppingStore.Repository.UserRepository;
import com.shop.ShoppingStore.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsControllerAPI {
    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @PostMapping("/createReview")
    public ResponseEntity<String> createReview(@RequestBody Map<String, Object> request) {
        Long userId = Long.parseLong(request.get("userId").toString());
        Long productId = Long.parseLong(request.get("productId").toString());
        int rating = Integer.parseInt(request.get("rating").toString());
        String text = request.get("text").toString();

        String result = reviewsService.createReview(userId, productId, rating, text);

        if ("review created".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @GetMapping("/getReviewsByUserId/{userId}")
    public String getReviewsByUserId(@PathVariable Long userId) {
        return reviewsService.getReviewsByUserId(userId);
    }

    @GetMapping("/getReviewsByProductId/{productId}")
    public List<Reviews> getReviewsByProductId(@PathVariable Long productId) {
        return reviewsService.getReviewsByProductId(productId);
    }

    @PutMapping("/updateReview/{reviewId}")
    public ResponseEntity<Reviews> updateReview(@PathVariable Long reviewId, @RequestBody Reviews updatedReview) {
        Reviews review = reviewsService.updateReview(reviewId, updatedReview);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteReview/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        boolean deleted = reviewsService.deleteReview(reviewId);
        if (deleted) {
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }
}
