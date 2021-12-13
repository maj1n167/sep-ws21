package ude.sep.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.model.Warenkorb;
import ude.sep.service.WarenkorbService;

import java.util.List;

@RestController
@RequestMapping("/warenkorb")
public class WarenkorbController {


    private final WarenkorbService warenkorbService;

    public WarenkorbController(WarenkorbService warenkorbService) {
        this.warenkorbService = warenkorbService;
    }


    @GetMapping
    public ResponseEntity<List<Warenkorb>> getAllWarenkorb() {

        List<Warenkorb> warenkorb = warenkorbService.findAllWaren();

        return new ResponseEntity<>(warenkorb, HttpStatus.OK);

    }


    @PostMapping("/add")
    public ResponseEntity<Warenkorb> addWarenkorb(@RequestBody Warenkorb warenkorb) {
        Warenkorb newWarenkorb = warenkorbService.addWare(warenkorb);
        return new ResponseEntity<>(newWarenkorb, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Warenkorb> updateWaren(@RequestBody Warenkorb warenkorb) {
        Warenkorb updateWare = warenkorbService.addWare(warenkorb);
        return new ResponseEntity<>(updateWare, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Warenkorb> getBestellHisByRBestellHisId(@PathVariable("id") int id) {
        List<Warenkorb> warenkorbs = warenkorbService.findAllWaren();
        Warenkorb war = null;
        for (int i = 0; i < warenkorbs.size(); i++) {
            if (warenkorbs.get(i).getWarenkorbId() == id) {
                war = warenkorbs.get(i);
            }
        }
        return new ResponseEntity<>(war, HttpStatus.OK);
    }

}
