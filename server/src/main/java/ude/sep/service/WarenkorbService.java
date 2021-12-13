package ude.sep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.model.Warenkorb;
import ude.sep.repo.WarenkorbRepo;

import java.util.List;

@Service
public class WarenkorbService {

    private final WarenkorbRepo warenkorbRepo;

    @Autowired
    public WarenkorbService(WarenkorbRepo warenkorbRepo) {
        this.warenkorbRepo = warenkorbRepo;
    }

    public Warenkorb addWare(Warenkorb warenkorb){
        return warenkorbRepo.save(warenkorb);
    }

    public List<Warenkorb> findAllWaren(){
        return warenkorbRepo.findAll();
    }

    public Warenkorb updateWaren(Warenkorb warenkorb){
        return warenkorbRepo.save(warenkorb);
    }

    public void deleteWaren(int warenId){
        warenkorbRepo.deleteById(warenId);
    }

    public Warenkorb findWarenkorbById(int warenkorbId) {
        return warenkorbRepo.findWarenkorbByWarenkorbId(warenkorbId);
    }
}



