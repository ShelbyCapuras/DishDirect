package com.capuras.dishdirect.api.repository;

import com.capuras.dishdirect.api.model.Order;
import com.capuras.dishdirect.api.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<Recipe, String> {

    Recipe findByRecipeTitle(String recipeTitle);


}
