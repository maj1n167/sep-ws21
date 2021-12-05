package com.example.client.controller;

import com.example.client.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GuthabenAufladenController extends ConnectionController {
@FXML
private TextField textField;
@FXML
private Label label;
public int userId=1;
public double guthaben;
   public void initialize() throws IOException{
   //     userId = LoginController.userId;
    //   JSONObject user = new JSONObject(JSONObjectGET("http://localhost:8080/user/findbyId/"+userId).toString());
       String url1 = "http://localhost:8080/user";
       JSONArray jsonArray =  new JSONArray(JSONObjectGET(url1).toString());
       JSONObject jsonObject = new JSONObject();

       for(int i = 0; i<jsonArray.length(); i++){
           JSONObject jsonObject1 = jsonArray.getJSONObject(i);

           if (jsonObject1.get("userId").equals(userId)){
               jsonObject = jsonObject1;
           }
       }
       label.setText(jsonObject.get("guthaben").toString());
       guthaben=Double.parseDouble(jsonObject.get("guthaben").toString()); 
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
             guthaben +=aufladen;
            String url1 = "http://localhost:8080/user";
            JSONArray jsonArray =  new JSONArray(JSONObjectGET(url1).toString());
            JSONObject jsonObject = new JSONObject();

            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                if (jsonObject1.get("userId").equals(userId)){
                    jsonObject = jsonObject1;
                }
            }
            jsonObject.put("guthaben",guthaben);
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
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }
    public void fertigButton() throws IOException{
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }

}
