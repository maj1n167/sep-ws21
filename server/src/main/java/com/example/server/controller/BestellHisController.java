package com.example.server.controller;

import com.example.server.model.BestellHistorie;
import com.example.server.service.BestellHisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/bestellHistorie")
public class BestellHisController {
    private final BestellHisService bestellHisService;

    public BestellHisController(BestellHisService bestellHisService) {this.bestellHisService = bestellHisService;}

    @GetMapping
    public ResponseEntity <List<BestellHistorie>>getAllBestellhistorie(){
       List<BestellHistorie>bestellHistories= bestellHisService.findAllBestellHistories();
       return new ResponseEntity<>(bestellHistories,HttpStatus.OK);}

    @PostMapping("/add")
        public ResponseEntity<BestellHistorie> addUser(@RequestBody BestellHistorie bestellHistorie) {
       BestellHistorie newBestellHistorie = bestellHisService.addBestellhistorie(bestellHistorie);
        return new ResponseEntity<>(newBestellHistorie, HttpStatus.CREATED);
   }
    @DeleteMapping("/delete/{bestellHisId}")
    public ResponseEntity deleteBestellHistorie(@PathVariable("bestellHisId") int bestellHisId) {
        bestellHisService.deleteBestellHistorie(bestellHisId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
