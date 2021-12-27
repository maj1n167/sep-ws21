package ude.sep.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class ResBestellungen {
    // Muss beim Restaurant erstellen erstellt werden

    @Id
    private int resBesId;

    @OneToMany(cascade= CascadeType.ALL)
    private List<Bestellungen> bestellungenList;

    public ResBestellungen(int resBesId, List<Bestellungen> bestellungenList) {
        this.resBesId = resBesId;
        this.bestellungenList = bestellungenList;
    }

    public ResBestellungen(List<Bestellungen> bestellungenList) {
        this.bestellungenList = bestellungenList;
    }

    public ResBestellungen() {
    }

    public int getResBesId() {
        return resBesId;
    }

    public void setResBesId(int resBesId) {
        this.resBesId = resBesId;
    }

    public List<Bestellungen> getBestellungenList() {
        return bestellungenList;
    }

    public void setBestellungenList(List<Bestellungen> bestellungenList) {
        this.bestellungenList = bestellungenList;
    }
}


