package ude.sep.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ude.sep.server.model.Sale;

import java.util.List;

public interface SaleRepo extends JpaRepository<Sale, Integer> {
    void deleteById(int id);
    List<Sale> findAllByRestaurantId(int rId);
}