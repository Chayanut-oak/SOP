package com.example.newlab4.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MathAPI {
    @GetMapping(value = "/plus/{n1}/{n2}")
    @ResponseBody
    public String myPlus(@PathVariable double n1,@PathVariable double n2){
        return String.valueOf(n1 + n2);
    }

    @GetMapping(value = "/minus/{n1}/{n2}")
    @ResponseBody
    public String myMinus(@PathVariable double n1,@PathVariable double n2){
        return String.valueOf(n1 - n2);
    }

    @GetMapping(value = "/multi/{n1}/{n2}")
    @ResponseBody
    public String myMulti(@PathVariable double n1, @PathVariable double n2){
        return String.valueOf(n1 * n2);
    }

    @GetMapping(value = "/divide/{n1}/{n2}")
    @ResponseBody
    public String myDivide(@PathVariable double n1,@PathVariable double n2){
        return String.valueOf(n1 / n2);
    }

    @GetMapping(value = "/mod/{n1}/{n2}")
    @ResponseBody
    public String myMod(@PathVariable double n1,@PathVariable double n2){
        return String.valueOf(n1 % n2);
    }
    @PostMapping (value = "/max")
    @ResponseBody
    public String myMax(@RequestBody MultiValueMap<String, String> n){
        Map<String, String> d = n.toSingleValueMap();
        return Math.max(Double.parseDouble(d.get("n1")),Double.parseDouble(d.get("n2")))+"";
    }
}
