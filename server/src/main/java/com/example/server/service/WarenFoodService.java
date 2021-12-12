package com.example.server.service;

import com.example.server.model.BestellFood;
import com.example.server.model.WarenFood;
import com.example.server.repo.BestellfoodRepo;
import com.example.server.repo.WarenFoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WarenFoodService {
    private final WarenFoodRepo warenFoodRepo;

    @Autowired
    public WarenFoodService(WarenFoodRepo warenFoodRepo){this.warenFoodRepo = warenFoodRepo;}
    public WarenFood addWarenFood(WarenFood warenFood){return warenFoodRepo.save(warenFood);}
    public List<WarenFood> findAllWarenFood(){return warenFoodRepo.findAll();}
    public void deleteWarenFood(int warenfoodId){warenFoodRepo.deleteById(warenfoodId);}

}
