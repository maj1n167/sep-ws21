package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditFood2Controller extends ConnectionController  {

    @FXML
    TextField name;

    @FXML
    TextField  beschreibung;

    @FXML
    TextField preis;

    public int foodId;

    JSONObject food;



    public  void initialize() {
        try {
            food = EditFoodController.food;
            JSONArray jsonArray2 = new JSONArray(JSONObjectGET("http://localhost:8080/food").toString());
            System.out.println(food);
            System.out.println(jsonArray2);
            for (int i = 0; i < jsonArray2.length(); i++) {
                JSONObject jsonObject = jsonArray2.getJSONObject(i);
                System.out.println("H");
                if (jsonObject.get("foodId").equals(food.get("foodId"))) {
                    food = jsonObject;
                    name.setText(food.get("name").toString());
                    beschreibung.setText(food.get("beschreibung").toString());
                    preis.setText(food.get("preis").toString());
                    return;


                }
            }

            Main m = new Main();
            m.ChangeScene("EditFood1.fxml");

        }catch (IOException e) {
            e.printStackTrace();
        }
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
        initialize();


    }

    public void fertigButton() throws IOException{
        String Url = "http://localhost:8080/food/update";

        String data =
                                 "{\"foodId\": \"" + food.getInt("foodId") + "\",\n"+
                                         "\"name\": \"" + name.getText() + "\",\n" +
                        "        \"beschreibung\": \"" + beschreibung.getText() + "\",\n" +
                        "        \"preis\":" + Double.parseDouble(preis.getText()) + ",\n" +
                        "       \"menuId\":" + food.getInt("menuId") +"}";



        JSONObjectPUT(Url, data);
        Main m = new Main();
        m.ChangeScene("EditFood1.fxml");


    }


}
