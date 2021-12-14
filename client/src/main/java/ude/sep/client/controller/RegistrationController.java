package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class RegistrationController extends ConnectionController {

    @FXML
    Button registerButton;
    @FXML
    public TextField vornameTextfield = new TextField();
    @FXML
    public TextField nameTextfield= new TextField();
    @FXML
    public TextField emailTextfield = new TextField();
    @FXML
    public PasswordField passwortTextfield= new PasswordField();
    @FXML
     public RadioButton businessUser = new RadioButton();
    @FXML
    public RadioButton kunde = new RadioButton();
    @FXML
    ToggleGroup group = new ToggleGroup();

    public static int regUserId;

    public RegistrationController() {
    }

    @FXML
    public void onRegisterButtonClick() throws IOException {

        if (vornameTextfield.getText().equals("") || nameTextfield.getText().equals("") || emailTextfield.getText().equals("")
                || passwortTextfield.getText().equals("") || !(businessUser.isSelected() || kunde.isSelected()) ) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlende Zeile");
            alert.setContentText("Bitte füllen Sie alle Felder aus");
            alert.showAndWait();
        } else {

            JSONArray json = new JSONArray(JSONObjectGET("http://localhost:8080/user").toString());
            for(int i = 0; i<json.length(); i++){
                JSONObject jsonObject =  json.getJSONObject(i);
                if(jsonObject.get("email").equals(emailTextfield.getText().toString())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setTitle("Error: Konto Bereits vorhanden!");
                    alert.setContentText("Konto bereits vorhanden!\nVerwenden Sie eine ander E-Mail!");
                    alert.showAndWait();
                    return;
                }
            }
            if (businessUser.isSelected()) {

                String url = "http://localhost:8080/user/add";


                String data = "{ \"vorname\": \"" + vornameTextfield.getText().toString() + "\",\n" +
                        " \"name\": \"" + nameTextfield.getText().toString() + "\",\n" +
                        " \"email\":\"" + emailTextfield.getText().toString() + "\",\n" +
                        " \"password\": \"" + passwortTextfield.getText().toString() + "\",\n" +
                        " \"restaurantBesitzer\": \"true\" }";

                JSONObjectPOST(url, data);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Konto hinzugefügt");
                alert.setContentText("Konto erfolgreich erstellt!");
                alert.showAndWait();
                changeScene("Login.fxml");
            } else {
//         Hier erstmal alles kommentiert, da das hier in Zyklus 2 relevant sein wird. -ok
                String url = "http://localhost:8080/user/add";

                JSONObject user = new JSONObject();

                user.put("vorname", vornameTextfield.getText());
                user.put("name", nameTextfield.getText());
                user.put("email", emailTextfield.getText());
                user.put("password", passwortTextfield.getText());
                user.put("guthaben", 0);
                user.put("restaurantBesitzer", false);


                JSONObjectPOST(url, user.toString());
                System.out.println("Daten korrekt übertragen");

                JSONArray jsonArray = new JSONArray(JSONObjectGET("http://localhost:8080/user").toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.get("email").equals(emailTextfield.getText().toString()) && jsonObject.get("password").equals(passwortTextfield.getText().toString())) {
                        regUserId = Integer.parseInt(jsonObject.get("userId").toString());
                    }
                }
                changeScene("KRegistration.fxml");
            }
        }
    }

    @FXML
    public void goBackButtonClick() throws IOException {
        // Change Scenes
        changeScene("Login.fxml");
    }

    @FXML
    public void onPrivatKundeClick() throws IOException {

        //Privatkunde --> Fortsetzung in Zyklus 2
        kunde.setToggleGroup(group);
    }

    @FXML
    public void onBusinessUserClick() throws IOException {

        //BusinessUser --> Weiterleitung zur Startseite
        businessUser.setToggleGroup(group);
    }
}

