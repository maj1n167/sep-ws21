package ude.sep.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    @FXML
    TableView list = new TableView();
    @FXML
    TableColumn lieferung = new TableColumn("Lieferung");
    @FXML
    TableColumn speise = new TableColumn("Speise");
    @FXML
    TableColumn comment = new TableColumn("Kommentar");
    @FXML
    TableColumn name = new TableColumn("Name");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            list.setEditable(true);
            lieferung.setCellValueFactory(new PropertyValueFactory<BewertungList, Integer>("lieferung"));
            speise.setCellValueFactory(new PropertyValueFactory<BewertungList, Integer>("gericht"));
            comment.setCellValueFactory(new PropertyValueFactory<BewertungList, String>("comment"));
            name.setCellValueFactory(new PropertyValueFactory<BewertungList, String>("name"));
            list.setItems(getBewertungen());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void zurueckButtonClick() throws IOException {
        JSONObject current = new JSONObject(JSONObjectGET("http://localhost:8080/user/findbyid/"+LoginController.userId).toString());
        if(current.getBoolean("restaurantBesitzer")) {
            changeScene("Startseite.fxml");
        } else {
            changeScene("Restaurants.fxml");
        }
    }



    public ObservableList<BewertungList> getBewertungen() throws IOException {
        ObservableList<BewertungList> output = FXCollections.observableArrayList();
        output.clear();
        JSONObject a = new JSONObject(JSONObjectGET("http://localhost:8080/user/findbyid/"+LoginController.userId).toString());
        if(a.getBoolean("restaurantBesitzer")) {

            // View fuer restaurantbesitzer fuellen

            JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/rating/" + LoginController.userId).toString());
            for (int i = 0; i < j.length(); i++) {
                JSONObject current = new JSONObject(j.get(i).toString());

                BewertungList r = new BewertungList();
                output.add(r = new BewertungList(current.getInt("id"),
                        current.getInt("restaurantId"),
                        current.getInt("starsLieferung"),
                        current.getInt("starsFood"),
                        current.getString("comment"),
                        current.getString("name")));
            }
            return output;
        }

        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/rating/" + RestaurantsController.id).toString());
        for(int i =0; i<j.length();i++) {
            JSONObject current = new JSONObject(j.get(i).toString());

            BewertungList r = new BewertungList();
            output.add(r = new BewertungList(current.getInt("id"),
                    current.getInt("restaurantId"),
                    current.getInt("starsLieferung"),
                    current.getInt("starsFood"),
                    current.getString("comment"),
                    current.getString("name")));
        }
        return output;
    }

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

        public String getName() {return name;}

        @Override
        public String toString() {
            String output = "";
            output = output + this.getId() + ", " + this.getRestaurantId() + ", " + this.getLieferung() + ", " + this.getGericht() + ", " + this.getComment();
            return output;
        }
    }

}
