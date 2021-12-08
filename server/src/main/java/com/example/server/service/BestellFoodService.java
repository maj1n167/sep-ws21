package com.example.server.service;

import com.example.server.model.BestellFood;
import com.example.server.repo.BestellfoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestellFoodService {
    private final BestellfoodRepo bestellfoodRepo;

    @Autowired
    public BestellFoodService(BestellfoodRepo bestellfoodRepo){this.bestellfoodRepo = bestellfoodRepo;}
    public BestellFood addBestellFood(BestellFood bestellFood){return bestellfoodRepo.save(bestellFood);}
    public List<BestellFood> findAllBestellFood(){return bestellfoodRepo.findAll();}
    public void deleteBestellFood(int bestellfoodId){bestellfoodRepo.deleteById(bestellfoodId);}
}
