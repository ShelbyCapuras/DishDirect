package com.capuras.dishdirect.dto;

import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

public class OrderRequest {

    private String recipeTitle;
    public OrderRequest(String recipeTitle) {
        this.recipeTitle = recipeTitle;

    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }


}
