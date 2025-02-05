package ude.sep.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AktionenController extends ConnectionController {

    @FXML
    TextField datum1;
    @FXML
    TextField datum2;


    @FXML
    public void goSaleClick() throws IOException {

        if(datum1.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehler!");
            alert.setTitle("Bitte füllen Sie die Felder richtig aus!");
            alert.setContentText("Bitte korrigieren!");
            alert.showAndWait();
        } else try {
            stringToDate(datum1.getText());
        } catch(DateTimeParseException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fehler!");
            alert.setTitle("Bitte füllen Sie die Felder richtig aus!");
            alert.setContentText("Bitte korrigieren!");
            alert.showAndWait();
            return;
        }

        //Alter Sale wird entfernt
        JSONArray jsonArray = new JSONArray(JSONObjectGET("http://localhost:8080/sale").toString());
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject abc = jsonArray.getJSONObject(i);
                JSONObjectDELETE("http://localhost:8080/sale/del/" + abc.get("id"));
            }
        JSONObject rest = new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/"+LoginController.userId).toString());
        JSONObject input = new JSONObject();
        input.put("rName", rest.getString("name"));
        input.put("rId" , LoginController.userId);
        input.put("start", datum1.getText());
        if(datum2.getText().matches("")) {input.put("end", datum1.getText());}
        else {input.put("end", datum2.getText());}
        addSale(input);
        changeScene("Startseite.fxml");
    }

    public void goBackClick(ActionEvent event) throws IOException{
        changeScene("Startseite.fxml");
    }
}
