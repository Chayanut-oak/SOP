package com.example.lab3.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class CustomerController {

    private List<Customer> customers;
    @GetMapping(value = "/customers")
    @ResponseBody
    public  List<Customer> getCustomers(){
        return this.customers;
    }

    public CustomerController(){
        customers = new ArrayList();
        customers.add(new Customer("1010","John","Male" ,25));
        customers.add(new Customer("1018","Peter","Male" ,24));
        customers.add(new Customer("1019","Sara","Female" ,23));
        customers.add(new Customer("1110","Rose","Female" ,23));
        customers.add(new Customer("1001","Emma","Female" ,30));
    }
    @GetMapping(value = "/customerbyid/{ID}")
    @ResponseBody
    public Customer getCustomerByID(@PathVariable String ID){
        for (int num = 0; num < customers.size() ; num++ ){
            if(customers.get(num).getID().equals(ID)){
                return customers.get(num);
            }
        }
        return null;
    }
    @GetMapping(value = "/customerbyname/{n}")
    @ResponseBody
    public Customer getCustomerByName(@PathVariable String n){
        for (int num = 0; num < customers.size() ; num++ ){
            if(customers.get(num).getName().equals(n)){
                return customers.get(num);
            }
        }
        return null;
    }
    @DeleteMapping(value = "/customerDelByid/{ID}")
    @ResponseBody
    public boolean delCustomerByID(@PathVariable String ID){
        for (int num = 0; num < customers.size() ; num++ ){
            if(customers.get(num).getID().equals(ID)){
                customers.remove(num);
                return true;
            }
        }
        return false;
    }
    @DeleteMapping (value = "/customerDelByname/{n}")
    @ResponseBody
    public boolean delCustomerByName(@PathVariable String n){
        for (int num = 0; num < customers.size() ; num++ ){
            if(customers.get(num).getName().equals(n)){
                customers.remove(num);
                return true;
            }
        }
        return false;
    }
    @GetMapping(value = "/addCustomer")
    @ResponseBody
    public boolean addCustomer(@RequestParam String ID,@RequestParam String n,@RequestParam String s,@RequestParam int a){
    Customer newcustomer = new Customer(ID,n,s,a);
        customers.add(newcustomer);
        return  true;
    }

    @PostMapping(value = "/addCustomer2")
    @ResponseBody
    public boolean addCustomer2(@RequestParam String ID,@RequestParam  String n,@RequestParam  String s,@RequestParam  int a){
        Customer newcustomer = new Customer(ID,n,s,a);
        customers.add(newcustomer);
        return  true;
    }
}
