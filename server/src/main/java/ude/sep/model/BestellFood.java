package ude.sep.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class BestellFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bestellfoodid;


    private int foodId;
    private double preis;
    private String name;
    private String beschreibung;
    private String url;
    private Long kategorieId;
    private Long menuId;
    private String date;

    public BestellFood(int bestellfoodid, int foodId, double preis, String name, String beschreibung, String url, Long kategorieId, Long menuId, String date) {
        this.bestellfoodid = bestellfoodid;
        this.foodId = foodId;
        this.preis = preis;
        this.name = name;
        this.beschreibung = beschreibung;
        this.url = url;
        this.kategorieId = kategorieId;
        this.menuId = menuId;
        this.date = date;
    }
    public BestellFood() {}


        public int getBestellfoodid () {
            return bestellfoodid;
        }

        public void setBestellfoodid ( int bestellfoodid){
            bestellfoodid = bestellfoodid;
        }

        public int getFoodId () {
            return foodId;
        }

        public void setFoodId ( int foodId){
            this.foodId = foodId;
        }

        public double getPreis () {
            return preis;
        }

        public void setPreis ( double preis){
            this.preis = preis;
        }

        public String getName () {
            return name;
        }

        public void setName (String name){
            this.name = name;
        }

        public String getBeschreibung () {
            return beschreibung;
        }

        public void setBeschreibung (String beschreibung){
            this.beschreibung = beschreibung;
        }

        public String getUrl () {
            return url;
        }

        public void setUrl (String url){
            this.url = url;
        }

        public Long getKategorieId () {
            return kategorieId;
        }

        public void setKategorieId (Long kategorieId){
            this.kategorieId = kategorieId;
        }

        public Long getMenuId () {
            return menuId;
        }

        public void setMenuId (Long menuId){
            this.menuId = menuId;
        }

        public String getDate () {
            return date;
        }

        public void setDate (String date){
            this.date = date;
        }

}
