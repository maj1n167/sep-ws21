package ude.sep.server.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestellHistorieTest {

    @Test
    void getBestellungenList() {
        //DAMit der Test erfolgreich ist, muss die Bestellung in der abgeschlossenen
        //Bestellhistorie angezeigt.
        Bestellungen neu= new Bestellungen();
        BestellHistorie b= new BestellHistorie();
        assertEquals(b,neu.datum);
        assertEquals(b,neu.restaurantId);
        assertEquals(b,neu.getListe());
        assertEquals(b,neu.summe);

    }

    @Test
    void setBestellungenList() {
    }
}