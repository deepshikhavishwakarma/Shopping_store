package com.shop.ShoppingStore.Model;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "Carts")
public class Carts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @Column(name = "created_at")
    private LocalDateTime created_at;

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Carts [cart_id=" + cart_id + ", user=" + user + ", created_at=" + created_at + "]";
    }


}
