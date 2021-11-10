package com.example.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Food  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int foodId;

    private String kategorie;

    private double preis;
    private String name;
    private String beschreibung;
    private String url;
    private int menuId;

    public Food(int foodId, String kategorie, double preis, String name, String beschreibung, String url, int menuId) {
        this.foodId = foodId;
        this.kategorie = kategorie;
        this.preis = preis;
        this.name = name;
        this.beschreibung = beschreibung;
        this.url = url;
        this.menuId = menuId;
    }

    public Food(int foodId, String kategorie, double preis, String name, String beschreibung, String url) {
        this.foodId = foodId;
        this.kategorie = kategorie;
        this.preis = preis;
        this.name = name;
        this.beschreibung = beschreibung;
        this.url = url;
    }

    public Food() {

    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
}
