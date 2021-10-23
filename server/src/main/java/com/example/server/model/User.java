package com.example.server.model;

import javax.persistence.*;
import java.io.Serializable;
//Datenbank table für dem User

@Entity
public class User implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String email;
    private String password;
    private String straße;
    private String plz;
    private String stadt;
    private Boolean restaurantBesitzer;

    private int restaurantId;
    private int verfiyCode;



    public User(int userId, String email, String password, String straße, String plz, String stadt) {
        this.email = email;
        this.password = password;
        this.straße = straße;
        this.plz = plz;
        this.stadt = stadt;
    }


    public User(int userId, String email, String password, String straße, String plz, String stadt, int restaurantId) {
        this.email = email;
        this.password = password;
        this.straße = straße;
        this.plz = plz;
        this.stadt = stadt;
        this.restaurantId = restaurantId;
    }

    public User() {

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

    public String getStraße() {
        return straße;
    }

    public void setStraße(String straße) {
        this.straße = straße;
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

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}

    public int getVerfiyCode() {return verfiyCode;}

    public void setVerfiyCode(int verfiyCode) {this.verfiyCode = verfiyCode;}

}
