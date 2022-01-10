package ude.sep.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.server.model.Food;
import ude.sep.server.service.FoodService;

import java.util.List;

@RestController
@RequestMapping("/food")
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

    @DeleteMapping("/delete/{foodId}")
    public ResponseEntity<?> deleteFood(@PathVariable("foodId") int foodId){
        foodService.deleteFood(foodId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Food> getBestellHisByRBestellHisId(@PathVariable("id") int id) {
        List<Food> foods = foodService.findAllFoods();
        Food war = null;
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getFoodId() == id) {
                war = foods.get(i);
            }
        }
        return new ResponseEntity<>(war, HttpStatus.OK);
    }
}

