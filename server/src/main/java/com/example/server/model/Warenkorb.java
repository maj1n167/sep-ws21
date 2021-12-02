package com.example.server.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
public class Warenkorb implements Serializable {



    @Id
    int warenkorbId;
    double summe;
    @OneToMany
    List<Food> foodList;


    public Warenkorb(int warenkorbId, double summe, List<Food> foodList) {
        this.warenkorbId = warenkorbId;
        this.summe = summe;
        this.foodList = foodList;
    }

    public Warenkorb() {

    }

    public long getWarenkorbId() {
        return warenkorbId;
    }

    public void setWarenkorbId(int warenkorbId) {
        this.warenkorbId = warenkorbId;
    }

    public double getSumme() {
        return summe;
    }

    public void setSumme(double summe) {
        this.summe = summe;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
