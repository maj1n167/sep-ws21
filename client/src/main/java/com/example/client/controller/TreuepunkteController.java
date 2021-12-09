package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TreuepunkteController extends ConnectionController {

    @FXML
    TextField DiscountText;

    @FXML
    public void onDiscountButtonClick(ActionEvent event) {
    }


    @FXML
    public void onGenerate(ActionEvent event) throws IOException {

        String url ="http://localhost:8080/coupon/send/";

        JSONObjectGET(url + LoginController.email);

    }

    @FXML
    public void onGoBackButtonClick(ActionEvent event) throws IOException {

        Main m = new Main();
        m.ChangeScene("KStartseite.fxml");
    }


}
