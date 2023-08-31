package com.shop.ShoppingStore.services;

import com.shop.ShoppingStore.Model.Products;
import com.shop.ShoppingStore.Model.Reviews;
import com.shop.ShoppingStore.Model.Users;
import com.shop.ShoppingStore.Repository.ProductsRepository;
import com.shop.ShoppingStore.Repository.ReviewsRepository;
import com.shop.ShoppingStore.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductsRepository productsRepository;


    public String createReview(Long userId, Long productId, int rating, String text) {
        Reviews reviews = new Reviews();
        Users users = userRepository.findById(userId).orElse(null);
        Products products = productsRepository.findById(productId).orElse(null);
        if (users == null) {
            return "Review was not created. Invalid user ID.";
        }

        if (products == null) {
            return "Review was not created. Invalid product ID.";
        }

        if (rating < 1 || rating > 5) {
            return "Review was not created. Invalid rating.";
        }

        reviews.setReview_date(LocalDateTime.now());
        reviews.setReview_text(text);
        reviews.setProduct_id(products);
        reviews.setUser_id(users);
        reviews.setRating(rating);
        reviewsRepository.save(reviews);

        return "review created";

    }



    public String getReviewsByUserId(Long userId) {
        List<Reviews> reviews = reviewsRepository.findAll();
        List<Reviews> data = new ArrayList<>();
        for(Reviews r : reviews){
            if(Objects.equals(r.getUser_id().getUserId(), userId)){
                data.add(r);
            }
        }
        return "Fetched " + data.size() + " records";
    }

//    public String getReviewsByProductId(Long productId) {
//        List<Reviews> reviews = reviewsRepository.findAll();
//        List<Reviews> data = new ArrayList<>();
//        for(Reviews r : reviews){
//            if(Objects.equals(r.getProduct_id().getProduct_id(), productId)){
//                data.add(r);
//            }
//        }
//        return "Fetched " + data.size() + " records";
//    }

    public List<Reviews> getReviewsByProductId(Long productId) {
        List<Reviews> reviews = reviewsRepository.findAll();
        List<Reviews> data = new ArrayList<>();

        for (Reviews r : reviews) {
            if (Objects.equals(r.getProduct_id().getProduct_id(), productId)) {
                data.add(r);
            }
        }

        return data;
    }

    public Reviews updateReview(Long reviewId, Reviews updatedReview) {
        Reviews existingReview = reviewsRepository.findById(reviewId).orElse(null);
        if (existingReview != null) {
            existingReview.setRating(updatedReview.getRating());
            existingReview.setReview_text(updatedReview.getReview_text());
            return reviewsRepository.save(existingReview);
        }
        return null;
    }

    public boolean deleteReview(Long reviewId) {
        Reviews existingReview = reviewsRepository.findById(reviewId).orElse(null);
        if (existingReview != null) {
            reviewsRepository.delete(existingReview);
            return true;
        }
        return false;
    }
}
