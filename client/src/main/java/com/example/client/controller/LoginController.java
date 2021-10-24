package com.example.client.controller;

import com.example.client.HelloApplication;
import com.example.client.controller.ConnectionController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Random;


public class LoginController extends ConnectionController {

    @FXML
    private Label SupremeLieferando;


    @FXML
    private TextField inputPassword;

    @FXML
    private  TextField inputEmail;
    private Stage stage;

    @FXML
    protected void onLoginButtonClick() throws IOException {
       String url = "http://localhost:8080/user";

       JSONArray jsonArray = new JSONArray(JSONObjectGET(url).toString());
       for(int i = 0; i<jsonArray.length(); i++){
         JSONObject jsonObject =  jsonArray.getJSONObject(i);
         if(jsonObject.get("email").equals(inputEmail.getText()) && jsonObject.get("password").equals(inputPassword.getText())){


             Random rnd = new Random();
             int number = rnd.nextInt(999999);
            jsonObject.put("verfiyCode",number);
            JSONObjectPUT("http://localhost:8080/user/update",jsonObject.toString());
         }
       }



        String url2 = "http://localhost:8080/user/add";

        String Json = "{\"email\":\"1234@gmail.com \",\"password\":\"56474\",\"firstname\":\"nick\"}";

       //JSONObjectPOST(url2,Json);

    }


}