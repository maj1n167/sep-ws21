package ude.sep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.model.BestellHistorie;

public interface BestellHisRepo extends JpaRepository<BestellHistorie,Integer> {
}
