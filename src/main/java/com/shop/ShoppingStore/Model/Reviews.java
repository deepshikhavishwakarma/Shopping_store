package com.shop.ShoppingStore.Model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_seq")
    @SequenceGenerator(name = "reviews_seq", sequenceName = "reviews_seq", allocationSize = 1)
    private Long review_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user_id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product_id;
    private int rating;
    private String review_text;
    private LocalDateTime review_date;
    
    public Long getReview_id() {
        return review_id;
    }
    public void setReview_id(Long review_id) {
        this.review_id = review_id;
    }
    public Users getUser_id() {
        return user_id;
    }
    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }
    public Products getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Products product_id) {
        this.product_id = product_id;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getReview_text() {
        return review_text;
    }
    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }
    public LocalDateTime getReview_date() {
        return review_date;
    }
    public void setReview_date(LocalDateTime review_date) {
        this.review_date = review_date;
    }

    @Override
    public String toString() {
        return "Reviews [review_id=" + review_id + ", user_id=" + user_id + ", product_id=" + product_id + ", rating="
                + rating + ", review_text=" + review_text + ", review_date=" + review_date + "]";
    }
    
}
