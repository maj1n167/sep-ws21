package ude.sep.client.controller;

import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.MapComponentInitializedListener;
import com.dlsc.gmapsfx.javascript.object.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @TODO: Favoriten fehlt
 */



public class RestaurantsController extends ConnectionController implements Initializable, MapComponentInitializedListener {

    @FXML
    ToggleGroup group = new ToggleGroup();
    @FXML
    RadioButton standard;
    @FXML
    RadioButton alternative;
    @FXML
    TextField filter;


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

    @FXML
    GoogleMapView mapView;
    private GoogleMap map;


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
        if(standard.isSelected()) {
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
        map.clearMarkers();
        if(standard.isSelected()) {
            String address = VerificationController.standard;
            LatLong latlong = null;

            String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+ address.replaceAll(" ", "%2C")+"&key=AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o";

//        String urlTest = "https://maps.googleapis.com/maps/api/geocode/json?address=Hermannstr.%2C9%2C45327%2CEssen&key=AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o";
            JSONObject result = null;
            try {
                result = new JSONObject(JSONObjectGET(url).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            latlong = new LatLong(  result.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat"),
                    result.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
            map.setCenter(latlong);
            MarkerOptions mOptions = new MarkerOptions();
            mOptions.visible(true);
            mOptions.position(latlong);
            Marker marker = new Marker(mOptions);
            marker.setTitle("Home");
            marker.setPosition(latlong);
            map.addMarker(marker);
        } else {
            LatLong latlong = null;
            String address = VerificationController.alternative;
            String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+ address.replaceAll(" ", "%2C")+"&key=AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o";

//        String urlTest = "https://maps.googleapis.com/maps/api/geocode/json?address=Hermannstr.%2C9%2C45327%2CEssen&key=AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o";
            JSONObject result = null;
            try {
                result = new JSONObject(JSONObjectGET(url).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            latlong = new LatLong(  result.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat"),
                    result.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
            map.setCenter(latlong);
            MarkerOptions mOptions = new MarkerOptions();
            mOptions.visible(true);
            mOptions.position(latlong);
            Marker marker = new Marker(mOptions);
            marker.setTitle("Home");
            marker.setPosition(latlong);
            map.addMarker(marker);
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

            mapView.addMapInitializedListener(this);
            mapView.setKey("AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        mapView.addMapInitializedListener(this);
//        mapView.setKey("AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o");
    }

    @Override
    public void mapInitialized() {
        String address = "";

        if(standard.isSelected()) {
            address = VerificationController.standard;
        }
        if (alternative.isSelected()) {
            address = VerificationController.alternative;
        }
        System.out.println(address);
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(0, 0))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .fullscreenControl(false)
                .mapTypeControl(false)
                .zoomControl(false)
                .zoom(12)
        ;
        map = mapView.createMap(mapOptions);
        LatLong latlong = null;

        String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+ address.replaceAll(" ", "%2C")+"&key=AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o";

//        String urlTest = "https://maps.googleapis.com/maps/api/geocode/json?address=Hermannstr.%2C9%2C45327%2CEssen&key=AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o";
        JSONObject result = null;
        try {
             result = new JSONObject(JSONObjectGET(url).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        latlong = new LatLong(  result.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat"),
                                result.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
        map.setCenter(latlong);
        MarkerOptions mOptions = new MarkerOptions();
        mOptions.visible(true);
        mOptions.position(latlong);
        Marker marker = new Marker(mOptions);
        marker.setTitle("Home");
        marker.setPosition(latlong);
        map.addMarker(marker);
    }

    public void addMarker(String address, int restaurantId) throws IOException {
        LatLong latlong = null;
        JSONObject rest = new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/"+restaurantId).toString());
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+ address.replaceAll(" ", "%2C")+"&key=AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o";

//        String urlTest = "https://maps.googleapis.com/maps/api/geocode/json?address=Hermannstr.%2C9%2C45327%2CEssen&key=AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o";
        JSONObject result = null;
        try {
            result = new JSONObject(JSONObjectGET(url).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        latlong = new LatLong(  result.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat"),
                result.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng"));

        MarkerOptions mOptions = new MarkerOptions();
        mOptions.visible(true);
        mOptions.position(latlong);
        Marker marker = new Marker(mOptions);
        marker.setTitle(rest.getString("name"));
        marker.setPosition(latlong);
        map.addMarker(marker);
    }

    public ObservableList<RestaurantList> getRestaurants() throws IOException {
        ObservableList<RestaurantList> output = FXCollections.observableArrayList();
        output.clear();
        JSONArray allRests = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());

        for(int i=0;i<allRests.length();i++){
            JSONObject curRest = allRests.getJSONObject(i);
            if(filter.getText().equals(curRest.getString("name")) ||
                    filter.getText().equals(curRest.getString("kategorie"))) {


                //Ueberpruefen ob es ein fav ist
                JSONArray allFavs = new JSONArray(JSONObjectGET("http://localhost:8080/fav/find/" + userId).toString());
                boolean isFav = false;
                int id = 0;
                for (int j = 0; j < allFavs.length(); j++) {
                    JSONObject favRest = allFavs.getJSONObject(j);
                    if (curRest.getInt("restaurantId") == (favRest.getInt("restaurantId"))) {
                        isFav = true;
                        id = favRest.getInt("id");
                    }
                }


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

//                String restAddress = curRest.getString("strasse")+ " " + curRest.getString("plz") + " " +curRest.getString("stadt");
//                addMarker(restAddress, curRest.getInt("restaurantId"));
            }
        }

        return output;
    }

    public ObservableList<RestaurantList> getRestaurants(String address) throws IOException {
        //fertig

        ObservableList<RestaurantList> output = FXCollections.observableArrayList();
        output.clear();
        // alle restaurants in einem array
        JSONArray allRests = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());
        // alle favoriten in einem array
        JSONArray allFavs = new JSONArray(JSONObjectGET("http://localhost:8080/fav/find/" + userId).toString());


        for(int i=0;i<allRests.length();i++){
            JSONObject curRest = allRests.getJSONObject(i);
            boolean isNear = false;
            boolean isFav = false;
            int id = 0;
            // ueberpruefung, ob restaurant fav ist
            for (int j = 0; j < allFavs.length(); j++) {
                JSONObject favRest = allFavs.getJSONObject(j);
                    if (curRest.getInt("restaurantId") == (favRest.getInt("restaurantId"))) {
                        isFav = true;
                        id = favRest.getInt("id");
                    }
            }
            //ueberpruefung, ob restaurant near ist
            isNear = isRestaurantNear(curRest.getInt("restaurantId"));

            if(isNear || isFav) {

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
//                String restAddress = curRest.getString("strasse")+ " " + curRest.getString("plz") + " " +curRest.getString("stadt");
//                addMarker(restAddress, curRest.getInt("restaurantId"));
            }
        }
        return output;
    }


    private Boolean isRestaurantNear(int restaurantId) throws IOException {
        // Funktioniert
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
        try {
            populateList();
            populateMap();
        } catch(JSONException e) {
            standard.setToggleGroup(group);
            standard.setSelected(true);
            alternative.setSelected(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Keine Alternativadresse");
            alert.setContentText("Bitte Alternativadresse bestimmen");
            alert.showAndWait();
        }
    }

    @FXML
    public void onFilterButton(ActionEvent actionEvent) throws IOException{

        list.getItems().clear();
        list.setItems(getRestaurants());
        list.refresh();
        // Fertig

//        try {
//
//
//        } catch (JSONException e) {
//
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Keine Alternativadresse");
//            alert.setContentText("Bitte Alternativadresse bestimmen");
//            alert.showAndWait();
//        }
    }

    @FXML
    public void onResetButton(ActionEvent actionEvent) throws IOException {
        filter.setText("");
        populateList();
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

