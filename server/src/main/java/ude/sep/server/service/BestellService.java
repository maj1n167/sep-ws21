package ude.sep.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.server.model.BestellHistorie;
import ude.sep.server.model.Bestellungen;
import ude.sep.server.repo.BestellRepo;

import java.util.List;

@Service
public class BestellService {
private final BestellRepo bestellRepo;

    @Autowired
    public BestellService(BestellRepo bestellRepo) {this.bestellRepo = bestellRepo;}
    public Bestellungen addBestellungen(Bestellungen bestellungen){return bestellRepo.save(bestellungen);}
    public List<Bestellungen> findAllBestellungens(){return bestellRepo.findAll();}
    public  Bestellungen updateBestellungen(Bestellungen bestellungen){return bestellRepo.save(bestellungen);}
    public void deleteBestellungen(int bestellId){bestellRepo.deleteById(bestellId);}
    public List<Bestellungen> findAllBestellungen(){return bestellRepo.findAll();}

}
