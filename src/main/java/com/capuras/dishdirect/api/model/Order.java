package com.capuras.dishdirect.api.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Order {

    @Id
    private String id;
    private String recipeTitle;
    private double price;
    @CreatedDate
    private Date dateOrdered;

    public Order(String recipeTitle, double price, Date dateOrdered) {
        this.recipeTitle = recipeTitle;
        this.price = price;
        this.dateOrdered = dateOrdered;
    }

    public String getId() {
        return id;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }
}
