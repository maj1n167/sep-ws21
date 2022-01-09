package ude.sep.server.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.Rating;

public interface RatingRepo extends JpaRepository<Rating, Integer> {

}
