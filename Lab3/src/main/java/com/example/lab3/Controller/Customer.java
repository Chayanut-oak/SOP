package com.example.lab3.Controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Customer {
    private String ID;
    private String name;
    private boolean sex;
    private int age;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = "Male".equals(sex) || "male".equals(sex);
    }

    public void setAge(Integer age) {
        this.age = age < 0 ? 0 : age;
    }

    public String getName() {
        return name;
    }

    public boolean isSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }

    public Customer(){
        this("",null,"female",0);
    }
    public Customer(String ID, String n, String s, int a){
    this.setID(ID);
    this.setName(n);
    this.setSex(s);
    this.setAge(a);
    }

}
