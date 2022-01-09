package ude.sep.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.server.model.BestellFood;
import ude.sep.server.repo.BestellfoodRepo;

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
