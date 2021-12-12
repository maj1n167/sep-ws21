package com.example.server.controller;

import com.example.server.model.BestellFood;
import com.example.server.model.WarenFood;
import com.example.server.service.BestellFoodService;
import com.example.server.service.WarenFoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Component
@RestController
@RequestMapping("/warenFood")
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

