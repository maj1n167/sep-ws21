package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class TabellenStatistikController extends ConnectionController {

    private JSONObject bestellungen;
    private JSONObject bewertungen;

    @FXML
    public ChoiceBox<String> zeitraumChoiceBox;
    @FXML
    public Button zueruckButton;

    public int userId;
    public int restaurantId;

    public void initialize() {
        zeitraumChoiceBox.getItems().addAll("1 Tag", "7 Tage", "14 Tage");
        zeitraumChoiceBox.setValue("0");

        this.restaurantId = RestaurantsController.id;

    }
    /*
    public int bewertungOverTime(int id) throws IOException {
        //HIer soll immer die Bewertung nacheinander ausgedruckt werden
        JSONArray output;
        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/rating/" + id).toString());
        for(int i=0;i<j.length();i++) {
            output=j.getJSONArray(i);
        }
        return output;
    }

     */

    public void zurueckButtonClick() throws IOException {
        changeScene("KStartseite.fxml");
    }
}
