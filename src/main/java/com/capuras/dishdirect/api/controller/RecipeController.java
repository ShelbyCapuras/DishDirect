package com.capuras.dishdirect.api.controller;

import com.capuras.dishdirect.api.model.Order;
import com.capuras.dishdirect.api.model.Recipe;
import com.capuras.dishdirect.api.model.User;
import com.capuras.dishdirect.api.repository.RecipeRepository;
import com.capuras.dishdirect.dto.RecipeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins="*")
@RestController
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;


    @GetMapping("/recipes")
    public List<Recipe> getAllRecipe() {

        return recipeRepository.findAll();

    }

    @PostMapping("/createRecipe")
    public ResponseEntity<String> createRecipe(@RequestBody RecipeRequest recipe){
        if ( recipe.getRecipeTitle().isEmpty() || recipe.getRecipeIngredients().isEmpty() || recipe.getRecipeInstructions().isEmpty() || recipe.getRecipePrice().toString().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields must be filled");
        }
        if(recipe.getRecipePrice() == 0){
            return ResponseEntity.badRequest().body("Please input price");
        }
        if (recipeRepository.findByRecipeTitle(recipe.getRecipeTitle()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Recipe already exists");
        }
        else {
            Recipe newRecipe = new Recipe(recipe.getRecipeTitle(),recipe.getRecipeIngredients(),recipe.getRecipeInstructions(), recipe.getRecipePrice());
            recipeRepository.save(newRecipe);
            return ResponseEntity.status(HttpStatus.CREATED).body("Recipe created successfully");
        }

    }




}
