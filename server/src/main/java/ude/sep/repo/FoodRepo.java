package ude.sep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.model.Food;

public interface FoodRepo extends JpaRepository<Food, Integer> {

}