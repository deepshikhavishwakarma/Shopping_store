package com.shop.ShoppingStore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/pages")
public class PageController {
    @CrossOrigin
    @GetMapping("/signup")
    public String getSignup(){
        return "signup";
    }
    @CrossOrigin
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
    @CrossOrigin
    @GetMapping("/index/{name}")
    public String getIndex(@PathVariable String name){
        return "index";
    }

    @CrossOrigin
    @GetMapping("/index")
    public String getIndex1(){
        return "index";
    }
    @CrossOrigin
    @GetMapping("/AddProduct")
    public String showAddProduct(){
        return "addProduct";
    }

    @CrossOrigin
    @GetMapping("/getAllProducts")
    public String getAllProducts(){return "allProducts";}

    @CrossOrigin
    @GetMapping("/getAllCategories")
    public String getAllCategories(){return "allCategories";}

    @CrossOrigin
    @GetMapping("/GetUserDetails")
    public String getUserDetailsPage(){
        return "userDetails";
    }

    @CrossOrigin
    @GetMapping("/superUser")
    public String getSuperUserPage(){
        return "superUser";
    }

    @CrossOrigin
    @GetMapping("/cartControl")
    public String getCartOrderform(){
        return "cartControl";
    }

    @CrossOrigin
    @GetMapping("/paymentControl")
    public String getPaymentControlPage(){return "paymentControl";}

    @CrossOrigin
    @GetMapping("/getReviews")
    public String gettestPage(){
        return "getProductReviews";
    }

    @CrossOrigin
    @GetMapping("/seeProduct/{id}")
    public String getProductPage(){return "productPage";}

    @CrossOrigin
    @GetMapping("/getProduct/{categoryId}")
    public String getProductByCategory(@PathVariable Long categoryId){
        return "getProductByCategory";
    }
}
