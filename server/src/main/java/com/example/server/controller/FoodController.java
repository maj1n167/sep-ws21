package com.example.server.controller;

import com.example.server.model.Food;
import com.example.server.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class FoodController {
    private final FoodService foodService;


    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }


    @GetMapping
    public ResponseEntity<List<Food>> getAllfoods() {

        List<Food> foods = foodService.findAllFoods();

        return new ResponseEntity<>(foods, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<Food> addFood(@RequestBody Food foods) {
        Food newFood = foodService.addFood(foods);
        return new ResponseEntity<>(newFood, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Food> updateFood(@RequestBody Food foods) {
        Food updateFood = foodService.addFood(foods);
        return new ResponseEntity<>(updateFood, HttpStatus.OK);
    }
}

