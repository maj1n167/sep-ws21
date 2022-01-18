package ude.sep.client.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
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

    ObservableList<PieChart.Data> pieChartData;
    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private PieChart dia;

    private int restaurantId;
    private int userId;

    @FXML
    LineChart<String,Integer>linechart;
    XYChart.Series<String,Integer> bestellungSeries;


    @FXML
    public void initialize() throws IOException {
        userId = LoginController.userId;
        choiceBox.getItems().add("1 Tag");
        choiceBox.getItems().add("1 Woche");
        choiceBox.getItems().add("1 Monat");
        choiceBox.getSelectionModel().getSelectedItem();

        /* choiceBox.setOnAction((event) -> {
            int selectedIndex = choiceBox.getSelectionModel().getSelectedIndex();
            Object selectedItem = choiceBox.getSelectionModel().getSelectedItem();

            System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
            System.out.println("   ChoiceBox.getValue(): " + choiceBox.getValue());
        }); */

        //Linechart konfigurieren
        bestellungSeries = new XYChart.Series<>();
        bestellungSeries.setName("Bestellungen über Zeitraum");
//        linechart.getData().addAll(bestellungSeries);





        // Aktueller Statistikzeitraum für 1 Tag und für 1 Woche
        JSONArray gesStatistik = new JSONArray();
        LocalDate date= LoginController.date;

             if(choiceBox.getValue().equals("1 Tag")){
                JSONObject day = new JSONObject();
                day.put("datum", date.minusDays(1));
                day.put("statistik", getAlleSpeisen(date.minusDays(1)));  //noch alle Gerichte hier muss man noch filtern


                dia.getData().addAll(pieChartData);

            }
           if(choiceBox.getValue().equals("1 Woche")){
            JSONObject week = new JSONObject();
            week.put("datum", date.minusDays(7));
            week.put("statistik", getAlleSpeisen(date.minusDays(7)));  //hier muss man dann noch filtern.
               System.out.println(week);


               JSONArray jsonArray = new JSONArray(week.getJSONArray("statistik")) ;
               ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
               PieChart pieChart = new PieChart(pieChartData);
               for(int i=0; i<jsonArray.length(); i++){
                   JSONObject jsonObject = jsonArray.getJSONObject(i);
                   pieChartData.add(new PieChart.Data(jsonObject.getString("name"),jsonObject.getInt("count")));
                   pieChart.setData(pieChartData);
               }


               dia.getData().addAll(pieChartData);



        }



    }





        //Oguzhan anfang
        public JSONArray getAlleSpeisen(LocalDate datum) throws IOException {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            // alle speisen hinterlegen
            JSONArray output = new JSONArray();
            JSONArray alleSpeisen = getMenu();
            // alle speisen im bestellzzeitraum zaehlen
            JSONArray alleBestellungen = getBestellungen(datum);
            for(int i = 0;i<alleSpeisen.length();i++) {
                JSONObject curSpeise = alleSpeisen.getJSONObject(i);
                int count = 0;
                for(int j=0; j<alleBestellungen.length();j++) {
                    JSONObject curOrder = alleBestellungen.getJSONObject(j);
                    if(curOrder.getString("name").equals(curSpeise.getString("name"))) {
                        count++;
                    }
                }
                curSpeise.put("count", count);
                output.put(curSpeise);
            }
            return output;
        }





        // oguzhan ende

        @FXML
        public void onRefresh() throws IOException {


            JSONArray gesStatistik = new JSONArray();
            LocalDate date= LoginController.date;

            if(choiceBox.getItems().contains("1 Tag")){
                JSONObject day = new JSONObject();
                day.put("datum", date.minusDays(1));
                day.put("statistik", getAlleSpeisen(date.minusDays(1)));  //noch alle Gerichte hier muss man noch filtern
                System.out.println("Messen");
            }
            if(choiceBox.getItems().contains("1 Woche")){
                JSONObject week = new JSONObject();
                week.put("datum", date.minusDays(7));
                week.put("statistik", getAlleSpeisen(date.minusDays(7)));  //hier muss man dann noch filtern.
                System.out.println(week);
            }



        }





        public void zurueckButton ()throws IOException {
            changeScene("Startseite.fxml");
        }
    }


