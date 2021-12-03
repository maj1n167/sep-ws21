package com.example.server.repo;

import com.example.server.model.User;
import com.example.server.model.Warenkorb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarenkorbRepo extends JpaRepository<Warenkorb,Integer> {
    Warenkorb findWarenkorbByWarenkorbId(int warenkorbId);
}
