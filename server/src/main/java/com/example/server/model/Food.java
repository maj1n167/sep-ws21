package com.example.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.*;
import java.io.Serializable;

@Entity
public class Food  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;
    private double preis;
    private String name;
    private String beschreibung;

    private Long kategorieId;
    private Long menuId;



    public Food(int foodId, double preis, String name, String beschreibung) {
        this.foodId = foodId;
        this.preis = preis;
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public Food(int foodId, Long kategorieId, double preis, String name, String beschreibung) {
        this.foodId = foodId;
        this.preis = preis;
        this.name = name;
        this.beschreibung = beschreibung;
        this.kategorieId = kategorieId;
    }

    public Food() {

    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getKategorieId() {
        return kategorieId;
    }

    public void setKategorieId(Long kategorieId) {
        this.kategorieId = kategorieId;
    }


    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }


    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

}
