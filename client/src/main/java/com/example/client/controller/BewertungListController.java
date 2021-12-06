package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class BewertungListController extends ConnectionController {

    @FXML
    private Button zurueckButton;

    @FXML
    ScrollPane scrollField;

    public void initialize () {


    }

    public void zurueckButtonClick() throws IOException {
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }

}
