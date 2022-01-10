package ude.sep.server.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Restaurant  implements Serializable {
    @Id
    private int restaurantId;
    private String name;
    private String strasse;
    private String nummer;
    private String plz;
    private String stadt;
    private double mbw;
    private double lieferkosten;
    private String kategorie;
    private int lieferbereich;
    private double ratingDelivery;
    private double ratingFood;
    @JoinColumn(name = "menuId")
    @OneToOne
    private Menu menu;


    public Restaurant() {
    }

    public Restaurant(int restaurantId, String name, String strasse, String plz, String stadt, double mbw, double lieferkosten, String kategorie, int lieferbereich, User owner) {
        this.restaurantId = restaurantId;
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

    public String getStrasse() {return strasse;}

    public void setStrasse(String strasse) {this.strasse = strasse;}

    public String getKategorie() { return kategorie; }

    public void setKategorie(String kategorie) { this.kategorie = kategorie; }

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

    public double getRatingDelivery() {return ratingDelivery;}

    public void setRatingDelivery(double ratingDelivery) {this.ratingDelivery = ratingDelivery;}

    public double getRatingFood() {return ratingFood;}

    public void setRatingFood(double ratingFood) {this.ratingFood = ratingFood;}

    public Menu getMenu() {return menu;}

    public void setMenu(Menu menu) {this.menu = menu;}

    public String getNummer() {return nummer;}

    public void setNummer(String nummer) {this.nummer = nummer;}
}
