package com.example.newlab7.repository;

import com.example.newlab7.pojo.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
@Configuration
@EnableCaching
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value="product" ,key="'product'")
    @RabbitListener(queues = "AddProductQueue")
    public boolean serviceAddProduct(Product product){
        try {
            repository.save(product);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @CachePut(value="product",key="'product'")
    @RabbitListener(queues = "UpdateProductQueue")
    public List<Product> serviceUpdateProduct(Product product){
        try {
            repository.save(product);
            return repository.findAll();
        }catch (Exception e){
            return null;
        }
    }

    @CacheEvict(value="product",key="'product'" )
    @RabbitListener(queues = "DeleteProductQueue")
    public boolean serviceDeleteProduct(Product product){
        try {
            repository.delete(product);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Cacheable(value="product",key="'product'")
    @RabbitListener(queues = "GetAllProductQueue")
    public List<Product> retrieveProduct() {
            return repository.findAll();
    }


    @RabbitListener(queues = "GetNameProductQueue")
    public Product retrieveProductName(String name) {
        return repository.findByName(name);
    }
    @Cacheable(value="product",key="'product:'+ #id")
    public Product retrieveProductId(String id) {
        return repository.findByID(id);
    }
}
