package com.capuras.dishdirect.api.model;

import java.util.Date;

public class Recipe {
    private String RecipeTile;
    private Double RecipePrice;
    private Date OrderDate;


    public Recipe(String title, double price, Date dateOrdered) {
        this.RecipeTile = title;
        this.RecipePrice = price;
        this.OrderDate = dateOrdered;
    }

    public String getRecipeTile() {
        return RecipeTile;
    }

    public void setRecipeTile(String recipeTile) {
        RecipeTile = recipeTile;
    }

    public Double getRecipePrice() {
        return RecipePrice;
    }

    public void setRecipePrice(Double recipePrice) {
        RecipePrice = recipePrice;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }
}
