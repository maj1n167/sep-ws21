package com.example.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class WarenFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bestellfoodid;


    private int warenId;
    private double preis;
    private String name;
    private String beschreibung;
    private String url;
    private Long kategorieId;
    private Long menuId;
    private int warenid;

    public WarenFood(int bestellfoodid, int warenId, double preis, String name, String beschreibung, String url, Long kategorieId, Long menuId, int warenid) {
        this.bestellfoodid = bestellfoodid;
        this.warenId = warenId;
        this.preis = preis;
        this.name = name;
        this.beschreibung = beschreibung;
        this.url = url;
        this.kategorieId = kategorieId;
        this.menuId = menuId;
        this.warenid = warenid;
    }

    public WarenFood() {

    }

    public int getBestellfoodid() {
        return bestellfoodid;
    }

    public void setBestellfoodid(int bestellfoodid) {
        this.bestellfoodid = bestellfoodid;
    }

    public int getWarenId() {
        return warenId;
    }

    public void setWarenId(int warenId) {
        this.warenId = warenId;
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

    public Long getKategorieId() {
        return kategorieId;
    }

    public void setKategorieId(Long kategorieId) {
        this.kategorieId = kategorieId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public int getWarenid() {
        return warenid;
    }

    public void setWarenid(int warenid) {
        this.warenid = warenid;
    }
}
