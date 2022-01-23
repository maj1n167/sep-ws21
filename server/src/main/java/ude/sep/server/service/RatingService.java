package ude.sep.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.server.model.Rating;
import ude.sep.server.repo.RatingRepo;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepo ratingRepo;

    @Autowired
    public RatingService(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    public Rating addRating(Rating ratings){
        System.out.println("Service= " + ratings.toString());
        return ratingRepo.save(ratings); }

    public List<Rating> findAllRatings() {
        return ratingRepo.findAll();
    }

    public List<Rating> findAllRatingsForRestaurantId(int id) {
            List<Rating> test = ratingRepo.findAll();
            test.removeIf(n -> n.getRestaurantId() != id);
            return test;
        }

    public List<Rating> findLatestRatingsForRestaurantId(int id, int latest) {
        List<Rating> output = null;
        List<Rating> test = ratingRepo.findAll();
        test.removeIf(n -> n.getRestaurantId() != id);
        for(int i=0;i<test.size();i++) {
            int e = 0;
            for(int j=0;j<test.size();j++) {
                if(test.get(j).getId()>test.get(e).getId()) {
                    e=j;
                }
            }
            output.add(test.get(e));
            if(output.size()==latest) {
                return output;
            }
        }
        return output;
    }
}
