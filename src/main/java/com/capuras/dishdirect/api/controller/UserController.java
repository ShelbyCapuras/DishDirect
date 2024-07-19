package com.capuras.dishdirect.api.controller;

import com.capuras.dishdirect.api.model.Order;
import com.capuras.dishdirect.api.model.Recipe;
import com.capuras.dishdirect.api.model.User;
import com.capuras.dishdirect.api.repository.RecipeRepository;
import com.capuras.dishdirect.api.repository.UserRepository;
import com.capuras.dishdirect.dto.OrderRequest;
import com.capuras.dishdirect.dto.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins="*")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RecipeRepository recipeRepo;



    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userRepo.findAll();

    }
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest sup) {

        // Check for empty fields
        if ( sup.getUserName().isEmpty() || sup.getPassword().isEmpty() || sup.getConfirmPassword().isEmpty() || sup.getEmail().isEmpty() || sup.getPhoneNo().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields must be filled");
        }
        // Check if password is matched
        if (!sup.getPassword().equals(sup.getConfirmPassword())){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password does not match");
        }
        // Check if user already exists
        if (userRepo.findByUserName(sup.getUserName()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
        // Save new user
        else {
            User newUser = new User(sup.getUserName(),sup.getPassword(),sup.getEmail(),sup.getPhoneNo());
            userRepo.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String userName, @RequestParam String password) {

        User existingUser = userRepo.findByUserName(userName);

        if (existingUser != null && existingUser.getPassword().equals(password)) {
            return ResponseEntity.ok("Login Success");
        } else  {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/users/{userName}/orders")
    public ResponseEntity<String> inputOrder(@RequestParam String userName, @RequestParam String recipeTitle) {
        // Find user by username from MongoDB
        User existingUser = userRepo.findByUserName(userName);
        // Find recipe by recipe title from MongoDB
        Recipe existingRecipe = recipeRepo.findByRecipeTitle(recipeTitle);

        // Check if user exists
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        // Check if recipe exists
        if (existingRecipe == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found");
        }
        else {
            Order orders = new Order(existingRecipe.getRecipeTitle(),existingRecipe.getRecipePrice(), new Date());
            existingUser.addOrder(orders);
            userRepo.save(existingUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Recipe Ordered successfully");

        }

    }
    @GetMapping("/users/{userName}/orders")
    public ResponseEntity<?> getUserOrders(@PathVariable String userName) {
        // Find user by username from MongoDB
        User existingUser = userRepo.findByUserName(userName);

        // Check if user exists
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        else {

            return ResponseEntity.ok(existingUser.getOrders());
        }
    }

}
