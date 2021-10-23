package com.example.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Restaurant {
    @Id
    private int restaurantId;
    private String name;
    private String plz;
    private String stadt;
    private double mbw;
    private double lieferkosten;
    private int lieferbereich;
    private int speisenId;

    public Restaurant(String name, String plz, String stadt, double mbw, double lieferkosten, int lieferbereich) {
        this.name = name;
        this.plz = plz;
        this.stadt = stadt;
        this.mbw = mbw;
        this.lieferkosten = lieferkosten;
        this.lieferbereich = lieferbereich;
    }

    public Restaurant() {}

    public Restaurant(int restaurantId, String name, String plz, String stadt, double mbw, double lieferkosten, int lieferbereich) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.plz = plz;
        this.stadt = stadt;
        this.mbw = mbw;
        this.lieferkosten = lieferkosten;
        this.lieferbereich = lieferbereich;
    }

    public Restaurant(String name, String plz, String stadt, double mbw, double lieferkosten, int lieferbereich, int speisenId) {
        this.name = name;
        this.plz = plz;
        this.stadt = stadt;
        this.mbw = mbw;
        this.lieferkosten = lieferkosten;
        this.lieferbereich = lieferbereich;
        this.speisenId = speisenId;
    }

    public Restaurant(int restaurantId, String name, String plz, String stadt, double mbw, double lieferkosten, int lieferbereich, int speisenId) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.plz = plz;
        this.stadt = stadt;
        this.mbw = mbw;
        this.lieferkosten = lieferkosten;
        this.lieferbereich = lieferbereich;
        this.speisenId = speisenId;
    }



    public int getRestaurantId() { return restaurantId; }

    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPlz() { return plz; }

    public void setPlz(String plz) { this.plz = plz; }

    public String getStadt() { return stadt; }

    public void setStadt(String stadt) { this.stadt = stadt; }

    public double getMbw() { return mbw; }

    public void setMbw(double mbw) { this.mbw = mbw; }

    public double getLieferkosten() { return lieferkosten; }

    public void setLieferkosten(double lieferkosten) { this.lieferkosten = lieferkosten; }

    public int getLieferbereich() { return lieferbereich; }

    public void setLieferbereich(int lieferbereich) { this.lieferbereich = lieferbereich; }

    public int getSpeisenId() { return speisenId; }

    public void setSpeisenId(int speisenId) { this.speisenId = speisenId; }
}
