package com.example.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fav {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int favOf;

    public Fav() {}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getFavOf() {return favOf;}

    public void setFavOf(int favOf) {this.favOf = favOf;}
}
