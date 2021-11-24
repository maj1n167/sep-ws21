package com.example.server.service;

import com.example.server.model.Food;
import com.example.server.model.Kategorie;
import com.example.server.repo.KategorieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategorieService {
    private final KategorieRepo kategorieRepo;

    @Autowired
    public KategorieService(KategorieRepo kategorieRepo) {
        this.kategorieRepo = kategorieRepo;
    }


    public Kategorie addKategorie(Kategorie kategorie){
        return kategorieRepo.save(kategorie);
    }

    public List<Kategorie> findAllKategories(){
        return kategorieRepo.findAll();
    }

    public Kategorie updateKategorie(Kategorie kategorie){
        return kategorieRepo.save(kategorie);
    }

    public void deleteKategorie(int kategorieId){
        kategorieRepo.deleteById(kategorieId);
    }


}

