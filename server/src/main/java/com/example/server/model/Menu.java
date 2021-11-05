package com.example.server.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Menu implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long menuId;

    private String name;

    private String beschreibung;

    private String kategorie;

    private double Preis;

    private String bild;

    @OneToMany
    @JoinColumn(name = "foodId")
    private List<Food> food;

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
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

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public double getPreis() {
        return Preis;
    }

    public void setPreis(double preis) {
        Preis = preis;
    }

    public String getBild() {
        return bild;
    }

    public void setBild(String bild) {
        this.bild = bild;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }


    public Menu(long menuId, String name, String beschreibung, String kategorie, double preis, String bild) {
        this.menuId = menuId;
        this.name = name;
        this.beschreibung = beschreibung;
        this.kategorie = kategorie;
        Preis = preis;
        this.bild = bild;

    }

    public Menu() {

    }
}
