package ude.sep.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.jar.Attributes;


// TO DO die ResBes Objekte hier hinzu fügen 

public class SonderwünscheController extends ConnectionController {
    
    @FXML
    TableColumn bestellung;
    
    @FXML
    TableColumn datum;
    
    @FXML
    TableColumn summe;
    
    @FXML
    TableColumn sonderwunsch;
    
    @FXML
    TableColumn bearbeiten;
    
    @FXML
    TableView<Bestellung> list;

    public static int  restaurantId;
    public static int userId;
    public static int bestellungsId;

    public void initialize() {
        try {

              restaurantId = 1;
              userId = 2;
            list.setEditable(true);
            bestellung.setCellValueFactory(new PropertyValueFactory<SonderwünscheController.Bestellung, Integer>("id"));
            sonderwunsch.setCellValueFactory(new PropertyValueFactory<SonderwünscheController.Bestellung, String>("sonderwunsch"));
            summe.setCellValueFactory(new PropertyValueFactory<SonderwünscheController.Bestellung, Double>("summe"));
            datum.setCellValueFactory(new PropertyValueFactory<SonderwünscheController.Bestellung, String>("datum"));
            bearbeiten.setCellValueFactory(new PropertyValueFactory<SonderwünscheController.Bestellung,Button>("bearbeiten"));
            list.setFixedCellSize(65);
            list.setItems(getSpeisekarte());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<SonderwünscheController.Bestellung> getSpeisekarte() throws IOException {
        ObservableList<SonderwünscheController.Bestellung> output = FXCollections.observableArrayList();
        JSONObject b = new JSONObject(JSONObjectGET("http://localhost:8080/resBes/find/" + restaurantId).toString());
        JSONArray offBes = new JSONArray(b.get("bestellungenList").toString());
        System.out.println(offBes);
            for (int j = 0; j < offBes.length(); j++) {
                JSONObject currentfoods = offBes.getJSONObject(j);
                System.out.println(currentfoods);

                Bestellung r;
                output.add(r = new SonderwünscheController.Bestellung(currentfoods.getString("datum"),currentfoods.getDouble("summe"),
                currentfoods.getInt("userId"),currentfoods.getString("sonderwunsch"),currentfoods.getInt("bestellungId")));


            }

        return output;
    }


    public class Bestellung extends ConnectionController {
        private String datum;
        private double summe;
        private int user;
        private String sonderwunsch;
        private int id;



        public Bestellung(String datum, double summe, int user, String sonderwunsch) {
            this.datum = datum;
            this.summe = summe;
            this.user = user;
            this.sonderwunsch = sonderwunsch;
        }


        private Button bearbeiten;
        public Bestellung(String datum, double summe, int user, String sonderwunsch, int id) {
            this.datum = datum;
            this.summe = summe;
            this.user = user;
            this.sonderwunsch = sonderwunsch;
            this.id = id;
            this.bearbeiten = new Button();
            this.bearbeiten.setText("Hinzufügen");
            this.bearbeiten.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                   bestellungsId = id;
                    try {
                        changeScene("SonderwunschBearbeiten.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } });
        }

        public Bestellung(){}

        public Button getBearbeiten() {
            return bearbeiten;
        }

        public void setBearbeiten(Button bearbeiten) {
            this.bearbeiten = bearbeiten;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDatum() {
            return datum;
        }

        public void setDatum(String datum) {
            this.datum = datum;
        }

        public double getSumme() {
            return summe;
        }

        public void setSumme(double summe) {
            this.summe = summe;
        }

        public int getUser() {
            return user;
        }

        public void setUser(int user) {
            this.user = user;
        }

        public String getSonderwunsch() {
            return sonderwunsch;
        }

        public void setSonderwunsch(String sonderwunsch) {
            this.sonderwunsch = sonderwunsch;
        }
    }
    
    
    
    
    
    
    public void zuruckButton(ActionEvent actionEvent) throws IOException {
        changeScene("Startseite.fxml");
    }
}
