package com.example.client.controller;

import com.example.client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class WarenkorbController extends ConnectionController {

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

    private ObservableList<Food> data = FXCollections.observableArrayList();


    public void initialize() {
        try {
            list.setEditable(true);
            Bild.setCellValueFactory(new PropertyValueFactory<Food, String>("Bild"));
            //Kategorie.setCellValueFactory(new PropertyValueFactory<Menu, String>("Kategorie"));
            Name.setCellValueFactory(new PropertyValueFactory<Food, String>("Name"));
            Beschreibung.setCellValueFactory(new PropertyValueFactory<Food, String>("Beschreibung"));
            Preis.setCellValueFactory(new PropertyValueFactory<Food, String>("Kategorie"));
            Id.setCellValueFactory(new PropertyValueFactory<Food, String>("Id"));
            list.setItems(getSpeisekarte());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Food> getSpeisekarte() throws IOException {
        ObservableList<Food> output = FXCollections.observableArrayList();
        JSONObject b = new JSONObject(JSONObjectGET("http://localhost:8080/menu/find/1").toString());
        JSONArray kategories= new JSONArray(b.get("kategories").toString());

        for (int i = 0; i < kategories.length(); i++) {

            JSONObject current = new JSONObject(kategories.get(i).toString());
            JSONArray foods = new JSONArray(current.get("foods").toString());
            for(int j =0;j < foods.length();j++){
                JSONObject currentfoods= new JSONObject(foods.get(j).toString());
                currentfoods.put("kategorie",current.get("kategorie"));
                Food r = new Food();
                output.add(r = new Food(currentfoods.getString("name"),
                        currentfoods.getString("beschreibung"), currentfoods.getDouble("preis"),
                        currentfoods.getInt("foodId"),currentfoods.getString("kategorie")));
                System.out.println(r.toString());
            }

        }
        data= FXCollections.observableArrayList(output);
        System.out.println(data);
        return output;
    }
    public static class Food {
        private int foodId;
        private double preis;
        private String name;
        private String beschreibung;
        private String url;
        private Long kategorieId;
        private Long menuId;
        private String kategorie;

        public Food() {
        }

        public Food(int foodId, double preis, String name, String beschreibung, String url, Long kategorieId, Long menuId,String kategorie) {
            this.foodId = foodId;
            this.preis = preis;
            this.name = name;
            this.beschreibung = beschreibung;
            this.url = url;
            this.kategorieId = kategorieId;
            this.menuId = menuId;
            this.kategorie= kategorie;
        }

        public Food(String name, String beschreibung, double preis, int id,String kategorie) {
        }
    }





    public void zurückButton () throws IOException {
        Main m = new Main();
        m.ChangeScene("KStartseite");
    }


    public void FertigButton(ActionEvent actionEvent) {
    }
}
