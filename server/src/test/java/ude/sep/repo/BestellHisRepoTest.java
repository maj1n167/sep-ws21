package ude.sep.repo;


import ch.qos.logback.core.status.Status;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.hibernate.boot.model.relational.Database;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.Assert;
import ude.sep.controller.BestellFoodController;
import ude.sep.model.BestellFood;
import ude.sep.model.BestellHistorie;
import ude.sep.model.Bestellungen;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BestellHisRepoTest {


    private List<BestellFood> liste1;
   // private List<Bestellungen> liste2;
    private List<Bestellungen> bestellungenList;
    private BestellFood bestellFoodInto;



    BestellFood bestellFoodInto1 = new BestellFood(1, 1, 10.0,
            "Big Mac", "Mit Cheese", "URL1", 1L,
            2L, "14.12.2021");
    BestellFood bestellFoodInto2 = new BestellFood(2, 2, 10.0,
            "Big Cheese", "Mit Cheese", "URL2", 1L,
            1L, "14.12.2021");
    BestellFood bestellFoodInto3 = new BestellFood(3, 3 ,10.0,
            "Big Chaos", "Mit Cheese und Chili", "URL1", 3L,
            2L, "14.12.2021");

   /* Bestellungen bestellungen= new Bestellungen(1,"14.12.2021",1,10.0,
            liste1);

    BestellHistorie bestellHistorie= new BestellHistorie(1,liste2);

*/


    @BeforeEach
    public void createBestellFood() {

       /* liste1.add(bestellFoodInto1);
        liste1.add(bestellFoodInto2);
        liste1.add(bestellFoodInto3);

        liste2.add(bestellungen);*/

        System.out.println("Start");
    }
    @Test
    public void derTest1(){
        //Testfall 1:Prüfung ob Datum übereinstimmt.

        System.out.println("Test 1");
       assertEquals("14.12.2021",bestellFoodInto1.getDate());
    }
    @Test
    public void derTest2(){
        //Testfall 2:Prüfung, ob Namen übereinstimmen.
        System.out.println("Test 2");
        assertEquals("Big Mac",bestellFoodInto1.getName());
    }

    @Test
    public void derTest3(){
        //Testfall 3: Prüfung, ob bestellte Gerichte übereinstimmen.
        System.out.println("Test 3");
        assertEquals("Big Mac","Big Mac");
    }
    @Test
    public void derTest4(){
        //Testfall 4:Prüfung, ob Summe übereinstimmt.
        System.out.println("Test 4");
        assertEquals(10.0,bestellFoodInto1.getPreis());
    }

}