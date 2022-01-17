package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class BewertungStatistikController extends ConnectionController{

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    LineChart<String,Integer>bewertungLinechart;
    XYChart.Series<String,Integer> bewertungSeries;

    @FXML
    public void initialize() throws IOException{
        choiceBox.getItems().add("1 Tag");
        choiceBox.getItems().add("1 Woche");
        choiceBox.getItems().add("1 Monat");
        choiceBox.getSelectionModel().getSelectedItem();

        bewertungSeries = new XYChart.Series<>();
        bewertungSeries.setName("Bewertungen über Zeitraum");
        bewertungLinechart.getData().addAll(bewertungSeries);
    }
}
