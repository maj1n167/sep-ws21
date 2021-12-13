package ude.sep.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ude.sep.model.Menu;
import ude.sep.service.MenuService;

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

    @DeleteMapping("/delete/{menuId}")
    public ResponseEntity<?> deleteMenu(@PathVariable("menuId") int menuId){
        menuService.deleteMenu(menuId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Menu> getMenuId(@PathVariable("id") int id) {
        List<Menu> menus = menuService.findAllMenus();
        Menu menu = null;
        for (int i = 0; i < menus.size(); i++) {
            if (menus.get(i).getMenuId() == id) {
                menu = menus.get(i);
            }
        }
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
}







