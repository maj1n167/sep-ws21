package com.example.server.controller;

import com.example.server.model.Bestellungen;
import com.example.server.service.BestellService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/bestellung")

public class BestellController {
    private final BestellService bestellService;


    public BestellController(BestellService bestellService){this.bestellService = bestellService;}

    @GetMapping
    public ResponseEntity<List<Bestellungen>> getAllBestellungens() {
        List<Bestellungen> bestellungen = bestellService.findAllBestellungens();
        return new ResponseEntity<>(bestellungen, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Bestellungen> addUser(@RequestBody Bestellungen bestellungen) {
        Bestellungen newBestellungen = bestellService.addBestellungen(bestellungen);
        return new ResponseEntity<>(newBestellungen, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{bestellId}")
    public ResponseEntity deleteBestellungen(@PathVariable("bestellId") int bestellId) {
        bestellService.deleteBestellungen(bestellId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
