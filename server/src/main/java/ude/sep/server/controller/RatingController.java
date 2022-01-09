package ude.sep.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.server.model.Rating;
import ude.sep.server.service.RatingService;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.findAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Rating>> getAllRatingsforRestaurantId(@PathVariable int id) {
        List<Rating> ratings = ratingService.findAllRatingsForRestaurantId(id);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        System.out.println("Controller= " + rating.toString());
        Rating newRating = ratingService.addRating(rating);
        return new ResponseEntity<>(newRating, HttpStatus.CREATED);
    }
}
