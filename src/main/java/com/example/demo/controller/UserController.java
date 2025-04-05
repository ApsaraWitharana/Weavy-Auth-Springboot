package com.example.demo.controller;

import com.example.demo.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    //add user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody String jsonPayload){
        try {
            String response = userService.createUser(jsonPayload);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(response);
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error user added:" + e.getMessage());
        }
    }

    //Get select id users
    @GetMapping("/{id}")
    public ResponseEntity<String> getUserDetails(@PathVariable ("id") String id){
        try {
            String response = userService.getUserDetails(id);
            return ResponseEntity.ok(response);
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getall user details:" + e.getMessage());
        }
    }

    // update user
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") String id, @RequestBody String jsonPayload) {
        try {
            String response = userService.updateUser(id, jsonPayload);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + e.getMessage());
        }
    }

    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
        try {
            String response = userService.deleteUser(id);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting user: " + e.getMessage());
        }
    }


    // get all
    @GetMapping
    public ResponseEntity<String> getAllUsers(@RequestParam("get") int get){
        try {
            String response = userService.listUsers(get);
            return ResponseEntity.ok(response);
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error get all users:" + e.getMessage());
        }
    }

}
