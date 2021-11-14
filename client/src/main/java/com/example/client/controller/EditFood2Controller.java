package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditFood2Controller extends ConnectionController implements Initializable {

    @FXML
    TextField name;

    @FXML
    TextField  beschreibung;

    @FXML
    TextField preis;

    @FXML
    ChoiceBox kategorie;

    JSONObject food;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
       food = EditFoodController.food;
       kategorie.getItems().addAll("Pizza", "Pasta", "Salate", "Desserts");
       kategorie.setValue(food.get("kategorie"));
       kategorie.show();
       name.setText(food.getString("name"));
       beschreibung.setText(food.getString("beschreibung"));
       preis.setText(String.valueOf(food.getDouble("preis")));

    }




    @FXML
    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("Speisekarte.fxml");
    }


    public void deleteFood() throws IOException {
        JSONObjectDELETE("http://localhost:8080/food/delete/"+String.valueOf(food.getInt("foodId")));
        Main m = new Main();
        m.ChangeScene("EditFood1.fxml");


    }

    public void fertigButton() throws IOException{
        System.out.println(food.toString());
        String Url = "http://localhost:8080/food/update";

        String data =
                                 "{\"foodId\": \"" + food.getInt("foodId") + "\",\n"+
                                         "\"name\": \"" + name.getText() + "\",\n" +
                        "        \"beschreibung\": \"" + beschreibung.getText() + "\",\n" +
                        "        \"preis\":" + Double.parseDouble(preis.getText()) + ",\n" +
                        "       \"menuId\":" + food.getInt("menuId") +",\n"+
                        "        \"kategorie\": \"" + kategorie.getValue() + "\"}";


        JSONObjectPUT(Url, data);
        Main m = new Main();
        m.ChangeScene("EditFood1.fxml");


    }


}
