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
import java.util.ResourceBundle;

public class BewertungListController extends ConnectionController implements Initializable {

    @FXML
    private Button zurueckButton;

    @FXML
    ScrollPane scrollField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            list.setEditable(true);
            id.setCellValueFactory(new PropertyValueFactory<BewertungListController.BewertungList, Integer>("Id"));
            restaurantId.setCellValueFactory(new PropertyValueFactory<BewertungListController.BewertungList, Integer>("restaurantId"));
            lieferung.setCellValueFactory(new PropertyValueFactory<BewertungListController.BewertungList, Integer>("starsLieferung"));
            speise.setCellValueFactory(new PropertyValueFactory<BewertungListController.BewertungList, Integer>("starsFood"));
            comment.setCellValueFactory(new PropertyValueFactory<BewertungListController.BewertungList, String>("comment"));
            name.setCellValueFactory(new PropertyValueFactory<BewertungListController.BewertungList, String>("name"));
            list.setItems(getBewertungen());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zurueckButtonClick() throws IOException {
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }

    @FXML
    TableView list = new TableView();
    @FXML
    TableColumn id = new TableColumn("Id");
    @FXML
    TableColumn restaurantId = new TableColumn("RestaurantId");
    @FXML
    TableColumn lieferung = new TableColumn("Lieferung");
    @FXML
    TableColumn speise = new TableColumn("Speise");
    @FXML
    TableColumn comment = new TableColumn("Kommentar");
    @FXML
    TableColumn name = new TableColumn("Name");

    ObservableList<BewertungListController.BewertungList> data = FXCollections.observableArrayList();


    @FXML
    public void onZurueckButtonClick() throws IOException {

        // Change Scenes
        Main m = new Main();
        m.ChangeScene("KStartseite.fxml");
    }

    /**
     * @TODO: Buttons konfigurieren
     */
    public ObservableList<BewertungListController.BewertungList> getBewertungen() throws IOException {
        ObservableList<BewertungListController.BewertungList> output = FXCollections.observableArrayList();
        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/rating").toString());
        for(int i =0; i<j.length();i++) {
            JSONObject current = new JSONObject(j.get(i).toString());

            BewertungListController.BewertungList r = new BewertungListController.BewertungList();
            output.add(r = new BewertungListController.BewertungList(current.getInt("id"), current.getInt("restaurantId"), current.getInt("starsLieferung"),
                    current.getInt("starsFood"), current.getString("comment"), current.getString("name")));
        }
        data = FXCollections.observableArrayList(output);
        return output;
    }
    /**
     *     @TODO: Filter einbauen
     */


    public static class BewertungList {
        private int id;
        private int restaurantId;
        private int lieferung;
        private int gericht;
        private String comment;
        private String name;

        public BewertungList() { }

        public BewertungList(int id, int restaurantId, int lieferung, int gericht, String comment, String name) {
            this.id = id;
            this.restaurantId = restaurantId;
            this.lieferung = lieferung;
            this.gericht = gericht;
            this.comment = comment;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int restaurantId) {
            this.id = restaurantId;
        }

        public int getRestaurantId() {
            return restaurantId;
        }

        public void setRestaurantId(int restaurantId) {
            this.restaurantId = restaurantId;
        }

        public int getGericht() {
            return gericht;
        }

        public void setGericht(int gericht) {
            this.gericht = gericht;
        }

        public int getLieferung() {
            return lieferung;
        }

        public void setLieferung(int lieferung) {
            this.lieferung = lieferung;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setName(String name) {
            this.name = name;
        }


        @Override
        public String toString() {
            String output = "";
            output = output + this.getId() + ", " + this.getRestaurantId() + ", " + this.getLieferung() + ", " + this.getGericht() + ", " + this.getComment();
            return output;
        }
    }

}
