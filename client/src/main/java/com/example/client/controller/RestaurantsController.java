package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RestaurantsController implements Initializable {


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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

