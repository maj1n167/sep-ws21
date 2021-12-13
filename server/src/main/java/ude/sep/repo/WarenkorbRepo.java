package ude.sep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.model.Warenkorb;

public interface WarenkorbRepo extends JpaRepository<Warenkorb,Integer> {
    Warenkorb findWarenkorbByWarenkorbId(int warenkorbId);
}
