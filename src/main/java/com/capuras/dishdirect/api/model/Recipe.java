package com.capuras.dishdirect.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;
    private String recipeTitle;
    private String recipeIngredients;
    private String recipeInstructions;
    private Double recipePrice;



    public Recipe(String title, String ingredients, String instructions, Double price) {
        this.recipeTitle = title;
        this.recipeIngredients = ingredients;
        this.recipeInstructions = instructions;
        this.recipePrice = price;

    }

    public Recipe(){}

    public String getId(){
        return id;
    }
    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public Double getRecipePrice() {
        return recipePrice;
    }

    public void setRecipePrice(Double recipePrice) {
        this.recipePrice = recipePrice;
    }


    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }
}
