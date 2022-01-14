package ude.sep.client.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;


public class RestaurantbesitzerStatistikController extends ConnectionController {
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private ListView sortedFood;
    private int restaurantId;
    private int userId;


    @FXML
    public void initialize() throws IOException {
        restaurantId = RestaurantsController.id;
        userId = LoginController.userId;
        choiceBox.getItems().add("1 Tag");
        choiceBox.getItems().add("1 Woche");
        choiceBox.getItems().add("1 Monat");
        choiceBox.getSelectionModel().getSelectedItem();


        String url1 = "http://localhost:8080/bestellung";
        JSONArray jsonArray = new JSONArray(JSONObjectGET(url1).toString());
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        System.out.println(jsonArray);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            //System.out.println(jsonArray);
            //System.out.println(userId);
            System.out.println(jsonObject1.get("restaurantId"));
            if (jsonObject1.getInt("restaurantId") == 2) {
                jsonArray1.put(jsonObject1);

            }
        }
        System.out.println(jsonArray1);
        for (int j = 0; j < jsonArray1.length(); j++) {
           JSONObject current= jsonArray1.getJSONObject(j);
           JSONArray jsonArray3= new JSONArray(current.getJSONArray("liste"));
            for (int k=0;k<jsonArray3.length();k++){
                JSONObject x=jsonArray3.getJSONObject(k);
                jsonArray2.put(x);

            }

        }
        System.out.println(jsonArray2);



    }


        public void zurückButton ()throws IOException {
            changeScene("Startseite.fxml");
        }
    }


