package com.example.server.controller;

import com.example.server.model.Fav;
import com.example.server.service.FavService;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fav")
public class FavController {
    private final FavService favService;

    public FavController(FavService favService) {this.favService = favService;}

    @GetMapping
    public ResponseEntity<List<Fav>> getAllUsers() {

        List<Fav> favs = favService.findAllFavs();

        return new ResponseEntity<>(favs, HttpStatus.OK);

    }

    @PostMapping("/add/{id}/{favOf}")
    public ResponseEntity<Fav> addFav(@PathVariable int favOf, @PathVariable int id) throws JSONException {
        Fav newFav = new Fav(favOf, id);
        Fav fav = favService.addFav(newFav);
        return new ResponseEntity<>(fav, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<List<Fav>> getAllFavsOf(@PathVariable String id) {
        List<Fav> favs = favService.findAllFavsOf(Integer.valueOf(id));
//        if(favs.size()!= 0) {
            return new ResponseEntity<>(favs, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(favs, HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity delFav(@PathVariable String id) {
        favService.delFav(Integer.valueOf(id));
        return new ResponseEntity(HttpStatus.OK);
    }
}
