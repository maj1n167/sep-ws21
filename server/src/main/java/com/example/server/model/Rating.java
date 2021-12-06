package com.example.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rating {
    @Id
    int id;
    int starsLieferung;
    int starsFood;
    String comment;
}
