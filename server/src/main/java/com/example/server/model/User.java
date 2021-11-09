package com.example.server.model;

import javax.persistence.*;
import java.io.Serializable;
//Datenbank Table für den User

@Entity
public class User implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String name;
    private String vorname;
    private String email;
    private String password;
    private String strasse;
    private String plz;
    private String stadt;
    private Boolean restaurantBesitzer;

    @OneToOne
    @JoinColumn(name="restaurantId")
    private Restaurant restaurant;

    private int verfiyCode;



    public User(int userId,String name, String vorname, String email, String password, String strasse, String plz, String stadt) {
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.password = password;
        this.strasse = strasse;
        this.plz = plz;
        this.stadt = stadt;
    }


    public User(int userId, String name, String vorname, String email, String password, String strasse, String plz, String stadt, int restaurantId) {
        this.name = name;
        this.vorname = vorname;
        this.email = email;
        this.password = password;
        this.strasse = strasse;
        this.plz = plz;
        this.stadt = stadt;
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

    public int getVerfiyCode() {return verfiyCode;}

    public void setVerfiyCode(int verfiyCode) {this.verfiyCode = verfiyCode;}

}
