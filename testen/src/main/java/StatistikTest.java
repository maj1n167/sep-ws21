import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ude.sep.client.controller.ConnectionController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class StatistikTest extends ConnectionController {
    public static JSONArray menu = new JSONArray();
    public static JSONArray bestellung = new JSONArray();
    public static JSONObject b1 = new JSONObject();
    public static JSONObject b2 = new JSONObject();
    public static JSONObject b3 = new JSONObject();
    public static JSONObject b4 = new JSONObject();


    @BeforeAll
    public static void beforeAll() {
        //Speise hinzufügen
        b1.put("foodId", "1");
        b1.put("name","Funghi");

        //Speise hinzufügen
        b2.put("foodId", "2");
        b2.put("name","Spaghetti");

        //Speise hinzufügen
        b3.put("foodId", "3");
        b3.put("name","Chicken Masala");

        // Speise hinzufügen
        b4.put("foodId","4");
        b4.put("name","Tonno");

        //Alle Speisen generieren
        menu.put(b1);
        menu.put(b2);
        menu.put(b3);
        menu.put(b4);

        // bestellung 1 füllen
        for (int i=0;i<5;i++){
            bestellung.put(b1);
        }
        for (int i=0;i<10;i++){
            bestellung.put(b2);
        }
        for (int i=0;i<15;i++){
            bestellung.put(b3);
        }
        for (int i=0;i<20;i++){
            bestellung.put(b4);
        }

    }

    @Test
    public  void getAlleSpeisenTest() throws IOException{
        JSONArray statistik= getStatistik(menu,bestellung);

        System.out.println(statistik);
        assertEquals(5,statistik.getJSONObject(0).getInt("count"));
        assertEquals(10,statistik.getJSONObject(1).getInt("count"));
        assertEquals(15,statistik.getJSONObject(2).getInt("count"));
        assertEquals(20,statistik.getJSONObject(3).getInt("count"));


        System.out.println("getAlleSpeisenTest Test1: \nErwarteter Wert \"5\", berechneter Wert: "+statistik.getJSONObject(0).getInt("count"));
        System.out.println("getAlleSpeisenTest Test1: \nErwarteter Wert \"10\", berechneter Wert: "+statistik.getJSONObject(1).getInt("count"));
        System.out.println("getAlleSpeisenTest Test1: \nErwarteter Wert \"15\", berechneter Wert: "+statistik.getJSONObject(2).getInt("count"));
        System.out.println("getAlleSpeisenTest Test1: \nErwarteter Wert \"20\", berechneter Wert: "+statistik.getJSONObject(3).getInt("count"));

    }

}