package com.capuras.dishdirect.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private  String email;
    private  String userName;
    private  String password;
    private  String phoneNo;
    private List<Order> orders = new ArrayList<>();

    public User(String userName, String password, String email, String phoneNo) {
        this.userName = userName;
        this.password = password;
        this.email =  email;
        this.phoneNo = phoneNo;
    }
    public User(){}

    public String getId() {
        return id;
    }

    public String getUserName() {return userName;}
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {return email;}
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {this.phoneNo = phoneNo;}

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    public void addOrder(Order order) {
        this.orders.add(order);
    }
}