package com.example.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Food implements Serializable {
    @Id
    private int foodId;
    private int kategorie;
    private double preis;
    private String name;
    private String beschreibung;
    private String url;
    private int foodOf;

}
