package ude.sep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.model.ResBestellungen;
import ude.sep.repo.ResBesRepo;

import java.util.List;

@Service
public class ResBesService {

    private final ResBesRepo resBesRepo;

    @Autowired
    public ResBesService(ResBesRepo resBesRepo) {
        this.resBesRepo = resBesRepo;
    }

    public ResBestellungen addResBes(ResBestellungen resBestellungen){
        return resBesRepo.save(resBestellungen);
    }

    public List<ResBestellungen> findAllResBes(){
        return resBesRepo.findAll();
    }

    public ResBestellungen updateResBes(ResBestellungen resBestellungen){
        return resBesRepo.save(resBestellungen);
    }

    public void deleteResBes(int Id){
        resBesRepo.deleteById(Id);
    }


}
