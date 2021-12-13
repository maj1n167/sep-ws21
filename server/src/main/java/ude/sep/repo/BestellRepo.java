package ude.sep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.model.Bestellungen;

public interface BestellRepo extends JpaRepository<Bestellungen,Integer> {
}
