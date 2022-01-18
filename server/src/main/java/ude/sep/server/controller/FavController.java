package ude.sep.server.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.server.model.Fav;
import ude.sep.server.model.User;
import ude.sep.server.service.FavService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

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

    @PostMapping("/send")
    public ResponseEntity sendSaleToFav(@RequestBody String input) throws IOException, JSONException {
        System.out.println("test bei Fav");
        JSONObject body = new JSONObject(input);
        //all users
        List<Fav> favs = favService.findAllByRestaurantId(body.getInt("rId"));
        // an user senden
        for(int i = 0; i < favs.size();i++) {
            Fav current = favs.get(i);
            JSONObjectPOST("http://localhost:8080/user/send/sale/" + current.getFavOf(), input);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //POST request um Daten über ein POSTMapping in die DB zu bekommen
    public void JSONObjectPOST(String inputUrl, String data) throws IOException {
        URL url = new URL(inputUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        con.getOutputStream().write(data.getBytes("UTF-8"));
        con.getInputStream();
    }
}
