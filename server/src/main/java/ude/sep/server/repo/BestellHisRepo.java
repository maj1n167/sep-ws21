package ude.sep.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.BestellHistorie;

public interface BestellHisRepo extends JpaRepository<BestellHistorie,Integer> {
}
