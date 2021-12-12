package com.example.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class bestellHistorieController extends ConnectionController implements Initializable {

    public int userId;

    @FXML
    public TreeView treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userId=LoginController.userId;
        //userId = LoginController.userId;
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
            System.out.println(bestellList);
            for(int j =0; j<bestellList.length(); j++) {
                JSONObject jsonObject = bestellList.getJSONObject(j);
                JSONArray jsonArray1 = new JSONArray(jsonObject.get("liste").toString());
                System.out.println(jsonObject);
                System.out.println(jsonObject.get("restaurantId"));
                int id = jsonObject.getInt("restaurantId");
                JSONObject restaurant = new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/"+id).toString());
                System.out.println(restaurant);
                TreeItem<String> bestellung = new TreeItem<>("Restaurant: "+restaurant.get("name").toString()+" Datum: "+jsonObject.get("datum").toString()+" Summe: "+jsonObject.get("summe").toString());
                for(int x = 0; x<jsonArray1.length(); x++){
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(x);
                    System.out.println(jsonObject1);
                    TreeItem<String> child = new TreeItem<>("Name: "+jsonObject1.get("name").toString()+" Beschreibung: "+ jsonObject1.get("beschreibung").toString()+" Preis: "+jsonObject1.get("preis").toString());
                    bestellung.getChildren().add(child);

                }
                root.getChildren().add(bestellung);


            }

          treeView.setRoot(root);


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


    public void zurückButtonn(ActionEvent actionEvent) throws IOException {
        changeScene("KStartseite.fxml");
    }

}
