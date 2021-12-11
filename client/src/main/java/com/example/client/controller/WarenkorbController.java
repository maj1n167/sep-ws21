package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WarenkorbController extends ConnectionController {
    @FXML
    ListView<String> listView;
    @FXML
    Label summe;

   public int userId;
   public int restaurantid;


    public void initialize() throws IOException {
        userId = LoginController.userId;
        restaurantid = MenuSpeisekarteeController.restaurantId;
        System.out.println(userId);
        System.out.println("Hello");
        JSONObject jsonObject4= new JSONObject(JSONObjectGET("http://localhost:8080/warenkorb/find/"+userId).toString());
        summe.setText(jsonObject4.get("summe").toString());
        JSONArray jsonArray = new JSONArray(jsonObject4.getJSONArray("foodList").toString());
        System.out.println(jsonArray);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject);
                    listView.getItems().add(jsonObject.get("name") +"  "+"Preis: " + jsonObject.get("preis").toString()+
                           " " +"Beschreibung: "+jsonObject.get("beschreibung"));
                }
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }
    @FXML
    public void bestellen(ActionEvent actionEvent) throws IOException {
       String x = listView.getSelectionModel().getSelectedItems().toString();
        System.out.println(x);
        String  url1 = "http://localhost:8080/warenkorb";
        String url2 = "http://localhost:8080/bestellung/add";
        String url3 = "http://localhost:8080/bestellHistorie/";
        String url4 = "http://localhost:8080/user/findbyid/"+userId;
        String url5 = "http://localhost:8080/restaurant/find/"+restaurantid;

        JSONArray jsonArray =  new JSONArray(JSONObjectGET(url1).toString());
        JSONObject jsonObject = new JSONObject();

        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

            if (jsonObject1.get("warenkorbId").equals(userId)){
                jsonObject = jsonObject1;
            }
        }
        JSONObject restaurant = new JSONObject(JSONObjectGET(url5).toString());
        JSONObject user = new JSONObject(JSONObjectGET(url4).toString());

        if(Double.parseDouble(user.get("guthaben").toString())>=Double.parseDouble(jsonObject.get("summe").toString()) &&
                Double.parseDouble(jsonObject.get("summe").toString())>= Double.parseDouble(restaurant.get("mbw").toString())) {
            double neuesGuthaben = Double.parseDouble(user.get("guthaben").toString())-Double.parseDouble(jsonObject.get("summe").toString());
            user.put("guthaben",neuesGuthaben);
            JSONObjectPOST("http://localhost:8080/user/add",user.toString());
            System.out.println(jsonObject);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            System.out.println(date.toString());
            System.out.println(jsonObject.getJSONArray("foodList"));
            JSONArray jsonArray8 = new JSONArray(jsonObject.get("foodList").toString());
            for(int z =0; z<jsonArray8.length(); z++){
                JSONObject currentFood = jsonArray8.getJSONObject(z);
                currentFood.put("date", date.toString());
                JSONObjectPOST("http://localhost:8080/bestellFood/add",
                        currentFood.toString());
            }

            JSONArray fObject = new JSONArray();
            JSONArray bestellFood = new JSONArray(JSONObjectGET("http://localhost:8080/bestellFood").toString());
            for (int b = 0; b<bestellFood.length(); b++){
                JSONObject newFood = bestellFood.getJSONObject(b);
                if(date.toString().equals(newFood.get("date"))){
                    fObject.put(newFood);
                }
            }

            System.out.println(fObject);

            System.out.println(date.toString());
            JSONObject bestellung = new JSONObject();
            System.out.println(restaurantid);
            bestellung.put("restaurantId",restaurantid);
            bestellung.put("userId", userId);
            bestellung.put("summe", jsonObject.get("summe"));
            bestellung.put("datum", date.toString());
            bestellung.put("liste", fObject);


            System.out.println(bestellung);
            System.out.println(date.toString());
            JSONObjectPOST(url2, bestellung.toString());

            JSONArray jsonArray1 = new JSONArray(JSONObjectGET(url3).toString());
            JSONArray bestellungen = new JSONArray();

            JSONArray jsonArray2 = new JSONArray(JSONObjectGET("http://localhost:8080/bestellung").toString());

            for (int j = 0; j < jsonArray2.length(); j++) {
                JSONObject jsonObject1 = jsonArray2.getJSONObject(j);
                if (jsonObject1.get("userId").equals(userId)) {
                    bestellungen.put(jsonObject1);
                }
            }
            System.out.println(bestellung);


            JSONObject jsonObject2 = new JSONObject();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);

                if (jsonObject1.get("bestellHisId").equals(userId)) {
                    jsonObject2 = jsonObject1;
                }
            }
            jsonObject2.put("bestellHis", userId);
            jsonObject2.put("bestellungenList", bestellungen);

            JSONObjectPOST("http://localhost:8080/bestellHistorie/add", jsonObject2.toString());
            System.out.printf("done");
            JSONArray y = new JSONArray();
            JSONObject emptyWarenkorb = new JSONObject();
            emptyWarenkorb.put("warenkorbId", userId);
            emptyWarenkorb.put("summe", 0);
            emptyWarenkorb.put("foodList", y);

            JSONObjectPOST(url1 + "/add", emptyWarenkorb.toString());

            JSONObjectGET("http://localhost:8080/user/send/orderVerification/"+user.get("email"));
            initialize();

        } else {
            //   Mindestbestellwert oder Guthaben reicht nicht aus
            System.out.println("zu wenig Guthaben");

            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Mbw nicht erreicht oder nicht genügend Guthaben ");
            alert.setContentText("Mbw nicht erreicht oder nicht genügend Guthaben");

            alert.showAndWait();
        }

    }




    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("KStartseite.fxml");
    }

    public void FertigButton(ActionEvent actionEvent) {
    }
}
