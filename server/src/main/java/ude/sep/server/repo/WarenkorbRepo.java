package ude.sep.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.Warenkorb;

public interface WarenkorbRepo extends JpaRepository<Warenkorb,Integer> {
    Warenkorb findWarenkorbByWarenkorbId(int warenkorbId);
}
