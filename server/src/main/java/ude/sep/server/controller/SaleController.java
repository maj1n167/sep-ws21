package ude.sep.server.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.server.model.Sale;
import ude.sep.server.service.SaleService;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {this.saleService = saleService;}

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.findAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Sale> addSale(@RequestBody String input) throws JSONException {
        JSONObject body = new JSONObject(input);
        Sale newSale = new Sale(body.getInt("rId"),body.getString("start"), body.getString("end"));
        Sale sale = saleService.addSale(newSale);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }
        @GetMapping("/find/{id}")
    public ResponseEntity<List<Sale>> getAllSalesOf(@PathVariable int id) {
        List<Sale> sales = saleService.findAllSalesOf(id);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity delTime(@PathVariable int id) {
        saleService.delSale(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}