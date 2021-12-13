package ude.sep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.model.Fav;

import java.util.List;

public interface FavRepo extends JpaRepository<Fav, Integer> {
    void deleteById(int id);
    List<Fav> findAllByFavOf(int favOf);
}
