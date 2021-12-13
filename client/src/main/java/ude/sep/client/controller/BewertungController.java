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
    private TextField kommentarTextfield;

    @FXML
    private TextField nameTextfield;

    @FXML
    ChoiceBox<String> gerichtChoiceBox;

    @FXML
    ChoiceBox<String> lieferungChoiceBox;

    @FXML
    private Button speichernButton;

    @FXML
    private Button zurueckButton;


    private int restaurantId;




    public void initialize() {

        gerichtChoiceBox.getItems().addAll("1", "2", "3", "4", "5");
        //ersten Punkt auf 5 setzen
        gerichtChoiceBox.setValue("5");

        lieferungChoiceBox.getItems().addAll("1", "2", "3", "4", "5");
        //ersten Punkt auf 5 setzen
        lieferungChoiceBox.setValue("5");

        /**
         * TODO: Speicherung fertigstellen
         */
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

            JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/rating/" + RestaurantsController.id).toString());
            Double newAvgFood = 0.0;
            Double newAvgDelivery = 0.0;
            for(int i=0;i<j.length();i++) {
                newAvgFood=newAvgFood+j.getJSONObject(i).getDouble("starsFood");
                newAvgDelivery=newAvgDelivery+j.getJSONObject(i).getDouble("starsLieferung");
            }

            newAvgFood = newAvgFood/j.length();
            newAvgDelivery = newAvgDelivery/j.length();
            JSONObject updateRest= new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/"+RestaurantsController.id).toString());
            updateRest.put("ratingFood", Math.round(newAvgFood*1e1)/1e1);
            updateRest.put("ratingDelivery", Math.round(newAvgDelivery*1e1)/1e1);
            JSONObjectPUT("http://localhost:8080/restaurant/update", updateRest.toString());


            changeScene("KStartseite.fxml");

        }

    }

    public void zurueckButtonClick() throws IOException {
        changeScene("KStartseite.fxml");
    }
}
