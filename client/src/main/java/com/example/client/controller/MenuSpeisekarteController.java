package com.example.client.controller;

import com.example.client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MenuSpeisekarteController extends ConnectionController {
    @FXML
    TableView list = new TableView();
    @FXML
    TableColumn Kategorie = new TableColumn("Kategorie");
    @FXML
    TableColumn AddButton = new TableColumn("zumWarenkorb");
    @FXML
    TableColumn Preis = new TableColumn("Preis");
    @FXML
    TableColumn Beschreibung = new TableColumn("Beschreibung");
    @FXML
    TableColumn Id = new TableColumn("Id");
    @FXML
    TableColumn Bild = new TableColumn("Bild");
    @FXML
    TableColumn Name = new TableColumn("Name");
    private ObservableList<Menu> data = FXCollections.observableArrayList();


    public void initialize() {
        try {
            list.setEditable(true);
            Bild.setCellValueFactory(new PropertyValueFactory<Menu, String>("Bild"));
            //Kategorie.setCellValueFactory(new PropertyValueFactory<Menu, String>("Kategorie"));
            Name.setCellValueFactory(new PropertyValueFactory<Menu, String>("Name"));
            Beschreibung.setCellValueFactory(new PropertyValueFactory<Menu, String>("Beschreibung"));
            Preis.setCellValueFactory(new PropertyValueFactory<Menu, String>("Kategorie"));
            Id.setCellValueFactory(new PropertyValueFactory<Menu, String>("Id"));
            list.setItems(getSpeisekarte());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Menu> getSpeisekarte() throws IOException {
        ObservableList<Menu> output = FXCollections.observableArrayList();
        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/Menu").toString());
        for (int i = 0; i < j.length(); i++) {

            JSONObject current = new JSONObject(j.get(i).toString());
            Menu r = new Menu();
            output.add(r = new Menu(current.getString("Bild"), current.getString("Name"),
                    current.getString("Beschreibung"), current.getString("Preis"), current.getDouble("Id");
        }
        data= FXCollections.observableArrayList(output);
        return output;

            public void zurückButton () throws IOException {
                Main m = new Main();
                m.ChangeScene("KStartseite");
            }


        }
    }
}