package ude.sep.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.model.ResBestellungen;
import ude.sep.model.Warenkorb;
import ude.sep.service.ResBesService;

import java.util.List;

@RestController
@RequestMapping("/resBes")
public class ResBesController {

    private final ResBesService resBesService;

    public ResBesController(ResBesService resBesService) {
        this.resBesService = resBesService;
    }

    @DeleteMapping("/delete/{resBesId}")
    public ResponseEntity deleteResBes(@PathVariable("resBesId") int id) {
        resBesService.deleteResBes(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ResBestellungen> addResBes(@RequestBody ResBestellungen resBestellungen) {
        ResBestellungen resBestellungen1 = resBesService.addResBes(resBestellungen);
        return new ResponseEntity<>(resBestellungen1, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ResBestellungen> updateWaren(@RequestBody ResBestellungen resBestellungen) {
        ResBestellungen updateResb = resBesService.updateResBes(resBestellungen);
        return new ResponseEntity<>(updateResb, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResBestellungen> getBestellHisByRBestellHisId(@PathVariable("id") int id) {
        List<ResBestellungen> resBestellungens = resBesService.findAllResBes();
        ResBestellungen war = null;
        for (int i = 0; i < resBestellungens.size(); i++) {
            if (resBestellungens.get(i).getResBesId() == id) {
                war = resBestellungens.get(i);
            }
        }
        return new ResponseEntity<>(war, HttpStatus.OK);
    }
}

