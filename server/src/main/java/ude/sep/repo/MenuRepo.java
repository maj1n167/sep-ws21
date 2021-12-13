package ude.sep.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.model.Menu;

public interface MenuRepo extends JpaRepository<Menu,Integer> {
}
