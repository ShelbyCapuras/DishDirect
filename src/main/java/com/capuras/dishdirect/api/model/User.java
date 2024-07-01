package com.capuras.dishdirect.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {
    private  String email;
    private  String userName;
    private  String password;
    private  String phoneNo;
    private List<Recipe> orders;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.orders = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public void setOrders(List<Recipe> orders) {
        this.orders = orders;
    }
    public List<Recipe> getOrders() {
        return orders;
    }

    public void addOrder(Recipe recipe) {
        this.orders.add(recipe);
    }



}