package com.example.server.model;

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
    private String plz;
    private String stadt;
    private Boolean restaurantBesitzer;
    private int treuepunkte;
    private Double guthaben;
    private String altAdresse;
    private String altPlz;
    private String altStadt;

    @OneToOne
    @JoinColumn(name="restaurantId" ,referencedColumnName = "restaurantId")
    private Restaurant restaurant;

    private int verifyCode;


    //konstruktor zur Registrierung - ok


    public User(int userId, String name, String vorname, String email, String password, String strasse, String plz, String stadt
                , String altAdresse,
                String altPlz, String altStadt) {
        this.userId = userId;
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.password = password;
        this.strasse = strasse;
        this.plz = plz;
        this.stadt = stadt;
        this.restaurantBesitzer = false;
        this.guthaben = 0.0;
        this.altAdresse = altAdresse;
        this.altPlz = altPlz;
        this.altStadt = altStadt;

    }

    //Konstruktor zur Erstellung des Restaurants - ok
    public User(int userId, String name, String vorname, String email, String password, String strasse, String plz, String stadt, Boolean restaurantBesitzer, int restaurantId) {
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.password = password;
        this.strasse = strasse;
        this.plz = plz;
        this.stadt = stadt;
        this.restaurantBesitzer = restaurantBesitzer;
    }

    public User() {

    }
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

}
