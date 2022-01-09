package ude.sep.server.model;

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
    List<WarenFood> foodList;


    public Warenkorb(int warenkorbId, double summe, List<WarenFood> foodList) {
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

    public List<WarenFood> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<WarenFood> foodList) {
        this.foodList = foodList;
    }
}
