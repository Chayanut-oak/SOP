package com.example.lab3.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MathService {

    @GetMapping(value = "/add/{a}/{b}")
    @ResponseBody
    public String add(@PathVariable double a,@PathVariable double b){
        return String.valueOf(a + b);
    }

    @GetMapping(value = "/minus/{a}/{b}")
    @ResponseBody
    public String minus(@PathVariable double a,@PathVariable double b){
        return String.valueOf(a - b);
    }

    @GetMapping(value = "/multiply")
    @ResponseBody
    public String multiply(@RequestParam double num1, @RequestParam double num2){
        return String.valueOf(num1 * num2);
    }

    @GetMapping(value = "/divide")
    @ResponseBody
    public String divide(@RequestParam double num1,@RequestParam double num2){
        return String.valueOf(num1 / num2);
    }

}
