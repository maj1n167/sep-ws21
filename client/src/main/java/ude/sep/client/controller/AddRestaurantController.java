package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;


public class AddRestaurantController extends ConnectionController {

    @FXML
    private Label restaurantHeaderLabel;

    @FXML
    public TextField nameTextfield;

    @FXML
    public TextField strasseTextfield;

    @FXML
    public TextField nummerTextfield;

    @FXML
    public TextField plzTextfield;

    @FXML
    public TextField stadtTextfield;

    @FXML
    public TextField lieferkostenTextfield;

    @FXML
    public TextField mbwTextfield;

    @FXML
    public TextField lieferbereichTextfield;

    @FXML
    public ChoiceBox<String> kategorieChoicebox;

    @FXML
    public Button speichernButton;

    @FXML
    public Button zurueckButton;

    public int userId;




    public void initialize() {

        kategorieChoicebox.getItems().addAll("Italienisch", "Griechisch", "Indisch", "Spanisch", "Deutsch", "Asiatisch", "Amerikanisch", "Türkisch", "Sonstige");
        //ersten Punkt auf Pizza setzen
        kategorieChoicebox.setValue("Italienisch");

        this.userId = LoginController.userId;
    }



    @FXML
    public void speichernButtonClick() throws IOException {
        try {


            double lieferkosten = Double.parseDouble(lieferkostenTextfield.getText());
            //nur zur Prüfung
            int plz = Integer.parseInt(plzTextfield.getText());
            double mbw = Double.parseDouble(mbwTextfield.getText());
            int radius = Integer.parseInt(lieferbereichTextfield.getText());


            DecimalFormat dec = new DecimalFormat();
            //Nachkommastellen
            dec.setMinimumFractionDigits(2);
            dec.setMaximumFractionDigits(2);
            //€-Format
            dec.format(lieferkosten);
            dec.format(mbw);


            if (nameTextfield.getText().equals("") || strasseTextfield.getText().equals("") || nummerTextfield.getText().equals("") || plzTextfield.getText().equals("")
                    || stadtTextfield.getText().equals("") || lieferkostenTextfield.getText().equals("") || mbwTextfield.getText().equals("")
                    || lieferbereichTextfield.getText().equals("")) {

                //pop-up
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setTitle("Falsches Zeichenformat oder fehlende Werte!");
                alert.setContentText("Bitte korrigieren!");
                //benutzeraction abwarten
                alert.showAndWait();

            } else {

                String url = "http://localhost:8080/restaurant/add";

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

                String json = "{ \"name\": \"" + nameTextfield.getText() + "\",\n" +
                            " \"restaurantId\": \"" + userId + "\",\n" +
                            " \"strasse\": \"" + strasseTextfield.getText() + "\",\n" +
                            " \"nummer\": \"" + nummerTextfield.getText() + "\",\n" +
                            " \"plz\": \"" + plzTextfield.getText() + "\",\n" +
                            " \"stadt\": \"" + stadtTextfield.getText() + "\",\n" +
                            " \"mbw\": \"" + mbw + "\",\n" +
                            " \"lieferkosten\": \"" + lieferkosten + "\",\n" +
                            " \"kategorie\": \"" + kategorieChoicebox.getValue() + "\",\n" +
                            " \"ratingFood\": \"" + 0.0 + "\",\n" +
                            " \"ratingDelivery\": \"" + 0.0 + "\",\n" +
                            " \"lieferbereich\": \"" + radius + "\"\n}";


                System.out.println(json);
                //Request
                JSONObjectPOST(url, json);

                //Speisekarte ID weiterleiten
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("resBesId",userId);

                String json2 = "{ \"menuId\":"+ userId + " }";
                JSONObjectPOST("http://localhost:8080/menu/add",json2);
                JSONObjectPOST("http://localhost:8080/resBes/add",jsonObject.toString());


                changeScene("Startseite.fxml");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setTitle("Falsches Zeichenformat oder fehlende Werte!");
            alert.setContentText("Bitte korrigieren!");

            alert.showAndWait();
        }
    }

    public void zurueckButtonClick() throws IOException {
        changeScene("Startseite.fxml");
    }


}

