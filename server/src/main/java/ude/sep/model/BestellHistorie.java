package ude.sep.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class BestellHistorie {

    @Id
    int bestellHisId;

    @OneToMany(cascade= CascadeType.ALL)
    List<Bestellungen> bestellungenList;


    public BestellHistorie(int bestellHisId, List<Bestellungen> bestellungenList) {
        this.bestellHisId = bestellHisId;
        this.bestellungenList = bestellungenList;
    }

    public BestellHistorie() {

    }

    public int getBestellHisId() {
        return bestellHisId;
    }

    public void setBestellHisId(int bestellHisId) {
        this.bestellHisId = bestellHisId;
    }

    public List<Bestellungen> getBestellungenList() {
        return bestellungenList;
    }

    public void setBestellungenList(List<Bestellungen> bestellungenList) {
        this.bestellungenList = bestellungenList;
    }
}
