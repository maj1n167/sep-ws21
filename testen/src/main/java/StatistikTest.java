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


public class StatistikTest extends ConnectionController {
    public static JSONArray ball = new JSONArray();
    public static JSONObject b1 = new JSONObject();
    public static JSONObject b2 = new JSONObject();
    public static JSONObject b3 = new JSONObject();
    public static JSONObject b4 = new JSONObject();


    @BeforeAll
    public static void beforeAll() {

        //Speise hinzufügen
        b1.put("foodId", "1");
        b1.put("menuId","1");
        b1.put("preis","6.5");
        b1.put("name","Funghi");
        b1.put("bild","");
        b1.put("beschreibung","lecker");
        b1.put("kategorieId", "1");


        //Speise hinzufügen
        b2.put("foodId", "2");
        b2.put("menuId","1");
        b2.put("preis","6.5");
        b2.put("name","Spaghetti");
        b2.put("bild","");
        b2.put("beschreibung","cremig");
        b2.put("kategorieId", "2");

        //Speise hinzufügen
        b3.put("foodId", "3");
        b3.put("menuId","1");
        b3.put("preis","6.5");
        b3.put("name","Chicken Masala");
        b3.put("bild","");
        b3.put("beschreibung","sehr scharf");
        b3.put("kategorieId", "3");

        b3.put("foodId","2");
        b3.put("name","Funghi");

        // Bestellung 1
        b4.put("date","30.01.2022");
        b4.put("foodId","2");
        b4.put("name","Funghi");
    }



    void initialize(int eingabe) {
        JSONArray statistik = new JSONArray();
        for (int i = 0;i < eingabe; i++) {
            JSONObject day = new JSONObject();
//            day.put("datum", LocalDate.now() - i);
//            day.put("statistik", getAlleSpeisen(LocalDate.now() - i));
        }
    }



    public JSONArray getAlleSpeisen(LocalDate datum) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        JSONArray alleSpeisen = new JSONArray(b3);
        for (int i = 0; i < alleSpeisen.length(); i++) {
            JSONObject toAdd = new JSONObject();
            JSONObject curSpeise = alleSpeisen.getJSONObject(i);
            toAdd.put("foodId", curSpeise.getInt("foodId"));
            toAdd.put("count", 0);
            alleSpeisen.put(toAdd);
        }

        JSONArray alleBestellungen = new JSONArray(b4);

        for (int i = 0; i < alleBestellungen.length(); i++) {
            JSONArray curBestellung = alleBestellungen.getJSONArray(i);
            for (int j = 0; j < curBestellung.length(); j++) {
                LocalDate curDate = LocalDate.parse(curBestellung.getJSONObject(j).getString("datum"), dtf);
                JSONObject curFood = curBestellung.getJSONObject(j);
                for (int k = 0; k < alleSpeisen.length(); k++) {
                    if (alleSpeisen.getJSONObject(k).getInt("foodId") == curFood.getInt("foodId")
                            && curDate.isEqual(datum)) {
                        alleSpeisen.getJSONObject(k).put("count", alleSpeisen.getJSONObject(k).getInt("count") + 1);
                    }
                }
            }
        }
        return alleSpeisen;
    }
    @Test
    public  void getAlleSpeisenTest() throws IOException{
        //ball.put(b1);
        //int count= getAlleSpeisen();
        //assertEquals(1,count);

       // JSONObject input = new JSONObject();
       // input.put("statistik", DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now().minusDays(1)));
        //int ergebnis = getAlleSpeisen(dateToString());
       // assertEquals(4, ergebnis);


    }

}