package com.example.server.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Restaurant  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restaurantId;
    private String name;
    private String strasse;
    private String plz;
    private String stadt;
    private double mbw;
    private double lieferkosten;
    private String kategorie;
    private int lieferbereich;
    @JoinColumn(name = "menuId" ,referencedColumnName = "menuId")
    @OneToOne
    private Menu menu;



    public Restaurant(int restaurantId, String name, String plz, String stadt, double mbw, double lieferkosten, int lieferbereich) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.plz = plz;
        this.stadt = stadt;
        this.mbw = mbw;
        this.lieferkosten = lieferkosten;
        this.lieferbereich = lieferbereich;
    }

    public Restaurant() {

    }

    public Restaurant(String name, String strasse, String plz, String stadt, double mbw, double lieferkosten, String kategorie, int lieferbereich) {
        this.name = name;
        this.strasse = strasse;
        this.plz = plz;
        this.stadt = stadt;
        this.mbw = mbw;
        this.lieferkosten = lieferkosten;
        this.kategorie = kategorie;
        this.lieferbereich = lieferbereich;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public double getMbw() {
        return mbw;
    }

    public void setMbw(double mbw) {
        this.mbw = mbw;
    }

    public double getLieferkosten() {
        return lieferkosten;
    }

    public void setLieferkosten(double lieferkosten) {
        this.lieferkosten = lieferkosten;
    }

    public int getLieferbereich() {
        return lieferbereich;
    }

    public void setLieferbereich(int lieferbereich) {
        this.lieferbereich = lieferbereich;
    }
}
