package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class StartseiteController extends ConnectionController {
    @FXML
    public Image bild;
    @FXML
    Button createRestaurant;

    @FXML
    Button editRestaurant;

    @FXML
    public void initialize() throws IOException {
        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());
        System.out.println(j.toString());
        for (int i = 0; i < j.length(); i++) {
            System.out.println(j.length());
            JSONObject curretnJson = new JSONObject(j.getJSONObject(i).toString());
            if (curretnJson.get("restaurantId").equals(LoginController.userId)) {
                createRestaurant.setVisible(false);

            }
        }
    }


    @FXML
    public void onEditProfileClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("EditProfile.fxml");
    }

    @FXML
    public void onCreateRestaurantClick() throws IOException {
        Main m= new Main();
        m.ChangeScene("AddRestaurant.fxml");
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

    @FXML
    public void onMapClick() throws IOException {
        // Change Scenes
        Main m= new Main();
        m.ChangeScene("Map.fxml");
    }
}
