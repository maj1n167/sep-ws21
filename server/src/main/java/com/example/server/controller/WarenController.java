package com.example.server.controller;

import com.example.server.model.Food;
import com.example.server.model.Warenkorb;
import com.example.server.service.FoodService;
import com.example.server.service.WarenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Warenkorb")
public class WarenController {


    private final WarenService warenService;

    public WarenController(WarenService warenService) {
        this.warenService = warenService;
    }


    @GetMapping
    public ResponseEntity<List<Warenkorb>> getAllWarenkorb() {

        List<Warenkorb> waren = warenService.findAllWaren();

        return new ResponseEntity<>(waren, HttpStatus.OK);

    }


    @PostMapping("/add")
    public ResponseEntity<Warenkorb> addWarenkorb(@RequestBody Warenkorb warenkorb) {
        Warenkorb newWarenkorb = warenService.addWare(warenkorb);
        return new ResponseEntity<>(newWarenkorb, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Warenkorb> updateWaren(@RequestBody Warenkorb warenkorb) {
        Warenkorb updateWare = warenService.addWare(warenkorb);
        return new ResponseEntity<>(updateWare, HttpStatus.OK);
    }



}
