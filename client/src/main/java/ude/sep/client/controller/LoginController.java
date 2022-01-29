package ude.sep.client.controller;

import com.dlsc.gmapsfx.shapes.Circle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;


public class LoginController extends ConnectionController {



    @FXML
    private Label SupremeLieferando;
    @FXML
    public PasswordField inputPassword;
    @FXML
    Button RegisterButton;
    @FXML
    public TextField inputEmail;

    public static String email;
    public static int userId;
    public static LocalDate date = LocalDate.now();


    @FXML
    public void onLoginButtonClick() throws IOException {

       String url = "http://localhost:8080/user";

       JSONArray jsonArray = new JSONArray(JSONObjectGET(url).toString());
       for(int i = 0; i<jsonArray.length(); i++){
         JSONObject jsonObject =  jsonArray.getJSONObject(i);
         if(jsonObject.get("email").equals(inputEmail.getText()) && jsonObject.get("password").equals(inputPassword.getText())){
             JSONObjectGET("http://localhost:8080/user/send/verification/"+jsonObject.get("email"));
             email = jsonObject.get("email").toString();
             userId = Integer.parseInt(jsonObject.get("userId").toString());
             // zum testen
//             if(jsonObject.getBoolean("restaurantBesitzer")){
//                 changeScene("Startseite.fxml");
//                 return;
//             } else {
//                 VerificationController.standard = jsonObject.getString("strasse")
//                         + " " + jsonObject.getString("nummer")
//                         + " " + jsonObject.getString("plz")
//                         + " " + jsonObject.getString("stadt");
//                 VerificationController.alternative = jsonObject.getString("altAdresse")
//                         + " " + jsonObject.getString("altNummer")
//                         + " " + jsonObject.getString("altPlz")
//                         + " " + jsonObject.getString("altStadt");
//                 changeScene("KStartseite.fxml");
//                 return;
//             }
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

    public int getUserId() {
        return userId;
    }

    @FXML
    public void onAdminButton() throws IOException{
        //test
//        changeScene("Admin.fxml");
//        return;
        //testende
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Supreme Eating Program");
        dialog.setHeaderText("Sie moechten die Admin-Seite oeffnen.");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        PasswordField pwd = new PasswordField();
        HBox content = new HBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        content.getChildren().addAll(new Label("Bitte geben Sie zur Bestätigung das Passwort ein:"), pwd);
        dialog.getDialogPane().setContent(content);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return pwd.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println(result.get());
        }

        String input = dialog.getResult();
        if(input.matches("12345678")) {
            changeScene("Admin.fxml");
        } else {
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Admin Passwort falsch");
            alert.setContentText("Bitte überprüfen sie ihre Eingaben!");
            alert.showAndWait();
        }
    }
}