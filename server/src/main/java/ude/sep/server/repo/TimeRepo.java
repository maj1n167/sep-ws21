package ude.sep.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.Time;

import java.util.List;

public interface TimeRepo extends JpaRepository<Time, Integer> {
    void deleteById(int id);
    List<Time> findAllByTimeOf(int timeOf);
}