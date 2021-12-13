package ude.sep.client.controller;

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
        for (int i = 0; i < j.length(); i++) {
            JSONObject curretnJson = new JSONObject(j.getJSONObject(i).toString());
            if (curretnJson.get("restaurantId").equals(LoginController.userId)) {
                createRestaurant.setVisible(false);

            }
        }
    }


    @FXML
    public void onEditProfileClick() throws IOException {
        changeScene("EditProfile.fxml");
    }

    @FXML
    public void onCreateRestaurantClick() throws IOException {
        changeScene("AddRestaurant.fxml");
    }

    @FXML
    public void onEditRestaurantClick() throws IOException {
        changeScene("EditRestaurant.fxml");
    }

    @FXML
    public void onSpeisekarteClick() throws IOException {
        changeScene("Speisekarte.fxml");
    }

    @FXML
    public void onLogoutClick() throws IOException {
        // Change Scenes
        changeScene("Login.fxml");
    }

    @FXML
    public void onMapClick() throws IOException {
        // Change Scenes
        changeScene("Map.fxml");
    }
    @FXML
    public void onRatingClick() throws IOException {
        changeScene("BewertungList.fxml");
    }
}
