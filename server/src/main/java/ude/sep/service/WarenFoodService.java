package ude.sep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.model.WarenFood;
import ude.sep.repo.WarenFoodRepo;

import java.util.List;
@Service
public class WarenFoodService {
    private final WarenFoodRepo warenFoodRepo;

    @Autowired
    public WarenFoodService(WarenFoodRepo warenFoodRepo){this.warenFoodRepo = warenFoodRepo;}
    public WarenFood addWarenFood(WarenFood warenFood){return warenFoodRepo.save(warenFood);}
    public List<WarenFood> findAllWarenFood(){return warenFoodRepo.findAll();}
    public void deleteWarenFood(int warenfoodId){warenFoodRepo.deleteById(warenfoodId);}

}
