package ude.sep.model;

import javax.persistence.*;
import java.io.Serializable;
import java.net.DatagramSocket;
import java.util.Date;
import java.util.List;

@Entity
public class Bestellungen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long bestellungId;

    String datum;

    int restaurantId;

     double summe;

     int userId;

     String sonderwunsch;

    @OneToMany(cascade = CascadeType.ALL)
    List<BestellFood> liste;

    public Bestellungen(long bestellungId, String datum, int restaurantId, double summe, int userId, List<BestellFood> liste) {
        this.bestellungId = bestellungId;
        this.datum = datum;
        this.restaurantId = restaurantId;
        this.summe = summe;
        this.userId = userId;
        this.liste = liste;
    }

    public Bestellungen(long bestellungId, String datum, int restaurantId, double summe, List<BestellFood> liste) {
        this.bestellungId = bestellungId;
        this.datum = datum;
        this.restaurantId = restaurantId;
        this.summe = summe;
        this.liste = liste;
    }

    public Bestellungen(long bestellungId, String datum, int restaurantId, double summe, int userId, String sonderwunsch, List<BestellFood> liste) {
        this.bestellungId = bestellungId;
        this.datum = datum;
        this.restaurantId = restaurantId;
        this.summe = summe;
        this.userId = userId;
        this.sonderwunsch = sonderwunsch;
        this.liste = liste;
    }

    public Bestellungen(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getBestellungId() {
        return bestellungId;
    }

    public void setBestellungId(long bestellungId) {
        this.bestellungId = bestellungId;
    }

    public String getDatum() {


        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getSumme() {
        return summe;
    }

    public void setSumme(double summe) {
        this.summe = summe;
    }

    public List<BestellFood> getListe() {
        return liste;
    }

    public void setListe(List<BestellFood> liste) {
        this.liste = liste;
    }

    public String getSonderwunsch() {
        return sonderwunsch;
    }

    public void setSonderwunsch(String sonderwunsch) {
        this.sonderwunsch = sonderwunsch;
    }
}
