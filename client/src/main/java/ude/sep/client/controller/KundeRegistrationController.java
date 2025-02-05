package ude.sep.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class KundeRegistrationController extends ConnectionController {

    @FXML
   public Button KundeRegisterButton;
    @FXML
   public TextField geburtsdatumTextfield;
    @FXML
   public TextField postleitzahlTextfield;
    @FXML
   public TextField stadtTextfield;
    @FXML
   public TextField strasseTextfield;
    @FXML
   public TextField nummerTextfield;



    @FXML
    public void onKundeRegisterButtonClick() throws IOException {
//      JSONObject nutzen
        if (geburtsdatumTextfield.getText().equals("") || postleitzahlTextfield.getText().equals("") || stadtTextfield.getText().equals("")
                || strasseTextfield.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlende Zeile");
            alert.setContentText("Bitte füllen Sie alle Felder aus");
            alert.showAndWait();
        } else {
            String data = "";
            String url = "http://localhost:8080/user/update/"+RegistrationController.regUserId;
            JSONArray jsonArray = new JSONArray(JSONObjectGET("http://localhost:8080/user").toString());
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject =  jsonArray.getJSONObject(i);
                if(jsonObject.get("userId").equals(RegistrationController.regUserId)){
                    data = data + "{ \"userId\": \""+RegistrationController.regUserId+"\", \n" +
                            "   \"vorname\": \"" + jsonObject.get("vorname").toString() + "\",\n" +
                            "   \"name\": \"" + jsonObject.get("name").toString() + "\",\n" +
                            "   \"email\":\"" + jsonObject.get("email").toString() + "\",\n" +
                            "   \"password\": \"" + jsonObject.get("password").toString() + "\",\n" +
                            "   \"restaurantBesitzer\": \"false\", \n";
                }
            }
            data = data + "   \"geburtsdatum\": \"" + geburtsdatumTextfield.getText().toString() + "\",\n" +
                    "   \"plz\": \"" + postleitzahlTextfield.getText().toString() + "\",\n" +
                    "   \"nummer\": \""+nummerTextfield.getText().toString() +"\",\n" +
                    "   \"stadt\":\"" + stadtTextfield.getText().toString() + "\",\n" +
                    "   \"guthaben\": \"0\",\n" +
                    "   \"altAdresse\": \"\",\n" +
                    "   \"altPlz\": \"\",\n" +
                    "   \"altNummer\": \"\",\n" +
                    "   \"altStadt\": \"\",\n" +
                    "   \"strasse\": \"" + strasseTextfield.getText().toString() + "\"\n }";
            System.out.println(data);
            JSONObjectPUT(url, data);

            //WarenkorbErstellen

            JSONObject wk = new JSONObject();
            wk.put("warenkorbId", RegistrationController.regUserId);
            wk.put("summe", 0.0);
            JSONObjectPOST("http://localhost:8080/warenkorb/add", wk.toString());

            //BestellHistorieErstellen

            JSONObject bh = new JSONObject();
            bh.put("bestellHisId", RegistrationController.regUserId);
            JSONObjectPOST("http://localhost:8080/bestellHistorie/add", bh.toString());



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Konto hinzugefügt");
            alert.setContentText("Konto erfolgreich erstellt!");
            alert.showAndWait();
            changeScene("Login.fxml");
        }
    }
    @FXML
    public void goBackToButtonClick(ActionEvent actionEvent) throws IOException {
        JSONObjectDELETE("http://localhost:8080/user/delete/"+RegistrationController.regUserId);
        changeScene("Login.fxml");
    }
}

