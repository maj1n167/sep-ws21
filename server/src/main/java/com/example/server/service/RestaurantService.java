package com.example.server.service;

import com.example.server.model.Restaurant;
import com.example.server.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RestaurantService {
    private final RestaurantRepo restaurantRepo;

    @Autowired
    public RestaurantService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public Restaurant addRestaurant(Restaurant restaurants){
        return restaurantRepo.save(restaurants);
    }

    public List<Restaurant> findAllRestaurants(){
        return restaurantRepo.findAll();
    }

//    public Restaurant findRestaurantByRestaurantId(int id){ return restaurantRepo.findRestaurantByRestaurantId(id); }

    public Restaurant updateRestaurant(Restaurant restaurants){
        return restaurantRepo.save(restaurants);
    }

    public void deleteRestaurant(int restaurantId){
        restaurantRepo.deleteById(restaurantId);
    }

}
