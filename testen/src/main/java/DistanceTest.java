import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ude.sep.client.controller.ConnectionController;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DistanceTest extends ConnectionController {

    public static JSONObject b1 = new JSONObject();
    public static JSONObject b2 = new JSONObject();
    public static JSONObject b3 = new JSONObject();

    @BeforeAll
    public static void beforeAll() {

        //Adresse hinzufügen
        b1.put("address1", "Hermannstr. 9, 45327 Essen");
        b1.put("address2", "Schuetzenbahn 70, 45127 Essen");


        //Adresse hinzufügen
        b2.put("address1", "Hermannstr. 9, 45327 Essen");
        b2.put("address2", "Universitaetsstr. 2, 45141 Essen");


        //Adresse hinzufügen
        b3.put("address1", "Hermannstr. 9, 45327 Essen");
        b3.put("address2", "Hoeltestr. 10, 45326 Essen");
    }

    @Test
    //AvgBewertung ausgeben lassen
    public void avgTest() throws IOException {
        //test 1
        int ergebnis = lookUpDistanceTest(b1);

        assertEquals(6094, ergebnis);

        System.out.println("DistanceTest1: \nErwarteter Wert \"6094\", berechneter Wert: " + ergebnis);

        //test 1
        ergebnis = lookUpDistanceTest(b2);

        assertEquals(6641, ergebnis);

        System.out.println("DistanceTest2: \nErwarteter Wert \"6641\", berechneter Wert: " + ergebnis);

        //test 1
        ergebnis = lookUpDistanceTest(b3);

        assertEquals(4737, ergebnis);

        System.out.println("DistanceTest3: \nErwarteter Wert \"4737\", berechneter Wert: " + ergebnis);
    }
}













