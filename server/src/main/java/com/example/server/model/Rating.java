package com.example.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int restaurantId;
    int starsLieferung;
    int starsFood;
    String comment;
    String name;

    public Rating(int restaurantId, int starsLieferung, int starsFood, String comment, String name) {
        this.restaurantId = restaurantId;
        this.starsLieferung = starsLieferung;
        this.starsFood = starsFood;
        this.comment = comment;
        this.name = name;
    }

    public Rating() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getStarsLieferung() {
        return starsLieferung;
    }

    public void setStarsLieferung(int starsLieferung) {
        this.starsLieferung = starsLieferung;
    }

    public int getStarsFood() {
        return starsFood;
    }

    public void setStarsFood(int starsFood) {
        this.starsFood = starsFood;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setName(String name) {
        this.name = name;
    }
}
