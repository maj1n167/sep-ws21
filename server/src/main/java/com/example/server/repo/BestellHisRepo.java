package com.example.server.repo;

import com.example.server.model.BestellHistorie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BestellHisRepo extends JpaRepository<BestellHistorie,Integer> {
}
