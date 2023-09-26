package com.example.newlab7.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("Product")
public class Product implements Serializable {
    @Id
    private String _Id;
    private String productName;
    private double productCost;
    private double productProfit;
    private double productPrice;

    public Product(){

    }
    public Product(String _Id, String productName, double productCost, double productProfit, double productPrice) {
        this._Id = _Id;
        this.productName = productName;
        this.productCost = productCost;
        this.productProfit = productProfit;
        this.productPrice = productPrice;
    }
}
