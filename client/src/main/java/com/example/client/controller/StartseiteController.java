package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class StartseiteController {
    @FXML
    public void onEditProfileClick() throws IOException {
//        Main m= new Main()
//        m.ChangeScene(".fxml");
    }

    @FXML
    public void onCreateRestaurantClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("CreateRestaurant.fxml");
    }

    @FXML
    public void onEditRestaurantClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("EditRestaurant.fxml");
    }

    @FXML
    public void onSpeisekarteClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("Speisekarte.fxml");
    }

    @FXML
    public void onLogoutClick() throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Login.fxml");
    }
}
