package ude.sep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ude.sep.model.Menu;
import ude.sep.repo.MenuRepo;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepo menuRepo;


    @Autowired
    public MenuService(MenuRepo menuRepo) {
        this.menuRepo = menuRepo;
    }

    public Menu addMenu(Menu menu){
        return menuRepo.save(menu);
    }

    public List<Menu> findAllMenus(){
        return menuRepo.findAll();
    }

    public Menu updateMenu(Menu menu){
        return menuRepo.save(menu);
    }

    public void deleteMenu(int menuId){
        menuRepo.deleteById(menuId);
    }



}
