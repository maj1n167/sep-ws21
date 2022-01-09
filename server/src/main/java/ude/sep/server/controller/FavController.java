package ude.sep.server.controller;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.server.model.Fav;
import ude.sep.server.service.FavService;

import java.util.List;

@RestController
@RequestMapping("/fav")
public class FavController {
    private final FavService favService;

    public FavController(FavService favService) {this.favService = favService;}

    @GetMapping
    public ResponseEntity<List<Fav>> getAllFavs() {

        List<Fav> favs = favService.findAllFavs();

        return new ResponseEntity<>(favs, HttpStatus.OK);

    }

    @PostMapping("/add/{restaurantId}/{favOf}")
    public ResponseEntity<Fav> addFav(@PathVariable int favOf, @PathVariable int restaurantId) throws JSONException {
        Fav newFav = new Fav(favOf, restaurantId);
        Fav fav = favService.addFav(newFav);
        return new ResponseEntity<>(fav, HttpStatus.OK);
    }

    @GetMapping("/find/{favOf}")
    public ResponseEntity<List<Fav>> getAllFavsOf(@PathVariable String favOf) {
        List<Fav> favs = favService.findAllFavsOf(Integer.valueOf(favOf));
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
