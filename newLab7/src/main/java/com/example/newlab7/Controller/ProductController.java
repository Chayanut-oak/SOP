package com.example.newlab7.Controller;

import com.example.newlab7.pojo.Product;
import com.example.newlab7.repository.CalculatorPriceService;
import com.example.newlab7.repository.ProductService;
import org.atmosphere.config.service.Post;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProductService productService;
    @Autowired
    private CalculatorPriceService calculatorPriceService;

    @PostMapping(value = "/addProduct")
    public boolean serviceAddProduct(@RequestBody Product product){
        return (boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "add", product);
    }


    @PostMapping(value = "/updateProduct")
    public List<Product> serviceUpdateProduct(@RequestBody Product product){
        return (List<Product>) rabbitTemplate.convertSendAndReceive("ProductExchange", "update", product);
    }
    @PostMapping(value = "/deleteProduct/{id}")
     public boolean serviceDeleteProduct(@PathVariable String id){
        Product product = productService.retrieveProductId(id);
        return (boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "delete", product);
    }


    @RequestMapping(value = "/products")
    public List<Product> getProducts() {
        return (List<Product>) rabbitTemplate.convertSendAndReceive("ProductExchange", "getall","");
    }
    @RequestMapping(value = "/productsbyname/{name}")
    public Product getProductsByName(@PathVariable String name) {
        return (Product) rabbitTemplate.convertSendAndReceive("ProductExchange", "getname",name);
    }
}
