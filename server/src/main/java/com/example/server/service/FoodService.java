package com.example.server.service;

import com.example.server.model.Food;
import com.example.server.repo.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private final FoodRepo foodRepo;

    @Autowired
    public FoodService(FoodRepo foodRepo) {
        this.foodRepo = foodRepo;
    }

    public Food addFood(Food foods){
        return foodRepo.save(foods);
    }

    public List<Food> findAllFoods(){
        return foodRepo.findAll();
    }

    public Food updateFood(Food foods){
        return foodRepo.save(foods);
    }

    public void deleteFood(int foodId){
        foodRepo.deleteById(foodId);
    }



}
