package com.example.client.controller;

import com.example.client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
 * @TODO: Favoriten fehlt
 */



public class RestaurantsController extends ConnectionController implements Initializable {

    @FXML
    ToggleGroup group = new ToggleGroup();
    @FXML
    RadioButton standard;
    @FXML
    RadioButton alternative;


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
    TableColumn order = new TableColumn("Speisekarte");
    @FXML
    TableColumn fav = new TableColumn("Favoriten");

    public static int id=-1;

    int userId = LoginController.userId;


    @FXML
    public void onZurueckButtonClick() throws IOException {
        // Fertig
        changeScene("KStartseite.fxml");
    }

    public void onAendernButtonClick(ActionEvent actionEvent) throws IOException {
        //change scene to alternativaddress
        changeScene("KStartseite.fxml");
    }

    void populate() throws IOException {
        // Fertig
        if(standard.isArmed()) {
            list.getItems().removeAll();
            list.setItems(getRestaurants(VerificationController.standard));
            list.refresh();
        } else {
            list.getItems().removeAll();
            list.setItems(getRestaurants(VerificationController.alternative));
            list.refresh();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fertig
        try {
            list.setEditable(true);
            name.setCellValueFactory(new PropertyValueFactory<RestaurantList, String>("name"));
            kategorie.setCellValueFactory(new PropertyValueFactory<RestaurantList, String>("kategorie"));
            rating.setCellValueFactory(new PropertyValueFactory<RestaurantList, Button>("ratingButton"));
            mbw.setCellValueFactory(new PropertyValueFactory<RestaurantList, Double>("mbw"));
            lieferkosten.setCellValueFactory(new PropertyValueFactory<RestaurantList, Double>("lieferkosten"));
            order.setCellValueFactory(new PropertyValueFactory<RestaurantList, Button>("order"));
            fav.setCellValueFactory(new PropertyValueFactory<RestaurantList, Button>("fav"));
            list.setItems(getRestaurants(VerificationController.standard));
            standard.setSelected(true);
            standard.setToggleGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<RestaurantList> getRestaurants(String address) throws IOException {
        ObservableList<RestaurantList> output = FXCollections.observableArrayList();
        output.clear();
        // alle restaurants in einem array
        JSONArray allRests = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());
        // alle favoriten in einem array
        JSONArray allFavs = new JSONArray(JSONObjectGET("http://localhost:8080/fav/find/" + userId).toString());

//         Favoriten des Users+ alle restaurants hinzufuegen

        //Funktioniert

        if(allFavs.length()>0) {
            for (int i = 0; i < allRests.length(); i++) {
                for (int j = 0; j < allFavs.length(); j++) {
                    JSONObject curRest = allRests.getJSONObject(i);
                    JSONObject favRest = allFavs.getJSONObject(j);
                    if (curRest.getInt("restaurantId") == favRest.getInt("restaurantId")) {
                        Button fav = new Button();
                        fav.setText("Favorit entfernen");
                        fav.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    JSONObjectDELETE("http://localhost:8080/fav/del/" + favRest.getInt("id"));
                                    populate();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        RestaurantList r = new RestaurantList();
                        output.add(r = new RestaurantList(curRest.getInt("restaurantId"),
                                curRest.getString("name"),
                                curRest.getString("strasse"),
                                curRest.getString("plz"),
                                curRest.getString("stadt"),
                                curRest.getDouble("mbw"),
                                curRest.getDouble("lieferkosten"),
                                curRest.getString("kategorie"),
                                curRest.getInt("lieferbereich"),
                                curRest.getDouble("rating"),
                                fav));
                        continue;
                    }
                    Button fav = new Button();
                    fav.setText("Favorit hinzufuegen");
                    fav.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
//                            JSONObjectPOST("http://localhost:8080/fav/add/" + curRest.getInt("restaurantId") + "/" + LoginController.userId, "null");
                                JSONObjectPOST("http://localhost:8080/fav/add/" + curRest.getInt("restaurantId") + "/" + userId, "null");
                                populate();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    RestaurantList r = new RestaurantList();
                    output.add(r = new RestaurantList(curRest.getInt("restaurantId"),
                            curRest.getString("name"),
                            curRest.getString("strasse"),
                            curRest.getString("plz"),
                            curRest.getString("stadt"),
                            curRest.getDouble("mbw"),
                            curRest.getDouble("lieferkosten"),
                            curRest.getString("kategorie"),
                            curRest.getInt("lieferbereich"),
                            curRest.getDouble("rating"),
                            fav));
                }
            }
        } else {
            for(int i=0;i<allRests.length();i++) {
            JSONObject curRest = allRests.getJSONObject(i);
            Button fav = new Button();
            fav.setText("Favorit hinzufuegen");
            fav.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        JSONObjectPOST("http://localhost:8080/fav/add/" + curRest.getInt("restaurantId")+ "/"+ userId, "null");
                        populate();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            RestaurantList r = new RestaurantList();
            output.add(r = new RestaurantList(curRest.getInt("restaurantId"),
                    curRest.getString("name"),
                    curRest.getString("strasse"),
                    curRest.getString("plz"),
                    curRest.getString("stadt"),
                    curRest.getDouble("mbw"),
                    curRest.getDouble("lieferkosten"),
                    curRest.getString("kategorie"),
                    curRest.getInt("lieferbereich"),
                    curRest.getDouble("rating"),
                    fav));
        }
        }
        return output;

        // NearMe einbauen
//
//        if(allFavs.length()>0) {
//            for (int i = 0; i < allRests.length(); i++) {
//                for (int j = 0; j < allFavs.length(); j++) {
//                    JSONObject curRest = allRests.getJSONObject(i);
//                    JSONObject favRest = allFavs.getJSONObject(j);
//                    if (curRest.getInt("restaurantId") == favRest.getInt("restaurantId")) {
//                        System.out.println("test");
//                        Button fav = new Button();
//                        fav.setText("Favorit entfernen");
//                        fav.setOnAction(new EventHandler<ActionEvent>() {
//                            @Override
//                            public void handle(ActionEvent event) {
//                                try {
//                                    JSONObjectDELETE("http://localhost:8080/fav/del/" + favRest.getInt("id"));
//                                    populate();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//                        RestaurantList r = new RestaurantList();
//                        output.add(r = new RestaurantList(curRest.getInt("restaurantId"),
//                                curRest.getString("name"),
//                                curRest.getString("strasse"),
//                                curRest.getString("plz"),
//                                curRest.getString("stadt"),
//                                curRest.getDouble("mbw"),
//                                curRest.getDouble("lieferkosten"),
//                                curRest.getString("kategorie"),
//                                curRest.getInt("lieferbereich"),
//                                curRest.getDouble("rating"),
//                                fav));
//                        continue;
//                    }
//                    Button fav = new Button();
//                    fav.setText("Favorit hinzufuegen");
//                    fav.setOnAction(new EventHandler<ActionEvent>() {
//                        @Override
//                        public void handle(ActionEvent event) {
//                            try {
////                            JSONObjectPOST("http://localhost:8080/fav/add/" + curRest.getInt("restaurantId") + "/" + LoginController.userId, "null");
//                                JSONObjectPOST("http://localhost:8080/fav/add/" + curRest.getInt("restaurantId") + "/" + userId, "null");
//                                populate();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//                    RestaurantList r = new RestaurantList();
//                    output.add(r = new RestaurantList(curRest.getInt("restaurantId"),
//                            curRest.getString("name"),
//                            curRest.getString("strasse"),
//                            curRest.getString("plz"),
//                            curRest.getString("stadt"),
//                            curRest.getDouble("mbw"),
//                            curRest.getDouble("lieferkosten"),
//                            curRest.getString("kategorie"),
//                            curRest.getInt("lieferbereich"),
//                            curRest.getDouble("rating"),
//                            fav));
//                }
//            }
//        } else {
//            for(int i=0;i<allRests.length();i++) {
//                JSONObject curRest = allRests.getJSONObject(i);
//                Button fav = new Button();
//                fav.setText("Favorit hinzufuegen");
//                fav.setOnAction(new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(ActionEvent event) {
//                        try {
//                            JSONObjectPOST("http://localhost:8080/fav/add/" + curRest.getInt("restaurantId")+ "/"+ userId, "null");
//                            populate();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                RestaurantList r = new RestaurantList();
//                output.add(r = new RestaurantList(curRest.getInt("restaurantId"),
//                        curRest.getString("name"),
//                        curRest.getString("strasse"),
//                        curRest.getString("plz"),
//                        curRest.getString("stadt"),
//                        curRest.getDouble("mbw"),
//                        curRest.getDouble("lieferkosten"),
//                        curRest.getString("kategorie"),
//                        curRest.getInt("lieferbereich"),
//                        curRest.getDouble("rating"),
//                        fav));
//            }
//        }
//        return output;
    }






    /**
     *     @TODO: Filter einbauen
     */
    private boolean isRestaurantNear(String address, int restaurantId) {
        boolean output = false;

        return output;
    }




    @FXML
    public void onStandardClick() throws IOException {
        // Fertig
        standard.setToggleGroup(group);
        list.setItems(getRestaurants(VerificationController.standard));
        list.refresh();
    }

    @FXML
    public void onAlternativeClick() throws IOException {
        // Fertig
        alternative.setToggleGroup(group);
        list.setItems(getRestaurants(VerificationController.alternative));
        list.refresh();
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
        private Button ratingButton;
        private Button order;
        private Button fav;

        public RestaurantList() { }

        public RestaurantList(int id, String name, String strasse, String plz,
                              String stadt, double mbw, double lieferkosten,
                              String kategorie, int lieferbereich, double rating, Button fav) {
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
            this.ratingButton = new Button();
            this.ratingButton.setText(Double.toString(this.rating));
            this.ratingButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    RestaurantsController.id = id;
                    try {
                        //Hier muss die View zu der Bewertungsliste
                        changeScene("BewertungList.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            this.order = new Button();
            this.order.setText("Bestellen");
            this.order.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent event) {
                    RestaurantsController.id = id;
                    try {
                        //Hier muss die View zum Bestellvorgang
                        changeScene("KStartseite.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            this.fav = fav;
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

        public Button getOrder() {return order;}

        public void setOrder(Button order) {this.order = order;}

        public Button getRatingButton() {return ratingButton;}

        public void setRatingButton(Button ratings) {this.ratingButton = ratings;}

        public Button getFav() {return fav;}

        public void setFav(Button fav) {this.fav = fav;}

        @Override
        public String toString() {
            String output = "";
            output = output + this.getId() + ", " + this.getName() + ", " + this.getKategorie() + ", " +  this.getStrasse() + ", " + this.getPlz() + " " + this.getStadt() + ", " + this.getRating() + ", " + this.getMbw() + ", " + this.getLieferkosten();
            return output;
        }
    }
}

