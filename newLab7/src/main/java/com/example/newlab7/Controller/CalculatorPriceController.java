package com.example.newlab7.Controller;

import com.example.newlab7.repository.CalculatorPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorPriceController {
    @Autowired
    private CalculatorPriceService calculatorPriceService;


    @GetMapping(value = "/getPrice/{c}/{pf}")
    public double serviceGetProducts(@PathVariable double c, @PathVariable double pf) {
        double newprice = calculatorPriceService.serviceGetProducts(c,pf);
        return newprice;
    }
}
