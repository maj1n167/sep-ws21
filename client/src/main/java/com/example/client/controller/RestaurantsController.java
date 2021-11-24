package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class RestaurantsController {



    @FXML
    public void onZurueckButtonClick () throws IOException {
        // Change Scenes
        Main m = new Main();
        m.ChangeScene("KStartseite.fxml");
    }
}

