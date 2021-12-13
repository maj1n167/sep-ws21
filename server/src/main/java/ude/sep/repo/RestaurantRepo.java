package ude.sep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.model.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
    Restaurant findRestaurantByRestaurantId(int id);
}