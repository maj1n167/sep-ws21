package ude.sep.client.controller;


import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


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

        // Aktueller Statistikzeitraum für 1 Tag und für 1 Woche
        JSONArray statistik = new JSONArray();
        LocalDate date= LocalDate.now();

            if(choiceBox.getItems().equals("1 Tag")){
                JSONObject day = new JSONObject();
                day.put("datum", date.minusDays(7));
                day.put("statistik", getAlleSpeisen(date.minusDays(7)));  //noch alle Gerichte hier muss man noch filtern
            }
           if(choiceBox.getItems().equals("1 Woche")){
            JSONObject week = new JSONObject();
            week.put("datum", date.minusDays(7));
            week.put("statistik", getAlleSpeisen(date.minusDays(7)));  //hier muss man dann noch filtern.
        }



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
            if (jsonObject1.getInt("restaurantId") == restaurantId) {
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
   /*  public Object verkaufteSpeise() throws IOException {

        ArrayList<String> list = new ArrayList<String>();
        JSONArray eingabe = new JSONArray("http://localhost:8080/bestellungen");
        ArrayList<String> gefiltertList= new ArrayList<String>();
        String speisenName="";
        if (eingabe != null) {
            for (int i = 0; i < eingabe.length(); i++) {
                list.add(eingabe.get(i).toString());
            }
            return speisenName;
        }
   */

    /*
        void initialize(int eingabe){
            JSONArray statistik = new JSONArray();
            LocalDate date= LocalDate.now();
            for (int i = 0;i < eingabe; i++) {
                if(choiceBox.getItems().equals("1 Woche")){
                JSONObject week = new JSONObject();
                week.put("datum", date.minusDays(7));
                week.put("statistik", getAlleSpeisen(date.minusDays(7));
            }

        } */


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


        public void zurueckButton ()throws IOException {
            changeScene("Startseite.fxml");
        }
    }


