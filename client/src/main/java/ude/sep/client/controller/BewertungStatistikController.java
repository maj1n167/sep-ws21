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



    }

   public JSONArray getBewertungen(LocalDate datum) throws IOException {
        // alle speisen hinterlegen
        double output=0;
        double input1=0;
        double input2=0;
        JSONArray ausgabe= new JSONArray();
        JSONArray gBewertung = new JSONArray(JSONObjectGET("http://localhost:8080/rating/" + userId).toString());
        for(int i=0;i<gBewertung.length();i++) {
            JSONObject curBewertung = gBewertung.getJSONObject(i);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            input1=input1+gBewertung.getJSONObject(i).getDouble("starsLieferung");
            input2=input2+gBewertung.getJSONObject(i).getDouble("starsFood");

            if(LocalDate.parse(curBewertung.getString("date"), dtf).isAfter(datum)) {
                for(int j=0;j< curBewertung.getJSONArray("id").length(); j++) {
                    JSONObject toAdd = new JSONObject();
                    toAdd.put("datum", curBewertung.getString("date"));
                    toAdd.put("starsFood", curBewertung.getString("starsFood"));
                    toAdd.put("starsLieferung", curBewertung.getString("starsLieferung"));
                    ausgabe.put(toAdd);
                    System.out.println(ausgabe);
                }
            }
        }

        return ausgabe;
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

        System.out.println("Messen");
        JSONObject month = new JSONObject();
        month.put("datum", date.minusDays(30));
        month.put("statistik", getBewertungen(date.minusDays(30)));
        System.out.println(month);
        JSONArray jsonArray = new JSONArray(month.getJSONArray("statistik")) ;


    }



        public void goBackControl() throws IOException {
            changeScene("Startseite.fxml");
        }
    }
