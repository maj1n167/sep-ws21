package com.example.server.controller;

import com.example.server.model.Kategorie;
import com.example.server.repo.KategorieRepo;
import com.example.server.service.KategorieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kategorie")
public class KategorieController {
    private final KategorieService kategorieService;

    public KategorieController(KategorieService kategorieService) {
        this.kategorieService = kategorieService;
    }


    @GetMapping
    public ResponseEntity<List<Kategorie>> getAlLKategories(){
        List<Kategorie> kategories = kategorieService.findAllKategories();

        return new ResponseEntity<>(kategories, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Kategorie> addKategorie(@RequestBody Kategorie kategorie){
        Kategorie kategorie1 = kategorieService.addKategorie(kategorie);
        return new ResponseEntity<>(kategorie1,HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Kategorie> updateKategorie(@RequestBody Kategorie kategorie){
        Kategorie newKategorie = kategorieService.updateKategorie(kategorie);
                return new ResponseEntity<>(newKategorie, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{kategorieId}")
    public ResponseEntity<?> deleteKategorie(@PathVariable("kategorieId") int kategorieId){
        kategorieService.deleteKategorie(kategorieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}


