package ude.sep.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.Bestellungen;

public interface BestellRepo extends JpaRepository<Bestellungen,Integer> {
}
