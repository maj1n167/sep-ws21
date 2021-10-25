package com.example.server.controller;

import com.example.server.model.User;
import com.example.server.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Component
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
    // Sending Verfiycode to Email
    @GetMapping("/find/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {

       User users = userService.findUserByEmail(email);
       String currentEmail = users.getEmail();
        System.out.println(currentEmail);
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        users.setVerfiyCode(number);
        userService.updateUser(users);
       int code = users.getVerfiyCode();
       userService.sendEmail(currentEmail.toString(), "Ihr Verifizierung Code: "+code, "Verifizierungcode");


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