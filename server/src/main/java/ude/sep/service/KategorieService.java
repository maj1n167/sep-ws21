package ude.sep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.model.Kategorie;
import ude.sep.repo.KategorieRepo;

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

    public Kategorie updateKategorie( Kategorie kategorie){
        return kategorieRepo.save(kategorie);
    }

    public void deleteKategorie(int kategorieId){
        kategorieRepo.deleteById(kategorieId);
    }

    public List<Kategorie> findbyKategorieId(){
        return kategorieRepo.findAll();
    }

}

