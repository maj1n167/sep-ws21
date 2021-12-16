package ude.sep.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.model.Kategorie;
import ude.sep.model.Restaurant;
import ude.sep.service.KategorieService;

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


    @PutMapping("/update/{kategorieId}")
    public ResponseEntity<Kategorie> updateKategorie(@RequestBody Kategorie kategorie){
        Kategorie newKategorie = kategorieService.updateKategorie(kategorie);
                return new ResponseEntity<>(newKategorie, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{kategorieId}")
    public ResponseEntity<?> deleteKategorie(@PathVariable("kategorieId") int kategorieId){
        kategorieService.deleteKategorie(kategorieId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Kategorie> getKategorieById(@PathVariable("id") int id) {
        List<Kategorie> kategories = kategorieService.findbyKategorieId();
        Kategorie kategorie = null;
        for (int i = 0; i < kategories.size(); i++) {
            if (kategories.get(i).getKategorieId() == id) {
                kategorie = kategories.get(i);
            }
        }
        if (kategorie != null) {
            return new ResponseEntity<>(kategorie, HttpStatus.OK);
        } else { return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); }
    }





}


