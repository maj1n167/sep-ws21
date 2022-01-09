package ude.sep.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ude.sep.server.model.BestellHistorie;
import ude.sep.server.model.Bestellungen;
import ude.sep.server.service.BestellService;

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

    @GetMapping("/find/{id}")
    public ResponseEntity<Bestellungen> getBestellId(@PathVariable("id") int id) {
        List<Bestellungen> bestellungen = bestellService.findAllBestellungen();
        Bestellungen bestellungen1 = null;
        for (int i = 0; i < bestellungen.size(); i++) {
            if (bestellungen.get(i).getBestellungId() == id) {
                bestellungen1 = bestellungen.get(i);
            }
        }
        return new ResponseEntity<>(bestellungen1, HttpStatus.OK);
    }


}
