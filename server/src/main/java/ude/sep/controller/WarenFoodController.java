package ude.sep.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ude.sep.model.WarenFood;
import ude.sep.service.WarenFoodService;

import java.util.List;
@Component
@RestController
@RequestMapping("/warenfood")
public class WarenFoodController {
        private final WarenFoodService warenFoodService;


        public WarenFoodController(WarenFoodService warenFoodService){this.warenFoodService = warenFoodService;}

        @GetMapping
        public ResponseEntity<List<WarenFood>> getAllWarenFood() {
            List<WarenFood> warenFood = warenFoodService.findAllWarenFood();
            return new ResponseEntity<>(warenFood, HttpStatus.OK);
        }


        @PostMapping("/add")
        public ResponseEntity<WarenFood> addWarenFood(@RequestBody WarenFood warenFood) {
            WarenFood newwarenFood = warenFoodService.addWarenFood(warenFood);
            return new ResponseEntity<>(newwarenFood, HttpStatus.CREATED);
        }
        @DeleteMapping("/delete/{warenfoodId}")
        public ResponseEntity deleteWarenFood(@PathVariable("warenfoodId") int warenfoodId) {
            warenFoodService.deleteWarenFood(warenfoodId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

