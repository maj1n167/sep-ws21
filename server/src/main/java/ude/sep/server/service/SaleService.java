package ude.sep.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.server.model.Sale;
import ude.sep.server.repo.SaleRepo;
import java.util.List;

@Service
public class SaleService {
    private final SaleRepo saleRepo;

    @Autowired
    public SaleService(SaleRepo saleRepo) {this.saleRepo = saleRepo;}

    public Sale addSale(Sale sale) {return saleRepo.save(sale);}

    public List<Sale> findAllSalesOf(int rId) {return saleRepo.findAllByRestaurantId(rId);}

    public void delSale(int id) {saleRepo.deleteById(id);}

    public List<Sale> findAllSales() {return saleRepo.findAll();}

}

