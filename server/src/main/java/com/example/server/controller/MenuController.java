package com.example.server.controller;



import com.example.server.model.Menu;
import com.example.server.model.User;
import com.example.server.service.MenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus(){
        List<Menu> menus = menuService.findAllMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Menu> addUser(@RequestBody Menu menus) {
        Menu newMenu = menuService.addMenu(menus);
        return new ResponseEntity<>(newMenu , HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<Menu> updateUser(@RequestBody Menu menus) {
        Menu updateMenu=  menuService.addMenu(menus);
        return new ResponseEntity<>(updateMenu, HttpStatus.OK);
    }
}







