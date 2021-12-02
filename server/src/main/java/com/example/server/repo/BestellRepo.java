package com.example.server.repo;

import com.example.server.model.Bestellungen;
import com.example.server.model.Warenkorb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BestellRepo extends JpaRepository<Bestellungen,Integer> {
}
