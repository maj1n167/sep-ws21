package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;

public class EditRestaurantController extends ConnectionController {

    @FXML
    private TextField nameTextfield;

    @FXML
    private TextField strasseTextfield;

    @FXML
    private TextField nummerTextfield;

    @FXML
    private TextField plzTextfield;

    @FXML
    private TextField stadtTextfield;

    @FXML
    private TextField lieferkostenTextfield;

    @FXML
    private TextField mbwTextfield;

    @FXML
    private TextField lieferbereichTextfield;

    @FXML
    ChoiceBox<String> kategorieChoicebox;

    private int userId;


    public void initialize() throws IOException {

        this.userId = LoginController.userId;

        kategorieChoicebox.getItems().addAll("Italienisch", "Griechisch", "Indisch", "Spanisch", "Deutsch", "Asiatisch", "Amerikanisch", "Türkisch", "Sonstige");

        String name = "";
        String strasse = "";
        String nummer = "";
        String plz = "";
        String stadt = "";
        double mbw = 0;
        double lieferkosten = 0;
        String kategorie = "";
        int lieferbereich = 0;


        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());

        for (int i = 0; i<j.length();i++){
            JSONObject currentjson = j.getJSONObject(i);
            if(currentjson.get("restaurantId").equals(userId)) {

                name = currentjson.get("name").toString();
                strasse = currentjson.get("strasse").toString();
                nummer = currentjson.get("nummer").toString();
                plz = currentjson.get("plz").toString();
                stadt = currentjson.get("stadt").toString();
                lieferkosten = Double.valueOf(currentjson.get("lieferkosten").toString());
                mbw = Double.valueOf(currentjson.get("mbw").toString());
                lieferbereich = Integer.valueOf(currentjson.get("lieferbereich").toString());
                kategorie = currentjson.get("kategorie").toString();

            }

        }

        nameTextfield.setText(name);
        stadtTextfield.setText(stadt);
        strasseTextfield.setText(strasse);
        nummerTextfield.setText(nummer);
        plzTextfield.setText(plz);
        mbwTextfield.setText(String.valueOf(mbw));
        lieferkostenTextfield.setText(String.valueOf(lieferkosten));
        lieferbereichTextfield.setText(String.valueOf(lieferbereich));
        kategorieChoicebox.setValue(kategorie);


    }

    public void speichernButtonClick() throws IOException {

        try {

            /**
             *     private int restaurantId;
             *     private String name;
             *     private String strasse;
             *     private String plz;
             *     private String stadt;
             *     private double mbw;
             *     private double lieferkosten;
             *     private String kategorie;
             *     private int lieferbereich;
             */

            double lieferkosten = Double.parseDouble(lieferkostenTextfield.getText());
            //nur zur Prüfung
            int plz = Integer.parseInt(plzTextfield.getText());
            double mbw = Double.parseDouble(mbwTextfield.getText());
            int radius = Integer.parseInt(lieferbereichTextfield.getText());


            DecimalFormat dec = new DecimalFormat();
            dec.setMinimumFractionDigits(2);
            dec.setMaximumFractionDigits(2);
            dec.format(lieferkosten);
            dec.format(mbw);


            if (nameTextfield.getText().equals("") || strasseTextfield.getText().equals("") || nummerTextfield.getText().equals("") || plzTextfield.getText().equals("")
                    || stadtTextfield.getText().equals("") || lieferkostenTextfield.getText().equals("") || mbwTextfield.getText().equals("")
                    || lieferbereichTextfield.getText().equals("")) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setTitle("Falsches Zeichenformat oder fehlende Werte!");
                alert.setContentText("Bitte korrigieren!");

                alert.showAndWait();

            } else {


                String url = "http://localhost:8080/restaurant/update";

                String temp = "{ \"restaurantId\":"+userId+"\n,"+
                        "\"name\": \"" + nameTextfield.getText() + "\",\n" +
                        " \"restaurantId\": \"" + userId + "\",\n" +
                        " \"strasse\": \"" + strasseTextfield.getText() + "\",\n" +
                        " \"nummer\": \"" + nummerTextfield.getText() + "\",\n" +
                        " \"plz\":" + plzTextfield.getText() + ",\n" +
                        " \"stadt\": \"" + stadtTextfield.getText() + "\",\n" +
                        " \"mbw\": \"" + mbwTextfield.getText() + "\",\n" +
                        " \"lieferkosten\": \"" + lieferkostenTextfield.getText() + "\",\n" +
                        " \"kategorie\": \"" + kategorieChoicebox.getValue() + "\",\n" +
                        " \"lieferbereich\": \"" + lieferbereichTextfield.getText() + "\"}";

                JSONObjectPUT(url, temp);


                changeScene("Startseite.fxml");

            }
        }
        catch (NumberFormatException e){
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setTitle("Falsches Zeichenformat oder fehlende Werte!");
            alert.setContentText("Bitte korrigieren!");

            alert.showAndWait();
        }
    }



    @FXML
    public void zurueckButtonClick () throws IOException {
        // Change Scenes
        changeScene("Startseite.fxml");

    }
}
