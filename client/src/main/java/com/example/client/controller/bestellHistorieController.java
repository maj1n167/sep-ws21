package com.example.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class bestellHistorieController extends ConnectionController implements Initializable {

    public int userId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userId = LoginController.userId;
        try {
            JSONObject currentBestellHis = new JSONObject();
            JSONArray jsonArray = new JSONArray(JSONObjectGET("http://localhost:8080/bestellHistorie").toString());
            for(int i =0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject.get("bestellHisId").equals(userId)){
                    currentBestellHis = jsonObject;
                }
            }
            TreeItem<String> root = new TreeItem<>("Speisekarte");
            // Alle Bestellungen drinnen
            JSONArray bestellList = new JSONArray(currentBestellHis.get("bestellungenList").toString());
            String x [] = new String[bestellList.length()];

            for(int j =0; j<bestellList.length(); j++) {
                x[j] = String.valueOf(j);
                JSONObject jsonObject = bestellList.getJSONObject(j);
                JSONObject restaurant = new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find"+jsonObject.get("restaurantId").toString()));
                TreeItem<String> bestellung = new TreeItem<>(restaurant.get("name ").toString()+jsonObject.get("datum").toString());
             //   restaurant.get("name ").toString()+jsonObject.get("datum").toString()


            }



        } catch (IOException e) {
            e.printStackTrace();
        }


/**
        if (jsonObject4.get("menuId").equals(restaurantid)) {
                JSONArray foods = new JSONArray(jsonObject4.getJSONArray("foods").toString());
                listView.getItems().add(jsonObject4.get("kategorie").toString());
                for (int j = 0; j < foods.length(); j++) {
                    JSONObject jsonObject = foods.getJSONObject(j);
                    listView.getItems().add(jsonObject.get("name") +"  "+"Preis: " + jsonObject.get("preis").toString()+
                            " " +"Beschreibung: "+jsonObject.get("beschreibung"));
                    //listView.getItems().setAll("Pizza","Döner","Spaghetti");
                }
            }
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
**/
    }

    public void zurückButton(ActionEvent actionEvent) {
    }

    public void FertigButton(ActionEvent actionEvent) {
    }
}
