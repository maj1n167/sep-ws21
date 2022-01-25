import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ude.sep.client.controller.ConnectionController;
import ude.sep.client.controller.LoginController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaleTest extends ConnectionController {
    public static JSONObject b1 = new JSONObject();
    public static JSONObject b2 = new JSONObject();
    public static JSONObject b3 = new JSONObject();
    public static JSONObject b4 = new JSONObject();

    @BeforeAll
    public static void beforeAll() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        b1.put("start", "01.01.1970");
        b1.put("end", "01.01.1970");

        b2.put("start", LocalDate.now().format(dtf));
        b2.put("end", LocalDate.now().format(dtf));
        b3.put("start", LocalDate.now().format(dtf));
        b3.put("end", "31.12.2022");
        b4.put("start", "01.01.2022");
        b4.put("end", LocalDate.now().format(dtf));
    }

    @Test
    //AvgBewertung ausgeben lassen
    public void aktionTest() throws IOException {
        //test 1
        boolean ergebnis = hasPromotionTest(b1);

        assertEquals(false, ergebnis);

        System.out.println("SaleTest1: \nErwarteter Wert \"false\", berechneter Wert: " + ergebnis);

        //test 2
        ergebnis = hasPromotionTest(b2);

        assertEquals(true, ergebnis);

        System.out.println("SaleTest2: \nErwarteter Wert \"TRUE\", berechneter Wert: " + ergebnis);

        //test 3
        ergebnis = hasPromotionTest(b3);

        assertEquals(true, ergebnis);

        System.out.println("SaleTest3: \nErwarteter Wert \"TRUE\", berechneter Wert: " + ergebnis);

        //test 4
        ergebnis = hasPromotionTest(b4);

        assertEquals(true, ergebnis);

        System.out.println("SaleTest4: \nErwarteter Wert \"TRUE\", berechneter Wert: " + ergebnis);
    }
}
