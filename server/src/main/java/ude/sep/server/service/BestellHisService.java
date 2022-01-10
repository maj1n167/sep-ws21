package ude.sep.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.server.model.BestellHistorie;
import ude.sep.server.repo.BestellHisRepo;

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

