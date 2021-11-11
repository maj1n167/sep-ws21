package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;

import java.io.IOException;

public class EditFood2Controller {
    @FXML
    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("Speisekarte.fxml");
    }


    public void deleteFood() throws IOException {


    }

    public void fertigButton() throws IOException{


    }
}
