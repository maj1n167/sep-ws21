package ude.sep.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fav {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int restaurantId;
    int favOf;

    public Fav() {}

    public Fav(int favOf, int id) {
        this.restaurantId=id;
        this.favOf=favOf;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public int getFavOf() {return favOf;}

    public void setFavOf(int favOf) {this.favOf = favOf;}

    public int getRestaurantId() {return restaurantId;}

    public void setRestaurantId(int restaurantId) {this.restaurantId = restaurantId;}
}
