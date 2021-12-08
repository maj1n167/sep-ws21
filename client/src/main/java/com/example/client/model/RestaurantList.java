package com.example.client.model;

public class RestaurantList {
    private int restaurantId;
    private String name;
    private String strasse;
    private String plz;
    private String stadt;
    private double mbw;
    private double lieferkosten;
    private String kategorie;
    private int lieferbereich;
    private double rating;

    public RestaurantList() { }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

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

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
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

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public int getLieferbereich() {
        return lieferbereich;
    }

    public void setLieferbereich(int lieferbereich) {
        this.lieferbereich = lieferbereich;
    }
}
