import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ude.sep.client.controller.ConnectionController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StatistikTest extends ConnectionController {




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
        JSONArray alleSpeisen = new JSONArray();
        for (int i = 0; i < alleSpeisen.length(); i++) {
            JSONObject toAdd = new JSONObject();
            JSONObject curSpeise = alleSpeisen.getJSONObject(i);
            toAdd.put("foodId", curSpeise.getInt("foodId"));
            toAdd.put("count", 0);
            alleSpeisen.put(toAdd);
        }

        JSONArray alleBestellungen = new JSONArray();

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
}