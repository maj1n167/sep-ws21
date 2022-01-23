package ude.sep.client.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class RestaurantbesitzerStatistikController extends ConnectionController {
    @FXML
    private PieChart dia;
    private int userId;
    LocalDate date= LoginController.date;

    @FXML
    public void initialize() throws IOException {
        userId = LoginController.userId;

    }

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

        @FXML
        public void messenTag() throws IOException {
            JSONObject day = new JSONObject();
            day.put("datum", date.minusDays(1));
            day.put("statistik", getAlleSpeisen(date.minusDays(1)));  //noch alle Gerichte hier muss man noch filtern
            JSONArray jsonArray = new JSONArray(day.getJSONArray("statistik")) ;
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            pieChartData.clear();
            dia.getData().clear();
            PieChart pieChart = new PieChart(pieChartData);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                pieChartData.add(new PieChart.Data(jsonObject.getString("name")+" "+String.valueOf(jsonObject.getInt("count")),jsonObject.getInt("count")));
                System.out.println(dia);
                pieChart.setData(pieChartData);
            }

            dia.getData().addAll(pieChartData);
            System.out.println(day);

        }

    public void messenWoche() throws IOException{
        JSONObject week = new JSONObject();
        week.put("datum", date.minusDays(7));
        week.put("statistik", getAlleSpeisen(date.minusDays(7)));  //hier muss man dann noch filtern.
        System.out.println(week);
        JSONArray jsonArray = new JSONArray(week.getJSONArray("statistik")) ;
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        pieChartData.clear();
        dia.getData().clear();
        PieChart pieChart = new PieChart(pieChartData);

        for(int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            pieChartData.add(new PieChart.Data(jsonObject.getString("name")+" "+String.valueOf(jsonObject.getInt("count")),jsonObject.getInt("count")));
            pieChart.setData(pieChartData);
        }


        dia.getData().addAll(pieChartData);
        System.out.println(week);


    }
    public void messenMonat() throws IOException{
        JSONObject month = new JSONObject();
        month.put("datum", date.minusDays(30));
        month.put("statistik", getAlleSpeisen(date.minusDays(30)));  //hier muss man dann noch filtern.
        System.out.println(month);
        JSONArray jsonArray = new JSONArray(month.getJSONArray("statistik")) ;
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        pieChartData.clear();
        dia.getData().clear();
        PieChart pieChart = new PieChart(pieChartData);
        for(int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            pieChartData.add(new PieChart.Data(jsonObject.getString("name")+" "+String.valueOf(jsonObject.getInt("count")),jsonObject.getInt("count")));
            pieChart.setData(pieChartData);

        }

        dia.getData().addAll(pieChartData);
        System.out.println(month);

    }



        public void zurueckButton ()throws IOException {
            changeScene("BewertungList.fxml");
        }



}


