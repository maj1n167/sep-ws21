package ude.sep.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class BewertungStatistikController extends ConnectionController {
    @FXML
    private LineChart lineChartBewertungGesamt;

   @FXML
    private NumberAxis y;

   @FXML
    private CategoryAxis x;


    private int userId;
    LocalDate date= LoginController.date;


    @FXML
    public void initialize() throws IOException {
        userId = LoginController.userId;
    }
    public void letzteFünf() throws IOException {
        XYChart.Series series= new XYChart.Series();
        XYChart.Series series1= new XYChart.Series();
        series.setName("Essensbewertung");
        series1.setName("Lieferbewertung");
        JSONArray gBewertung = new JSONArray(JSONObjectGET("http://localhost:8080/rating/"+userId+"/5").toString());
        System.out.println(gBewertung);
        lineChartBewertungGesamt.getData().clear();
        series.getData().clear();
        series1.getData().clear();
        for (int i=0;i<gBewertung.length();i++) {
            String letztenBewertungen = String.valueOf(i+1);
                series.getData().add(new XYChart.Data<>(letztenBewertungen, gBewertung.getJSONObject(i).getInt("starsFood")));
                series1.getData().add(new XYChart.Data<>(letztenBewertungen, gBewertung.getJSONObject(i).getInt("starsLieferung")));
        }
        System.out.println(series);
        lineChartBewertungGesamt.getData().addAll(series,series1);
    }

    public void letzteZehn() throws IOException {
        XYChart.Series series= new XYChart.Series();
        XYChart.Series series1= new XYChart.Series();
        series.setName("Essensbewertung");
        series1.setName("Lieferbewertung");
        JSONArray gBewertung = new JSONArray(JSONObjectGET("http://localhost:8080/rating/"+userId+"/10").toString());
        System.out.println(gBewertung);
        lineChartBewertungGesamt.getData().clear();
        series.getData().clear();
        series1.getData().clear();
        for (int i=0;i<gBewertung.length();i++) {
            String letztenBewertungen = String.valueOf(i+1);
            series.getData().add(new XYChart.Data<>(letztenBewertungen, gBewertung.getJSONObject(i).getInt("starsFood")));
            series1.getData().add(new XYChart.Data<>(letztenBewertungen, gBewertung.getJSONObject(i).getInt("starsLieferung")));
        }
        System.out.println(series);
        lineChartBewertungGesamt.getData().addAll(series,series1);
    }
    public void letzteFünfzehn() throws IOException {
        XYChart.Series series= new XYChart.Series();
        XYChart.Series series1= new XYChart.Series();
        series.setName("Essensbewertung");
        series1.setName("Lieferbewertung");
        JSONArray gBewertung = new JSONArray(JSONObjectGET("http://localhost:8080/rating/"+userId+"/15").toString());
        System.out.println(gBewertung);
        lineChartBewertungGesamt.getData().clear();
        series.getData().clear();
        series1.getData().clear();
        for (int i=0;i<gBewertung.length();i++) {
            String letztenBewertungen = String.valueOf(i+1);
            series.getData().add(new XYChart.Data<>(letztenBewertungen, gBewertung.getJSONObject(i).getInt("starsFood")));
            series1.getData().add(new XYChart.Data<>(letztenBewertungen, gBewertung.getJSONObject(i).getInt("starsLieferung")));
        }
        System.out.println(series);
        lineChartBewertungGesamt.getData().addAll(series,series1);
    }

    public void zurückButton()throws IOException {
        changeScene("BewertungList.fxml");
    }
}
