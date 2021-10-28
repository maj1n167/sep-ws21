package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class VerificationController {


   @FXML
    TextField inputCode;



    public void onGoBackButtonClick(ActionEvent event) throws IOException {
        Main m = new Main();
        m.ChangeScene("Login.fxml");
    }

   public void onVerifyButtonClicked(ActionEvent event) {


  }
}
