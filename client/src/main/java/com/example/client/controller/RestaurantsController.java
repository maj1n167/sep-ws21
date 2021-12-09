package com.example.client.controller;

import com.example.client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @TODO: Favoriten fehlt.
 */



public class RestaurantsController extends ConnectionController implements Initializable {

    @FXML
    ToggleGroup group = new ToggleGroup();

    @FXML
    RadioButton standard;

    @FXML
    RadioButton alternative;

    private int restaurantId;


    @FXML
    TableView list = new TableView();
    @FXML
    TableColumn name = new TableColumn("Name");
    @FXML
    TableColumn kategorie = new TableColumn("Kategorie");
    @FXML
    TableColumn rating = new TableColumn("Bewertung");
    @FXML
    TableColumn mbw = new TableColumn("Mindestbestellwert");
    @FXML
    TableColumn lieferkosten = new TableColumn("Lieferkosten");
    @FXML
    TableColumn menu = new TableColumn("Speisekarte");
    @FXML
    TableColumn order = new TableColumn("Favoriten");

    ObservableList<RestaurantList> data = FXCollections.observableArrayList();


    @FXML
    public void onZurueckButtonClick() throws IOException {

        // Change Scenes
        Main m = new Main();
        m.ChangeScene("KStartseite.fxml");
    }

    public void onAendernButtonClick(ActionEvent actionEvent) {

    }



//    public void onFilternButtonClick(ActionEvent actionEvent) {
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            list.setEditable(true);
            name.setCellValueFactory(new PropertyValueFactory<RestaurantList, String>("name"));
            kategorie.setCellValueFactory(new PropertyValueFactory<RestaurantList, String>("kategorie"));
            rating.setCellValueFactory(new PropertyValueFactory<RestaurantList, Double>("rating"));
            mbw.setCellValueFactory(new PropertyValueFactory<RestaurantList, Double>("mbw"));
            lieferkosten.setCellValueFactory(new PropertyValueFactory<RestaurantList, Double>("lieferkosten"));
            menu.setCellValueFactory(new PropertyValueFactory<RestaurantList, Button>("menu"));
            order.setCellValueFactory(new PropertyValueFactory<RestaurantList, Button>("order"));
            list.setItems(getRestaurants());
            standard.setSelected(true);
            standard.setToggleGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @TODO: Buttons konfigurieren
     */
    public ObservableList<RestaurantList> getRestaurants() throws IOException {
        ObservableList<RestaurantList> output = FXCollections.observableArrayList();
        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());
        for(int i =0; i<j.length();i++) {

            JSONObject current = new JSONObject(j.get(i).toString());
            Button curMenu=new Button();
            Button curOrder=new Button();
            curMenu.setText("Ansehen");
            curOrder.setText("Favorit");





           /* Button curMenu = new Button();
            curMenu.setText("Menu");
            //buttons konfigurieren
            if(current.get("menuId").equals(menu.getId())){

            }//else nichts machen.

            Button curOrder = new Button();
            curOrder.setText("Favorit");
            current.put("Favorit",curOrder);
            JSONObjectPOST("http://localhost:8080/user/add",curOrder.toString());
            if(current.get("restaurantId").equals(restaurantId)){

            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Restaurant wurde als Favorit gespeichert");
            alert.setContentText("Restaurant wurde erfolgreich als Favorit gespeichert");

            alert.showAndWait();
 */
            //buttons konfigurieren

            RestaurantList r = new RestaurantList();
            output.add(r = new RestaurantList(current.getInt("restaurantId"), current.getString("name"), current.getString("strasse"),
                    current.getString("plz"), current.getString("stadt"), current.getDouble("mbw"),
                    current.getDouble("lieferkosten"), current.getString("kategorie"), current.getInt("lieferbereich"),
                    current.getDouble("rating"), curMenu, curOrder));
        }
        data = FXCollections.observableArrayList(output);
        return output;
    }
/**
  *     @TODO: Filter einbauen
  */

//    public ObservableList<RestaurantList> getRestaurantsNearMe(String address) throws IOException {
//        ObservableList<RestaurantList> output = FXCollections.observableArrayList();
//        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());
//        for(int i =0; i<j.length();i++) {
//            JSONObject current = new JSONObject(j.get(i).toString());
//            RestaurantList r = new RestaurantList();
//            r.setKategorie(current.getString("kategorie"));
//            r.setLieferkosten(current.getDouble("lieferkosten"));
//            r.setMbw(current.getDouble("mbw"));
//            r.setName(current.getString("name"));
//            r.setId(current.getInt("restaurantId"));
//            output.add(r);
//        }
//        return output;
//    }


    @FXML
    public void onStandardClick() throws IOException {
        standard.setToggleGroup(group);
    }

    @FXML
    public void onAlternativeClick() throws IOException {
        alternative.setToggleGroup(group);
    }

    public static class RestaurantList {
        private int id;
        private String name;
        private String strasse;
        private String plz;
        private String stadt;
        private double mbw;
        private double lieferkosten;
        private String kategorie;
        private int lieferbereich;
        private double rating;
        private Button menu;
        private Button order;

        public RestaurantList() { }

        public RestaurantList(int id, String name, String strasse, String plz,
                              String stadt, double mbw, double lieferkosten,
                              String kategorie, int lieferbereich, double rating, Button menu, Button order) {
            this.id = id;
            this.name = name;
            this.strasse = strasse;
            this.plz = plz;
            this.stadt = stadt;
            this.mbw = mbw;
            this.lieferkosten = lieferkosten;
            this.kategorie = kategorie;
            this.lieferbereich = lieferbereich;
            this.rating = rating;
            this.menu = menu;
            this.order = order;
        }

        public double getRating() { return rating; }

        public void setRating(double rating) { this.rating = rating; }

        public int getId() {
            return id;
        }

        public void setId(int restaurantId) {
            this.id = restaurantId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStrasse() {
            return strasse;
        }

        public void setStrasse(String strasse) {
            this.strasse = strasse;
        }

        public String getPlz() {
            return plz;
        }

        public void setPlz(String plz) {
            this.plz = plz;
        }

        public String getStadt() {
            return stadt;
        }

        public void setStadt(String stadt) {
            this.stadt = stadt;
        }

        public double getMbw() {
            return mbw;
        }

        public void setMbw(double mbw) {
            this.mbw = mbw;
        }

        public double getLieferkosten() {
            return lieferkosten;
        }

        public void setLieferkosten(double lieferkosten) {
            this.lieferkosten = lieferkosten;
        }

        public String getKategorie() {
            return kategorie;
        }

        public void setKategorie(String kategorie) {
            this.kategorie = kategorie;
        }

        public int getLieferbereich() {
            return lieferbereich;
        }

        public void setLieferbereich(int lieferbereich) {
            this.lieferbereich = lieferbereich;
        }

        public Button getMenu() {return menu;}

        public void setMenu(Button menu) {this.menu = menu;}

        public Button getOrder() {return order;}

        public void setOrder(Button order) {this.order = order;}


        @Override
        public String toString() {
            String output = "";
            output = output + this.getId() + ", " + this.getName() + ", " + this.getKategorie() + ", " +  this.getStrasse() + ", " + this.getPlz() + " " + this.getStadt() + ", " + this.getRating() + ", " + this.getMbw() + ", " + this.getLieferkosten();
            return output;
        }
    }
}

