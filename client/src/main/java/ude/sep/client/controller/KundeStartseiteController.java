package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class KundeStartseiteController extends ConnectionController{

    public static boolean ordered = false;

    @FXML
    Button order;

    @FXML
    public void initialize() throws IOException {

        System.out.println("Standard: " + VerificationController.standard);
        System.out.println("Alternativ: " + VerificationController.alternative);
        order.setVisible(ordered);
    }



    @FXML
    public void onSeekRestaurantClick() throws IOException {
        changeScene("Restaurants.fxml");
    }

    @FXML
    public void onShowOrderClick() throws IOException {
        changeScene("bestellHistorie.fxml");
    }

    @FXML
    public void onBuyCreditClick() throws IOException {
        changeScene("Guthaben.fxml");
    }

    @FXML
    public void onValuationClick() throws IOException {
        changeScene("Bewertung.fxml");
    }

    @FXML
    public void onLogoutClick() throws IOException {
        // Change Scenes
        changeScene("Login.fxml");
    }

    @FXML
    public void onLoyaltyPointsClick() throws IOException {
        // Change Scenes
        changeScene("Treuepunkte.fxml");
    }

    @FXML
    public void onTimeClick() throws IOException {
        changeScene("Lieferzeit.fxml");
    }
}


