package ude.sep.repo;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.Assert;
import ude.sep.model.BestellFood;
import ude.sep.model.BestellHistorie;
import ude.sep.model.Bestellungen;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BestellHisRepoTest {

    @Autowired
    private BestellHisRepo underTest;

    private List<BestellFood> liste1;
    private List<Bestellungen> bestellungenList;
    private BestellFood bestellFoodInto;

    @BeforeTestClass
    public void createBestellFood() {
        bestellFoodInto = new BestellFood(1, 1, 10.0,
                "Big Mac", "Mit Cheese", "URL1", 1L,
                2L, "14.12.2021");
        System.out.println("Start");
    }
    @BeforeEach
    public void vorJedemTest(){
        System.out.println("Vor Test");
    }
    @Test
    public void derTest1(){
        //Testfall 1:Prüfung ob Datum übereinstimmt.
        System.out.println("Test1");
        assertEquals("14.12.2021",bestellFoodInto.getDate());
    }
    @Test
    public void derTest2(){
        //Testfall 2:Prüfung, ob Namen übereinstimmen.
    }

    @Test
    public void derTest3(){
        //Testfall 3: Prüfung, ob bestellte Gerichte übereinstimmen.
    }
    @Test
    public void derTest4(){
        //Testfall 4:Prüfung, ob Summe übereinstimmt.
    }




}