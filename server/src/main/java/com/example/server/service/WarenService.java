package com.example.server.service;

import com.example.server.model.Warenkorb;
import com.example.server.repo.WarenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarenService {

    private final WarenRepo warenRepo;

    @Autowired
    public WarenService(WarenRepo warenRepo) {
        this.warenRepo = warenRepo;
    }

    public Warenkorb addWare(Warenkorb warenkorb){
        return warenRepo.save(warenkorb);
    }

    public List<Warenkorb> findAllWaren(){
        return warenRepo.findAll();
    }

    public Warenkorb updateWaren(Warenkorb warenkorb){
        return warenRepo.save(warenkorb);
    }

    public void deleteWaren(int warenId){
        warenRepo.deleteById(warenId);
    }

    public Warenkorb findWarenkorbById(int warenkorbId) {
        return warenRepo.findUserById(warenkorbId);
    }
}



