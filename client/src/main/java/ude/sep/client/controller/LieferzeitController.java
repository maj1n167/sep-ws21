package ude.sep.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class LieferzeitController extends ConnectionController {

    @FXML
    Label ergebnisLabel;

    public void initialize() throws IOException {
        String answer = "";



        ergebnisLabel.setText(answer);
    }


    public void zurueckButtonClick(ActionEvent actionEvent) throws IOException {
        changeScene("KStartseite.fxml");
    }

    public void bewertungButtonClick(ActionEvent actionEvent) throws IOException {
        changeScene("Bewertung.fxml");
    }
}
