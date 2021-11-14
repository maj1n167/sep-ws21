package com.example.server.repo;

import com.example.server.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
   // Restaurant findRestaurantByRestaurantId(int id);
}