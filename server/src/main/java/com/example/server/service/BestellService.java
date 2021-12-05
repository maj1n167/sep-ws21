package com.example.server.service;

import com.example.server.model.Bestellungen;
import com.example.server.model.User;
import com.example.server.repo.BestellRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}
