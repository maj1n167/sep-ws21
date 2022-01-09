package ude.sep.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.Food;

public interface FoodRepo extends JpaRepository<Food, Integer> {

}