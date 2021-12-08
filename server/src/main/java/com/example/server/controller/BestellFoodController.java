package com.example.server.controller;

import com.example.server.model.BestellFood;
import com.example.server.model.Bestellungen;
import com.example.server.service.BestellFoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/bestellFood")
public class BestellFoodController {
    private final BestellFoodService bestellFoodService;


    public BestellFoodController(BestellFoodService bestellFoodService){this.bestellFoodService = bestellFoodService;}

    @GetMapping
    public ResponseEntity<List<BestellFood>> getAllBestellFood() {
        List<BestellFood> bestellFood = bestellFoodService.findAllBestellFood();
        return new ResponseEntity<>(bestellFood, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<BestellFood> addBestellFood(@RequestBody BestellFood bestellFood) {
        BestellFood newbestellFood = bestellFoodService.addBestellFood(bestellFood);
        return new ResponseEntity<>(newbestellFood, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{bestellfoodId}")
    public ResponseEntity deleteBestellFood(@PathVariable("bestellfoodId") int bestellfoodId) {
        bestellFoodService.deleteBestellFood(bestellfoodId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
