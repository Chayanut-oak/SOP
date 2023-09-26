package com.example.newlab7.repository;

import com.example.newlab7.pojo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(value="{productName:'?0'}")
    public Product findByName(String name);
    @Query(value="{_Id:'?0'}")
    public Product findByID (String id);
}
