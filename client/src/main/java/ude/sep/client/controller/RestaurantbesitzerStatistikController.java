package ude.sep.client.controller;


import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class RestaurantbesitzerStatistikController extends ConnectionController {
    @FXML
    private ChoiceBox choiceBox;

    private int restaurantId;
    private int userId;

    @FXML
    LineChart<String,Integer>linechart;
    XYChart.Series<String,Integer> bestellungSeries;




    @FXML
    public void initialize() throws IOException {
        restaurantId = RestaurantsController.id;
        userId = LoginController.userId;
        choiceBox.getItems().add("1 Tag");
        choiceBox.getItems().add("1 Woche");
        choiceBox.getItems().add("1 Monat");
        choiceBox.getSelectionModel().getSelectedItem();

        //Linechart konfigurieren
        bestellungSeries = new XYChart.Series<>();
        bestellungSeries.setName("Bestellungen über Zeitraum");
        linechart.getData().addAll(bestellungSeries);



        // Aktueller Statistikzeitraum für 1 Tag und für 1 Woche
        JSONArray gesStatistik = new JSONArray();
        LocalDate date= LocalDate.now();

            if(choiceBox.getItems().equals("1 Tag")){
                JSONObject day = new JSONObject();
                day.put("datum", date.minusDays(1));
                day.put("statistik", getAlleSpeisen(date.minusDays(1)));  //noch alle Gerichte hier muss man noch filtern
            }
           if(choiceBox.getItems().equals("1 Woche")){
            JSONObject week = new JSONObject();
            week.put("datum", date.minusDays(7));
            week.put("statistik", getAlleSpeisen(date.minusDays(7)));  //hier muss man dann noch filtern.

               Iterator<String> nameItr = week.keys();   //Verwandlung von JSon in Map damit man filtern kann.
               Map<String, String> outMap = new HashMap<>();
               while(nameItr.hasNext()) {
                   String name = nameItr.next();
                   outMap.put(name, week.getString(name));
               }

               /*
                   hier streamen

               Map.Entry<String, Integer> max = outMap.entrySet().stream()
                       .max((o1, o2) -> o1.getValue(),o2.getValue()).get();    MAXvergleich klappt noch nicht.


               String besteGericht= max.getKey();     //Name der Speise, die am häufigsten verkauft wurde
               int salesBesteGericht = max.getValue(); //Sie wurde x mal verkauft.

               */


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

      /*  @FXML
        public void onRefresh(){  Mit RefreshButton rueckwärts zählen

            //letzten Daten hinzu
            System.out.println("Messen");

            BestellungsData bd= data.get(data.size()-1);

            bestellungSeries.getData().add(new XYChart.Data<String,Integer>(bd.getTimestamp.toString(),bd.getBestellungen()))

            //bestellungSeries.getChart();

        } */





        public void zurueckButton ()throws IOException {
            changeScene("Startseite.fxml");
        }
    }


