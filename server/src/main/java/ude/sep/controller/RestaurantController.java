package ude.sep.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.model.Restaurant;
import ude.sep.service.RestaurantService;

import java.util.List;

    @RestController
    @RequestMapping("/restaurant")
    public class RestaurantController {
        private final RestaurantService restaurantService;


        public RestaurantController(RestaurantService restaurantService) {
            this.restaurantService = restaurantService;
        }


        @GetMapping
        public ResponseEntity<List<Restaurant>> getAllRestaurants() {

            List<Restaurant> restaurants = restaurantService.findAllRestaurants();

            return new ResponseEntity<>(restaurants, HttpStatus.OK);

        }

        @GetMapping("/find/{id}")
        public ResponseEntity<Restaurant> getRestaurantByRestaurantId(@PathVariable("id") int id) {
            List<Restaurant> restaurants = restaurantService.findAllRestaurants();
            Restaurant restaurant = null;
            for (int i = 0; i < restaurants.size(); i++) {
                if (restaurants.get(i).getRestaurantId() == id) {
                    restaurant = restaurants.get(i);
                }
            }
            if (restaurant != null) {
                return new ResponseEntity<>(restaurant, HttpStatus.OK);
            } else { return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); }
        }

        @PostMapping("/add")
        public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurants) {
            Restaurant newRestaurant = restaurantService.addRestaurant(restaurants);
            return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
        }

        @PutMapping("/update")
        public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant input) {
            Restaurant updateRestaurant = restaurantService.updateRestaurant(input);
            return new ResponseEntity<>(updateRestaurant, HttpStatus.OK);
        }
    }

