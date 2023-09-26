package com.example.productsservice.rest;

import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;
@RestController
@RequestMapping("/products")
public class ProductsController {
    private final Environment env;

    public ProductsController(Environment env){
        this.env = env;
    }

    @PostMapping
    public String createProduct(){
        return "HTTP POST Handled";
    }
    @GetMapping
    public String getProduct(){
        return "HTTP Get Handled" + env.getProperty("local.server.port");
    }
    @PutMapping
    public String updateProduct(){
        return "HTTP Put Handled";
    }
    @DeleteMapping
    public String deleteProduct(){
        return "HTTP DELETE Handled";
    }
}
