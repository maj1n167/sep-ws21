package ude.sep.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.model.Rating;

public interface RatingRepo extends JpaRepository<Rating, Integer> {

}
