package com.example.server.controller;


import com.example.server.model.User;
import com.example.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.findAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User users) {
        User newUser = userService.addUser(users);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User users) {
        User updateUser = userService.addUser(users);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
}