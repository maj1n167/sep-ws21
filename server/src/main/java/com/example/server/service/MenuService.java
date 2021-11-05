package com.example.server.service;

import com.example.server.model.Food;
import com.example.server.model.Menu;
import com.example.server.repo.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteFood(int menuId){
        menuRepo.deleteById(menuId);
    }



}
