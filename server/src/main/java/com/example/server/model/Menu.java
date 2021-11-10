package com.example.server.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Menu implements Serializable {


    @Id
    private long menuId;
    @OneToMany
    @JoinColumn(name = "foodId")
    private List<Food> foods;



    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }


    public Menu(long menuId, String name, String beschreibung, String kategorie, double preis, String bild) {
        this.menuId = menuId;
    }

    public Menu() {

    }
}
