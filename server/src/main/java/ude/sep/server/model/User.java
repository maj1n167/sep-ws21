package ude.sep.server.model;

import javax.persistence.*;
import java.io.Serializable;
//Datenbank Table für den User

@Entity
public class User implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    private String vorname;
    private String email;
    private String password;
    private String strasse;
    private String nummer;
    private String plz;
    private String stadt;
    private String geburtsdatum;
    private Boolean restaurantBesitzer;
    private int treuepunkte;
    private Double guthaben;
    private String altAdresse;
    private String altNummer;
    private String altPlz;
    private String altStadt;

    @OneToOne
    @JoinColumn(name="restaurantId" ,referencedColumnName = "restaurantId")
    private Restaurant restaurant;

    private int verifyCode;

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean getRestaurantBesitzer() {
        return restaurantBesitzer;
    }

    public void setRestaurantBesitzer(Boolean restaurantBesitzer) {
        this.restaurantBesitzer = restaurantBesitzer;
    }

    public String getNummer() {return nummer;}

    public void setNummer(String nummer) {this.nummer = nummer;}

    public Restaurant getRestaurant() {return restaurant;}

    public void setRestaurant(Restaurant restaurant) {this.restaurant = restaurant;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getVerifyCode() {return verifyCode;}

    public void setVerifyCode(int verifyCode) {this.verifyCode = verifyCode;}

    public Double getGuthaben() {
        return guthaben;
    }

    public void setGuthaben(Double guthaben) {
        this.guthaben = guthaben;
    }

    public String getAltAdresse() {
        return altAdresse;
    }

    public void setAltAdresse(String altAdresse) {
        this.altAdresse = altAdresse;
    }

    public String getAltPlz() {
        return altPlz;
    }

    public void setAltPlz(String altPlz) {
        this.altPlz = altPlz;
    }

    public String getAltStadt() {
        return altStadt;
    }

    public void setAltStadt(String altStadt) {
        this.altStadt = altStadt;
    }

    public int getTreuepunkte() { return treuepunkte; }

    public void setTreuepunkte(int treuepunkte) { this.treuepunkte = treuepunkte; }

    public String getAltNummer() {return altNummer;}

    public void setAltNummer(String altNummer) {this.altNummer = altNummer;}

    public String getGeburtsdatum() {return geburtsdatum;}

    public void setGeburtsdatum(String geburtsdatum) {this.geburtsdatum = geburtsdatum;}
}
