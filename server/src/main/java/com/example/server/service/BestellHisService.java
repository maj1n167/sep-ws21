package com.example.server.service;

import com.example.server.model.BestellHistorie;
import com.example.server.repo.BestellHisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BestellHisService {
    private final BestellHisRepo bestellHisRepo;

    @Autowired
    public BestellHisService(BestellHisRepo bestellHisRepo) {this.bestellHisRepo = bestellHisRepo;}
    public BestellHistorie addBestellhistorie(BestellHistorie bestellHistorie){return  bestellHisRepo.save(bestellHistorie);}
    public List<BestellHistorie> findAllBestellHistories(){return bestellHisRepo.findAll();}
    public BestellHistorie upddateBestellHistorie(BestellHistorie bestellHistorie){return bestellHisRepo.save(bestellHistorie);}
    public void deleteBestellHistorie(int bestellHisId){bestellHisRepo.deleteById(bestellHisId);}

}

