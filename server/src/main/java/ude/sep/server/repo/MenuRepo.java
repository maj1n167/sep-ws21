package ude.sep.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.Menu;

public interface MenuRepo extends JpaRepository<Menu,Integer> {
}
