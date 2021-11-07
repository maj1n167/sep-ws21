package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class StartseiteController {
    public void onEditProfileClick(ActionEvent event) {
    }

    public void onCreateRestaurantClick(ActionEvent event) {
    }

    public void onEditRestaurantClick(ActionEvent event) {
    }

    public void onSpeisekarteClick(ActionEvent event) {
    }

    @FXML
    public void onLogoutClick(ActionEvent event) throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Login.fxml");
    }
}
