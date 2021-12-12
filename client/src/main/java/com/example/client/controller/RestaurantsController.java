package com.example.client.controller;

import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.MapComponentInitializedListener;
import com.dlsc.gmapsfx.javascript.object.*;
import com.dlsc.gmapsfx.service.geocoding.GeocoderStatus;
import com.dlsc.gmapsfx.service.geocoding.GeocodingResult;
import com.dlsc.gmapsfx.service.geocoding.GeocodingService;
import com.dlsc.gmapsfx.shapes.Circle;
import com.dlsc.gmapsfx.shapes.CircleOptions;
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


//, MapComponentInitializedListener
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
    TableColumn ratingFood;
    @FXML
    TableColumn ratingDelivery;
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

//    @FXML
//    GoogleMapView mapView;
//    private GoogleMap map;
//    GeocodingService geocodingService;


    @FXML
    public void onZurueckButtonClick() throws IOException {
        // Fertig
        changeScene("KStartseite.fxml");
    }

    public void onAendernButtonClick(ActionEvent actionEvent) throws IOException {
        //change scene to alternativaddress
        changeScene("EditAltProfile.fxml");
    }

    void populateList() throws IOException {
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

    void populateMap() throws IOException {
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
            standard.setSelected(true);
            standard.setToggleGroup(group);
            list.setEditable(true);
            name.setCellValueFactory(new PropertyValueFactory<RestaurantList, String>("name"));
            kategorie.setCellValueFactory(new PropertyValueFactory<RestaurantList, String>("kategorie"));
            ratingFood.setCellValueFactory(new PropertyValueFactory<RestaurantList, Double>("ratingFood"));
            ratingDelivery.setCellValueFactory(new PropertyValueFactory<RestaurantList, Double>("ratingLieferung"));
            rating.setCellValueFactory(new PropertyValueFactory<RestaurantList, Button>("ratingButton"));
            mbw.setCellValueFactory(new PropertyValueFactory<RestaurantList, Double>("mbw"));
            lieferkosten.setCellValueFactory(new PropertyValueFactory<RestaurantList, Double>("lieferkosten"));
            order.setCellValueFactory(new PropertyValueFactory<RestaurantList, Button>("order"));
            fav.setCellValueFactory(new PropertyValueFactory<RestaurantList, Button>("fav"));
            list.setItems(getRestaurants(VerificationController.standard));

//            mapView.addMapInitializedListener(this);
//            mapView.setKey("AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        mapView.addMapInitializedListener(this);
//        mapView.setKey("AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o");
    }

//    @Override
//    public void mapInitialized() {
//        String address = "";
//
//        if(standard.isArmed()) {
//            address = VerificationController.standard;
//        }
//        if (alternative.isArmed()) {
//            address = VerificationController.alternative;
//        }
//        System.out.println(address);
//        geocodingService = new GeocodingService();
//        MapOptions mapOptions = new MapOptions();
//        mapOptions.center(new LatLong(0, 0))
//                .mapType(MapTypeIdEnum.ROADMAP)
//                .overviewMapControl(false)
//                .panControl(false)
//                .rotateControl(false)
//                .scaleControl(false)
//                .streetViewControl(false)
//                .fullscreenControl(false)
//                .mapTypeControl(false)
//                .zoom(12)
//        ;
//        map = mapView.createMap(mapOptions);
//        geocodingService.geocode(address, (GeocodingResult[] results, GeocoderStatus status) -> {
//            LatLong latlong = null;
//            System.out.println("test0");
//            if (status == GeocoderStatus.ZERO_RESULTS) {
//                System.out.println("test1");
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Google API hat kein Ergebnis!");
//                alert.setContentText("Bitte erneut versuchen!\nFalls der Fehler weiterhin auftritt,\nbitte Restaurantdaten ueberpruefen");
//                alert.showAndWait();
//                try {
//                    changeScene("Login.fxml");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return;
//            } else if (results.length > 1) {
//                System.out.println("test2");
//                latlong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
//            } else {
//                System.out.println("test3");
//                latlong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
//            }
//            System.out.println(latlong);
//            map.setCenter(latlong);
//
//            MarkerOptions mOptions = new MarkerOptions();
//            mOptions.visible(true);
//            mOptions.position(latlong);
//            Marker marker = new Marker(mOptions);
//            marker.setTitle("Home");
//            marker.setPosition(latlong);
//            map.addMarker(marker);
//        });
//    }

    public ObservableList<RestaurantList> getRestaurants(String address) throws IOException {
        ObservableList<RestaurantList> output = FXCollections.observableArrayList();
        output.clear();
        // alle restaurants in einem array
        JSONArray allRests = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());
        // alle favoriten in einem array
        JSONArray allFavs = new JSONArray(JSONObjectGET("http://localhost:8080/fav/find/" + userId).toString());

//         Favoriten des Users+ alle restaurants hinzufuegen

        //Funktioniert
//
//        if(allFavs.length()>0) {
//            for (int i = 0; i < allRests.length(); i++) {
//                for (int j = 0; j < allFavs.length(); j++) {
//                    JSONObject curRest = allRests.getJSONObject(i);
//                    JSONObject favRest = allFavs.getJSONObject(j);
//                    if (curRest.getInt("restaurantId") == favRest.getInt("restaurantId")) {
//                        Button fav = new Button();
//                        fav.setText("Favorit entfernen");
//                        fav.setOnAction(new EventHandler<ActionEvent>() {
//                            @Override
//                            public void handle(ActionEvent event) {
//                                try {
//                                    JSONObjectDELETE("http://localhost:8080/fav/del/" + favRest.getInt("id"));
//                                    populateList();
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
//                                curRest.getDouble("ratingFood"),
//                                curRest.getDouble("ratingDelivery"),
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
//                                populateList();
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
//                            curRest.getDouble("ratingFood"),
//                            curRest.getDouble("ratingDelivery"),
//                            fav));
//                }
//            }
//        } else {
//            for(int i=0;i<allRests.length();i++) {
//            JSONObject curRest = allRests.getJSONObject(i);
//            Button fav = new Button();
//            fav.setText("Favorit hinzufuegen");
//            fav.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent event) {
//                    try {
//                        JSONObjectPOST("http://localhost:8080/fav/add/" + curRest.getInt("restaurantId")+ "/"+ userId, "null");
//                        populateList();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            RestaurantList r = new RestaurantList();
//            output.add(r = new RestaurantList(curRest.getInt("restaurantId"),
//                    curRest.getString("name"),
//                    curRest.getString("strasse"),
//                    curRest.getString("plz"),
//                    curRest.getString("stadt"),
//                    curRest.getDouble("mbw"),
//                    curRest.getDouble("lieferkosten"),
//                    curRest.getString("kategorie"),
//                    curRest.getInt("lieferbereich"),
//                    curRest.getDouble("ratingFood"),
//                    curRest.getDouble("ratingDelivery"),
//                    fav));
//        }
//        }
//        return output;

        // NearMe einbauen
        boolean isNear = false;
        boolean isFav = false;
        int id = 0;
        for(int i=0;i<allRests.length();i++){
            JSONObject curRest = allRests.getJSONObject(i);
            // ueberpruefung, ob restaurant fav ist
            for (int j = 0; j < allFavs.length(); j++) {
                JSONObject favRest = allFavs.getJSONObject(j);
                    if (curRest.getInt("restaurantId") == favRest.getInt("restaurantId")) {
                        isFav = true;
                        id = favRest.getInt("id");
                    }
            }
            //ueberpruefung, ob restaurant near ist
            isNear = isRestaurantNear(curRest.getInt("restaurantId"));

            if(isNear || isFav) {

//                public RestaurantList(int id,
//                String name,
//                String strasse,
//                String plz,
//                String stadt,
//                double mbw,
//                double lieferkosten,
//                String kategorie,
//                int lieferbereich,
//                double ratingFood,
//                double ratingLieferung,
//                Boolean isFav,
//                int favId,
//                int userId)

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
                        curRest.getDouble("ratingFood"),
                        curRest.getDouble("ratingDelivery"),
                        isFav,
                        id,
                        userId));
            }
        }
        return output;
    }
    /**
     *     @TODO: Filter einbauen
     */
    private Boolean isRestaurantNear(int restaurantId) throws IOException {
        Boolean output = false;
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
        JSONObject user = new JSONObject(JSONObjectGET("http://localhost:8080/user/findbyid/"+LoginController.userId).toString());
        if(standard.isSelected()) {url = url + user.getString("strasse") + "%2C" + user.getString("nummer") + "%2C" + user.getString("plz") + "%2C" + user.getString("stadt");}
        if(alternative.isSelected()) {url = url + user.getString("altAdresse") + "%2C" + user.getString("altNummer") + "%2C" + user.getString("altPlz") + "%2C" + user.getString("altStadt");}
        JSONObject rest = new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/"+restaurantId).toString());
        url = url + "&destinations=" + rest.getString("strasse") + "%2C" + rest.getString("nummer") + "%2C" + rest.getString("plz") + "%2C" + rest.getString("stadt");
        String end = "&departure_time=now&key=AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o";
        url= url+end;
        url = url.replaceAll(" ", "%2C");
        JSONObject result = new JSONObject(JSONObjectGET(url).toString());
        if(result.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getInt("value") <= rest.getInt("lieferbereich")) {
            output = true;
        }
        System.out.println("Distanz betraegt: " + result.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getInt("value"));
        return output;
    }




    @FXML
    public void onStandardClick() throws IOException {
        // Fertig
        standard.setToggleGroup(group);
        populateList();
        populateMap();
    }

    @FXML
    public void onAlternativeClick() throws IOException {
        // Fertig
        alternative.setToggleGroup(group);
        populateList();
        populateMap();
    }

    public class RestaurantList {
        private int id;
        private String name;
        private String strasse;
        private String plz;
        private String stadt;
        private double mbw;
        private double lieferkosten;
        private String kategorie;
        private int lieferbereich;

        private double ratingFood;
        private double ratingLieferung;
        private Button ratingButton;
        private Button order;
        private Button fav;

        public RestaurantList() { }

        public RestaurantList(int id, String name, String strasse, String plz,
                              String stadt, double mbw, double lieferkosten,
                              String kategorie, int lieferbereich, double ratingFood,
                              double ratingLieferung, Boolean isFav, int favId, int userId) {
            this.id = id;
            this.name = name;
            this.strasse = strasse;
            this.plz = plz;
            this.stadt = stadt;
            this.mbw = mbw;
            this.lieferkosten = lieferkosten;
            this.kategorie = kategorie;
            this.lieferbereich = lieferbereich;
            this.ratingFood = ratingFood;
            this.ratingLieferung = ratingLieferung;
            this.ratingButton = new Button();
            this.ratingButton.setText("Bewertungen");
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
                        changeScene("Menu.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            this.fav = new Button();
            if(isFav) {
                this.fav.setText("Favorit entfernen");
                this.fav.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            JSONObjectDELETE("http://localhost:8080/fav/del/" + favId);
                            populateList();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                this.fav.setText("Favorit hinzufuegen");
                this.fav.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            JSONObjectPOST("http://localhost:8080/fav/add/" + id + "/" + userId, "null");
                            populateList();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }

        public double getRatingFood() {return ratingFood;}

        public void setRatingFood(double ratingFood) {this.ratingFood = ratingFood;}

        public double getRatingLieferung() {return ratingLieferung;}

        public void setRatingLieferung(double ratingLieferung) {this.ratingLieferung = ratingLieferung;}

        public int getId() {return id;}

        public void setId(int restaurantId) {this.id = restaurantId;}

        public String getName() {return name;}

        public void setName(String name) {this.name = name;}

        public String getStrasse() {return strasse;}

        public void setStrasse(String strasse) {this.strasse = strasse;}

        public String getPlz() {return plz;}

        public void setPlz(String plz) {this.plz = plz;}

        public String getStadt() {return stadt;}

        public void setStadt(String stadt) {this.stadt = stadt;}

        public double getMbw() {return mbw;}

        public void setMbw(double mbw) {this.mbw = mbw;}

        public double getLieferkosten() {return lieferkosten;}

        public void setLieferkosten(double lieferkosten) {this.lieferkosten = lieferkosten;}

        public String getKategorie() {return kategorie;}

        public void setKategorie(String kategorie) {this.kategorie = kategorie;}

        public int getLieferbereich() {return lieferbereich;}

        public void setLieferbereich(int lieferbereich) {this.lieferbereich = lieferbereich;}

        public Button getOrder() {return order;}

        public void setOrder(Button order) {this.order = order;}

        public Button getRatingButton() {return ratingButton;}

        public void setRatingButton(Button ratings) {this.ratingButton = ratings;}

        public Button getFav() {return fav;}

        public void setFav(Button fav) {this.fav = fav;}
    }
}

