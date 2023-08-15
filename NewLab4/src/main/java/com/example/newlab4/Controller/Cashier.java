package com.example.newlab4.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class Cashier {
    private Change change;

    @GetMapping(value = "/getChange/{money}")
    @ResponseBody
    public Change getChange(@PathVariable int money){
     change = new Change();
    change.setB1000(money/1000);
    money = money%1000;
    change.setB500(money/500);
    money = money%500;
    change.setB100(money/100);
    money = money%100;
    change.setB20(money/20);
    money = money%20;
    change.setB10(money/10);
    money = money%10;
    change.setB5(money/5);
    money = money%5;
    change.setB1(money/1);
    return change;
    }
}
