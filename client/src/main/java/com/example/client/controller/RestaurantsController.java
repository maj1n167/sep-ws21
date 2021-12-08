package com.example.client.controller;

import com.example.client.Main;



import com.example.client.model.RestaurantList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class RestaurantsController extends ConnectionController implements Initializable {


    @FXML
    private TableView<RestaurantList> list = new TableView<>();
    @FXML
    public TableColumn<RestaurantList, Integer> restaurantId;

    @FXML
    public TableColumn<RestaurantList, String> name;

    @FXML
    public TableColumn<RestaurantList, String> kategorie;

    @FXML
    public TableColumn<RestaurantList, Integer> mbw;

    @FXML
    public TableColumn<RestaurantList, String> lieferkosten;

//    @FXML
//    public TableColumn<RestaurantList, String> rating;

//    @FXML
//    public TableColumn<RestaurantList, Button> Speisekarte;
//
//    @FXML
//    public TableColumn<RestaurantList, Button> bestellen;



    ObservableList<RestaurantList> restaurants= FXCollections.observableArrayList();



    @FXML
    public void onZurueckButtonClick() throws IOException {

        // Change Scenes
        Main m = new Main();
        m.ChangeScene("KStartseite.fxml");
    }

    public void onAendernButtonClick(ActionEvent actionEvent) {

    }

    public void onFilternButtonClick(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        restaurantId.setCellValueFactory(new PropertyValueFactory<>("RestaurantId"));
        kategorie.setCellValueFactory(new PropertyValueFactory<>("Kategorie"));
        lieferkosten.setCellValueFactory(new PropertyValueFactory<>("Lieferkosten"));
        mbw.setCellValueFactory(new PropertyValueFactory<>("Mbw"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        try {
            list.setEditable(true);
            JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());
            for(int i =0; i<j.length();i++) {
                System.out.println(j.get(i).toString());
                JSONObject current = new JSONObject(j.get(i).toString());
                RestaurantList r = new RestaurantList();
                r.setKategorie(current.getString("kategorie"));
                r.setLieferkosten(current.getDouble("lieferkosten"));
                r.setMbw(current.getDouble("mbw"));
                r.setName(current.getString("name"));
                r.setRestaurantId(current.getInt("restaurantId"));
                restaurants.add(i, r);
            }

//            rating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
            list.setItems(restaurants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

