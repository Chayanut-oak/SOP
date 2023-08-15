package com.example.lab3.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class GeneratePasswordService {
   @RequestMapping(value = "/{username}.generate")
   @ResponseBody
    public String generate(@PathVariable String username){
       int min = 100000000;
       int max = 999999999;
       int password = (int)Math.floor(Math.random() * (max - min + 1) + min);;
       return "Hi, "+ username + "\n" + "Your new password is "+password+".";
    }
}
