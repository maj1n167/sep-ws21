package com.example.client.controller;

import com.example.client.controller.ConnectionController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


public class LoginController extends ConnectionController {

    @FXML
    private Label SupremeLieferando;


    @FXML
    private TextField inputPassword;

    @FXML
    private  TextField inputEmail;

    @FXML
    protected void onLoginButtonClick() throws IOException {
       String url = "http://localhost:8080/user";
       String output = JSONObjectGET(url).toString();

        System.out.println(output);
    }
}