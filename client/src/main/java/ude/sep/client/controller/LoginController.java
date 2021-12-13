package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;



public class LoginController extends ConnectionController {



    @FXML
    private Label SupremeLieferando;
    @FXML
    private PasswordField inputPassword;
    @FXML
    Button RegisterButton;
    @FXML
    private  TextField inputEmail;

    public static String email;
    public static int userId;


    @FXML
    protected void onLoginButtonClick() throws IOException {

       String url = "http://localhost:8080/user";

       JSONArray jsonArray = new JSONArray(JSONObjectGET(url).toString());
       for(int i = 0; i<jsonArray.length(); i++){
         JSONObject jsonObject =  jsonArray.getJSONObject(i);
         if(jsonObject.get("email").equals(inputEmail.getText()) && jsonObject.get("password").equals(inputPassword.getText())){
             JSONObjectGET("http://localhost:8080/user/send/verification/"+jsonObject.get("email"));
             email = jsonObject.get("email").toString();
             userId = Integer.parseInt(jsonObject.get("userId").toString());
             changeScene("Verify-View.fxml");
             return;
         }
       }
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setTitle("Error: Daten nicht Korrekt");
        alert.setContentText("Bitte überprüfen sie ihre Eingaben!");

        alert.showAndWait();
    }
    @FXML
    public void onRegisterButtonClick() throws IOException {
        // Change Scenes
        changeScene("Registration.fxml");
    }

    @FXML
    public void onExitButtonClick() throws IOException {
        System.exit(0);
    }
}