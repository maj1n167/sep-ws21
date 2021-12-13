package ude.sep.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ude.sep.model.BestellHistorie;
import ude.sep.service.BestellHisService;

import java.util.List;

@Component
@RestController
@RequestMapping("/bestellHistorie")
public class BestellHisController {
    private final BestellHisService bestellHisService;

    public BestellHisController(BestellHisService bestellHisService) {
        this.bestellHisService = bestellHisService;
    }

    @GetMapping
    public ResponseEntity<List<BestellHistorie>> getAllBestellhistorie() {
        List<BestellHistorie> bestellHistories = bestellHisService.findAllBestellHistories();
        return new ResponseEntity<>(bestellHistories, HttpStatus.OK);
    }

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

    @GetMapping("/find/{id}")
    public ResponseEntity<BestellHistorie> getBestellHisByRBestellHisId(@PathVariable("id") int id) {
        List<BestellHistorie> bestellHistories = bestellHisService.findAllBestellHistories();
        BestellHistorie bestellHis = null;
        for (int i = 0; i < bestellHistories.size(); i++) {
            if (bestellHistories.get(i).getBestellHisId() == id) {
                bestellHis = bestellHistories.get(i);
            }
        }
        return new ResponseEntity<>(bestellHis, HttpStatus.OK);
    }
}