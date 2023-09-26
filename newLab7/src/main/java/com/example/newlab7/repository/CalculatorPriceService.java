package com.example.newlab7.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorPriceService {

    public double serviceGetProducts(double Cost,double Profit){
        return Cost + Profit;
    }
}
