package com.capuras.dishdirect.api.controller;

import com.capuras.dishdirect.api.model.Recipe;
import com.capuras.dishdirect.api.model.User;
import com.capuras.dishdirect.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;


    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }


    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userRepo.findAll();

    }
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        // Check for empty fields
        if ( user.getUserName().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty() || user.getPhoneNo().isEmpty()) {
            return ResponseEntity.badRequest().body("All fields must be filled");
        }

        // Check if user already exists
        if (userRepo.findByUserName(user.getUserName()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        // Save new user
        else {
            userRepo.save(user);
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

    @PostMapping("/order")
    public ResponseEntity<String> inputOrder(@RequestParam String userName, @RequestBody Recipe recipe) {
        // Find user by username from MongoDB
        User existingUser = userRepo.findByUserName(userName);

        // Check if user exists
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Add order to user's orders
        else {
            recipe.setOrderDate(new Date()); // Set current date as order date
            existingUser.addOrder(recipe);

            // Save updated user in MongoDB
            userRepo.save(existingUser);
            return ResponseEntity.ok("Recipe ordered successfully");
        }
    }
    @GetMapping("/{userName}/orders")
    public ResponseEntity<?> getUserOrders(@PathVariable String userName) {
        // Find user by username from MongoDB
        User existingUser = userRepo.findByUserName(userName);

        // Check if user exists
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        else {
            // Return user's orders
            List<Recipe> orders = existingUser.getOrders();
            return ResponseEntity.ok(orders);
        }
    }

}
