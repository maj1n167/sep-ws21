package com.example.server.service;

import com.example.server.model.Fav;
import com.example.server.repo.FavRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavService {
    private final FavRepo favRepo;

    @Autowired
    public FavService(FavRepo favRepo) {this.favRepo = favRepo;}

    public Fav addFav(Fav fav) {return favRepo.save(fav);}

    public List<Fav> findAllFavsOf(int favOf) {return favRepo.findAllByFavOf(favOf);}

    public void delFav(int fav) {favRepo.deleteById(fav);}

}
