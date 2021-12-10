package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditFoodController extends ConnectionController {
    @FXML
    private Button deleteMenu;
    @FXML
    private Button deleteFood;
    @FXML
    private Button speichern;
    @FXML
    private TextArea AusgabeSpeisekarte;
    @FXML
    private TextField foodId;
    @FXML
    private TextFlow foods;

    private int menuId;

    public static int foodId2;

    public static JSONObject food;

    public EditFoodController() {
    }


    public void initialize() throws IOException {
        this.menuId = LoginController.userId;
        foods.getChildren().clear();
        String allFood = "Ihre Speisen: \n";
        String url1 = "http://localhost:8080/food";
        JSONArray jsonArray1 = new JSONArray(JSONObjectGET("http://localhost:8080/kategorie").toString());
        for (int i = 0; i < jsonArray1.length(); i++) {
            JSONObject jsonObject4 = jsonArray1.getJSONObject(i);
            if (jsonObject4.get("menuId").equals(menuId)) {
                JSONArray foods = new JSONArray(jsonObject4.getJSONArray("foods").toString());
                allFood += "Kategorie: "+ jsonObject4.get("kategorie")+"\n";
            for(int j = 0; j<foods.length(); j++){
                JSONObject jsonObject = foods.getJSONObject(j);
                    allFood = allFood + "Foodid: " + jsonObject.get("foodId").toString() +
                            "   Name: " + jsonObject.get("name").toString() + "\n";

                }
            }

            }

        Text text = new Text(allFood);
        foods.getChildren().add(text);


    }

    @FXML
    public void änderungSpeichern() throws IOException {
// hier werden die alten daten von den neuen überschrieben
        String url = "http://localhost:8080/food/update";

    }


    @FXML
    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("Speisekarte.fxml");
    }
    @FXML
    public void deleteMenu() throws IOException {
        String url = "http://localhost:8080/menu/update";
        String json = "{\"menuId\":" + menuId + ",\n" +
                "\"foods\": [] }";
        JSONObjectPUT(url, json);
        String url1 = "http://localhost:8080/food";
        JSONArray jsonArray = new JSONArray(JSONObjectGET(url1).toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.get("menuId").equals(menuId)) {
                JSONObjectDELETE("http://localhost:8080/food/delete/"+jsonObject.get("foodId"));

            }

        }
        initialize();
    }

    @FXML
    public void fertigButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");

    }

//    public void ShowFood(ActionEvent event) throws IOException {
//        foods.getChildren().clear();
//        String allFood = "Ihre Speise: \n";
//        String url1 = "http://localhost:8080/food";
//        JSONArray jsonArray = new JSONArray(JSONObjectGET(url1).toString());
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            if (jsonObject.get("menuId").equals(menuId)) {
//                allFood = allFood + "Foodid: " + jsonObject.get("foodId").toString() +
//                        "   Name: " + jsonObject.get("name").toString() + "\n";
//
//            }
//        }
//        Text text = new Text(allFood);
//        foods.getChildren().add(text);
//    }

    @FXML
    public void ChangeFood(ActionEvent event) throws IOException {

        String url1 = "http://localhost:8080/food";
        JSONArray jsonArray = new JSONArray(JSONObjectGET(url1).toString());
        System.out.println(jsonArray);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int x = Integer.parseInt(foodId.getText());

            System.out.println(jsonObject.get("foodId")+foodId.getText());
            if(jsonObject.get("foodId").equals(x)) {
                this.food = jsonObject;
                System.out.println(this.food);
                Main m = new Main();
                m.ChangeScene("EditFood2.fxml");
            }
        }


    }

}