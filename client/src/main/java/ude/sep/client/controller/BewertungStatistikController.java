package ude.sep.client.controller;

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
        JSONArray ausgabe = new JSONArray();
        JSONArray gBewertung = new JSONArray(JSONObjectGET("http://localhost:8080/rating").toString());
        for (int i = 0; i < gBewertung.length(); i++) {
            JSONObject curBewertung = gBewertung.getJSONObject(i);
            if (curBewertung.get("restaurantId").equals(userId)) {
                ausgabe.put(curBewertung);


         }
        }
        System.out.println(ausgabe);

        }




    public void onBMessenTag() throws IOException {

        XYChart.Series series= new XYChart.Series();

        series.getData().add(new XYChart.Data<>("1",10));
        series.getData().add(new XYChart.Data<>("2",9));
        series.getData().add(new XYChart.Data<>("3",9));
        series.getData().add(new XYChart.Data<>("4",8));
        series.getData().add(new XYChart.Data<>("5",9));
        series.getData().add(new XYChart.Data<>("6",10));
        series.getData().add(new XYChart.Data<>("7",9));

        lineChartBewertungGesamt.getData().addAll(series);




    }
    public void onBMessenWoche() throws IOException {

        XYChart.Series series= new XYChart.Series();

        series.getData().add(new XYChart.Data<>("1",10));
        series.getData().add(new XYChart.Data<>("2",9));
        series.getData().add(new XYChart.Data<>("3",5));
        series.getData().add(new XYChart.Data<>("4",4));
        series.getData().add(new XYChart.Data<>("5",1));
        series.getData().add(new XYChart.Data<>("6",10));
        series.getData().add(new XYChart.Data<>("7",1));

        lineChartBewertungGesamt.getData().addAll(series);



    }
    public void onBMessenMonat() throws IOException {



    }



        public void goBackControl() throws IOException {
            changeScene("Startseite.fxml");
        }
    }
