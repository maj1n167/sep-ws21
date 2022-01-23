package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class LieferzeitController extends ConnectionController {

    @FXML
    Label ergebnisLabel;

    public void initialize() throws IOException {
        String answer = getDeliveryTime(LoginController.userId)+ " Minuten";
        if(getDeliveryTime(LoginController.userId)<=0) {answer = "0 Minuten";}
        ergebnisLabel.setText(answer);
    }


    public void zurueckButtonClick() throws IOException {
        changeScene("KStartseite.fxml");
    }

    public void bewertungButtonClick() throws IOException {
        KundeStartseiteController.ordered = false;
        changeScene("Bewertung.fxml");
    }

    public void refreshButtonClick() throws IOException {
        initialize();
    }
}
