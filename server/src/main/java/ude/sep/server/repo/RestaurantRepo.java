package ude.sep.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
    Restaurant findRestaurantByRestaurantId(int id);
}