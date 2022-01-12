package ude.sep.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AdminController extends ConnectionController {

    @FXML
    TextField datum;

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
    public void onMinuteGoButton(ActionEvent actionEvent) {

    }

    public void onMinuteTestButton(ActionEvent actionEvent) throws IOException {
        JSONObjectPOST("http://localhost:8080/time/test", "{}");
    }

    public void onMinuteResetButton(ActionEvent actionEvent) {

    }
}
