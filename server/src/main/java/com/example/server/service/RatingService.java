package com.example.server.service;

import com.example.server.model.Rating;
import com.example.server.repo.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepo ratingRepo;

    @Autowired
    public RatingService(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    public Rating addRating(Rating ratings){ return ratingRepo.save(ratings); }

    public List<Rating> findAllRatings(){
        return ratingRepo.findAll();
    }
}
