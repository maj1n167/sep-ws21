package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class RestaurantsController {


    @FXML
    TableView list;

    @FXML
    public void onZurueckButtonClick () throws IOException {

        // Change Scenes
        Main m = new Main();
        m.ChangeScene("KStartseite.fxml");
    }

    public void onAendernButtonClick(ActionEvent actionEvent) {

    }

    public void onFilternButtonClick(ActionEvent actionEvent) {

    }
}

