package ude.sep.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AdminController extends ConnectionController {

    @FXML
    TextField datum;
    @FXML
    TextField minute;

    public void onBackButton(ActionEvent actionEvent) throws IOException {changeScene("Login.fxml");}
    public void initialize() throws IOException {}

    public void onDatumGoButton(ActionEvent actionEvent) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if(!datum.getText().equals("")) {
            try {
                LoginController.date = LocalDate.parse(datum.getText(), dtf);
            } catch(DateTimeParseException e) {
                System.out.println("Eingabe ueberpruefen!");
                return;
            }
            datum.clear();
            System.out.println("Erfolgreich geaendert!");
        } else {
            System.out.println("Eingabe ueberpruefen!");
        }
    }

    public void onDatumTestButton(ActionEvent actionEvent) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("Das aktuell hinterlegte Datum im Programm ist folgendes: " + LoginController.date.format(dtf).toString());
    }

    public void onDatumResetButton(ActionEvent actionEvent) {
        LoginController.date = LocalDate.now();
        System.out.println("Erfolgreich geaendert!");
    }

    //funktionen fehlen
    public void onMinuteGoButton(ActionEvent actionEvent) throws IOException {
        try {
            int test = Integer.parseInt(minute.getText());
            JSONObject input = new JSONObject();
            input.put("toAdd", minute.getText());
            JSONObjectPOST("http://localhost:8080/time/change", input.toString());
            System.out.println("Erfolgreich geaendert!");
            minute.clear();
        } catch(NumberFormatException e) {
            System.out.println("Eingabe ueberpruefen!");
        }
    }

    public void onMinuteTestButton(ActionEvent actionEvent) throws IOException {
        String output = JSONObjectGET("http://localhost:8080/time/test").toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("Echtzeit: "+ LocalDateTime.now().format(dtf)+"\nServerzeit: "+output);
    }

    public void onMinuteResetButton(ActionEvent actionEvent) throws IOException {
        JSONObjectDELETE("http://localhost:8080/time/reset");
        System.out.println("Erfolgreich geaendert!");
    }
}
