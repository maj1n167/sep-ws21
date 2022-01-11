package ude.sep.server.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String start;
    String end;
    int restaurantId;

    public Sale(int rId, String start, String end) {
        this.start = start;
        this.end = end;
        this.restaurantId = rId;
    }

    public Sale() {}


    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getStart() {return start;}

    public int getRestaurantId() {return restaurantId;}

    public void setRestaurantId(int rId) {this.restaurantId = rId;}

    public void setStart(String start) {this.start = start;}

    public String getEnd() {return end;}

    public void setEnd(String end) {this.end = end;}

}