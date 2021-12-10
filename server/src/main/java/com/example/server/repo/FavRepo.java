package com.example.server.repo;

import com.example.server.model.Fav;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavRepo extends JpaRepository<Fav, Integer> {
    void deleteById(int id);
    List<Fav> findAllByFavOf(int favOf);
}
