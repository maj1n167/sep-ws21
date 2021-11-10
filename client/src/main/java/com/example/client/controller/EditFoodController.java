package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

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
    private TextField  foodId;
    @FXML
    private ChoiceBox<String> neuekategorie;
    @FXML
    private TextField neuername;
    @FXML
    private TextField neuebeschreibung;
    @FXML
    private TextField neuepreis;


   public void initialize() throws IOException{



   }

    @FXML
    public void änderungSpeichern()throws IOException {
// hier werden die alten daten von den neuen überschrieben
            String url = "http://localhost:8080/food/update";

    }




    @FXML
    public void zurückButton() throws IOException{
        Main m = new Main();
        m.ChangeScene("Speisekarte.fxml");
    }

    public void deleteMenu() throws IOException {


    }

    public void deleteFood() throws IOException {
       

    }

    public void fertigButton() throws IOException{


    }
}
