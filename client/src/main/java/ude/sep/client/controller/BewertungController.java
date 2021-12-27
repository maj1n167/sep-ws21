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

    private JSONObject answer;


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


    /**
     * Gibt die gespeicherte Server-Antwort zurueck
     * - Die eigentliche Antwort liegt in:
     *   answer -> response_data -> body
     *
     * @return Server-Response als JSON Object:
     * @apiNote ist das Feld leer, gab es einen Fehler
     */
    public JSONObject getAnswer() {
        return answer;
    }


    /**
     * Flusht die gespeicherte Server-Antwort
     */
    public void flush(){
        answer = null;
    }


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
    public Double avgFood(int id) throws IOException {
        double output = 0;
        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/rating/" + id).toString());
        for(int i=0;i<j.length();i++) {
            output=output+j.getJSONObject(i).getDouble("starsFood");
        }
        output = output/j.length();
        return Math.round(output*1e1)/1e1;
    }

    public Double avgDelivery(int id) throws IOException {
        double output = 0;
        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/rating/" + id).toString());
        for(int i=0;i<j.length();i++) {
            output=output+j.getJSONObject(i).getDouble("starsDelivery");
        }
        output = output/j.length();
        return Math.round(output*1e1)/1e1;
    }

    public void zurueckButtonClick() throws IOException {
        changeScene("KStartseite.fxml");
    }
}
