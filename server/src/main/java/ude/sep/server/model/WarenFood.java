package ude.sep.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class WarenFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bestellfoodid;
    private double preis;
    private String name;
    private String beschreibung;
    private String url;
    private Long kategorieId;
    private Long menuId;
    private int warenFId;

    public WarenFood(int bestellfoodid, double preis, String name, String beschreibung, String url, Long kategorieId, Long menuId, int warenFId, String date) {
        this.bestellfoodid = bestellfoodid;
        this.preis = preis;
        this.name = name;
        this.beschreibung = beschreibung;
        this.url = url;
        this.kategorieId = kategorieId;
        this.menuId = menuId;
        this.warenFId = warenFId;
        this.date = date;
    }

    private String date;
    public WarenFood(int bestellfoodid, double preis, String name, String beschreibung, String url, Long kategorieId, Long menuId, int warenid) {
        this.bestellfoodid = bestellfoodid;

        this.preis = preis;
        this.name = name;
        this.beschreibung = beschreibung;
        this.url = url;
        this.kategorieId = kategorieId;
        this.menuId = menuId;
        this.warenFId = warenid;
    }

    public WarenFood() {

    }

    public int getWarenFId() {
        return warenFId;
    }

    public void setWarenFId(int warenFId) {
        this.warenFId = warenFId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBestellfoodid() {
        return bestellfoodid;
    }

    public void setBestellfoodid(int bestellfoodid) {
        this.bestellfoodid = bestellfoodid;
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
        return warenFId;
    }

    public void setWarenid(int warenid) {
        this.warenFId = warenid;
    }
}
