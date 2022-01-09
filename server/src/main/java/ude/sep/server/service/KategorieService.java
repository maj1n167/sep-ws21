package ude.sep.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.server.model.Kategorie;
import ude.sep.server.repo.KategorieRepo;
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

