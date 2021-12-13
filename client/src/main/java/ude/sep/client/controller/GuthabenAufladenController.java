package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class GuthabenAufladenController extends ConnectionController {
@FXML
private TextField textField;
@FXML
private Label label;
public int userId;
public double guthaben1;
   public void initialize() throws IOException{
       userId = LoginController.userId;
      // JSONObject user = new JSONObject(JSONObjectGET("http://localhost:8080/user/findbyid/"+userId).toString());
       String url1 = "http://localhost:8080/user";
       JSONArray jsonArray =  new JSONArray(JSONObjectGET(url1).toString());
       JSONObject jsonObject = new JSONObject();

       for(int i = 0; i<jsonArray.length(); i++){
           JSONObject jsonObject1 = jsonArray.getJSONObject(i);

           if (jsonObject1.get("userId").equals(userId)){
               jsonObject = jsonObject1;
           }
       }
       double guthaben = jsonObject.getDouble("guthaben");
       double round = round(guthaben,2);
       label.setText(String.valueOf(round));
       guthaben1 = round;
   }


    public void AufladenButton() throws IOException{

        if (textField.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Geben sie was ein");
            alert.setContentText("Guthaben einfügen");


            alert.showAndWait();
        }else {
            double aufladen =  Double.parseDouble(textField.getText());
             guthaben1 +=aufladen;
            System.out.println(guthaben1);
            String url1 = "http://localhost:8080/user";
            JSONArray jsonArray =  new JSONArray(JSONObjectGET(url1).toString());
            JSONObject jsonObject = new JSONObject();

            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                if (jsonObject1.get("userId").equals(userId)){
                    jsonObject = jsonObject1;
                }
            }
            jsonObject.put("guthaben",guthaben1);
            JSONObjectPOST("http://localhost:8080/user/add",jsonObject.toString());
            initialize();



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Guthaben hinzugefügt");
            alert.setContentText("Guthaben wurde erfolgreich hinzugefügt");

            alert.showAndWait();
        }

    }


    public void zurückButton() throws IOException{
        changeScene("KStartseite.fxml");
    }
    public void fertigButton() throws IOException{
        changeScene("KStartseite.fxml");
    }
    private double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }

}
