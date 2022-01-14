import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ude.sep.client.controller.ConnectionController;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StatistikTest extends ConnectionController {

    @BeforeAll
    public static void beforeAll() {

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
                JSONObject curFood = curBestellung.getJSONObject(j);
                for (int k = 0; k < alleSpeisen.length(); k++) {
                    if (alleSpeisen.getJSONObject(k).getInt("foodId") == curFood.getInt("foodId")) {
                        alleSpeisen.getJSONObject(k).put("count", alleSpeisen.getJSONObject(k).getInt("count") + 1);
                    }
                }
            }
        }
    }
}