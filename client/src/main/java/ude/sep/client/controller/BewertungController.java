package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;


public class BewertungController extends ConnectionController {

    @FXML
    public TextField kommentarTextfield = new TextField();

    @FXML
    public TextField nameTextfield = new TextField();

    @FXML
    public ChoiceBox<String> gerichtChoiceBox;

    @FXML
    public ChoiceBox<String> lieferungChoiceBox;

    @FXML
    public Button speichernButton;

    @FXML
    public Button zurueckButton;


    public int restaurantId;


    public void initialize() {

        gerichtChoiceBox.getItems().addAll("1", "2", "3", "4", "5");
        //ersten Punkt auf 5 setzen
        gerichtChoiceBox.setValue("5");

        lieferungChoiceBox.getItems().addAll("1", "2", "3", "4", "5");
        //ersten Punkt auf 5 setzen
        lieferungChoiceBox.setValue("5");

        this.restaurantId = RestaurantsController.id;
    }


    public void speichernButtonClick () throws IOException {

        if (kommentarTextfield.getText().equals("") || nameTextfield.getText().equals("") ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlendes Kommentarfeld.");
            alert.setContentText("Bitte füllen Sie das Kommentarfeld aus.");

            alert.showAndWait();
        }
        else {

            /**
             * int id;
             *     int starsLieferung;
             *     int starsFood;
             *     String comment;
             */
            String url = "http://localhost:8080/rating/add";

            JSONObject bewertung = new JSONObject();

            bewertung.put("comment", kommentarTextfield.getText());
            bewertung.put("name", nameTextfield.getText());
            bewertung.put("starsLieferung", Integer.parseInt(lieferungChoiceBox.getValue()));
            bewertung.put("starsFood", Integer.parseInt(gerichtChoiceBox.getValue()));
            bewertung.put("restaurantId", restaurantId);

            System.out.println(bewertung);
            JSONObjectPOST(url, bewertung.toString());

            //Avg Bewertung anpassen
            JSONObject updateRest= new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/"+RestaurantsController.id).toString());
            updateRest.put("ratingFood", avgFood(RestaurantsController.id));
            updateRest.put("ratingDelivery", avgDelivery(RestaurantsController.id));
            JSONObjectPUT("http://localhost:8080/restaurant/update", updateRest.toString());


            changeScene("KStartseite.fxml");
        }
    }

    public void zurueckButtonClick() throws IOException {
        changeScene("KStartseite.fxml");
    }
}
