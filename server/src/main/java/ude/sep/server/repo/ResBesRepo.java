package ude.sep.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.ResBestellungen;

public interface ResBesRepo extends JpaRepository<ResBestellungen,Integer> {

}
