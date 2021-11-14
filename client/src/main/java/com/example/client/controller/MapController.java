package com.example.client.controller;

import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.MapComponentInitializedListener;
import com.dlsc.gmapsfx.javascript.JavascriptArray;
import com.dlsc.gmapsfx.javascript.object.GoogleMap;
import com.dlsc.gmapsfx.javascript.object.LatLong;
import com.dlsc.gmapsfx.javascript.object.MapOptions;
import com.dlsc.gmapsfx.javascript.object.MapTypeIdEnum;
import com.dlsc.gmapsfx.javascript.object.Marker;
import com.dlsc.gmapsfx.javascript.object.MarkerOptions;
import com.dlsc.gmapsfx.service.directions.DirectionsGeocodedWaypointStatus;
import com.dlsc.gmapsfx.service.geocoding.GeocoderStatus;
import com.dlsc.gmapsfx.service.geocoding.GeocodingResult;
import com.dlsc.gmapsfx.service.geocoding.GeocodingService;
import com.dlsc.gmapsfx.service.geocoding.GeocodingServiceCallback;
import com.example.client.Main;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//Klasse erstellt zum testen der funktionen der App damit das später in die eigentliche View integriert werden kann
public class MapController extends ConnectionController implements Initializable, MapComponentInitializedListener {

    @FXML
    GoogleMapView mapView;
    private GoogleMap map;
    private String addresse;
    private String addresseTest;
    private JSONObject j;
    GeocodingService geocodingService;

    @Override
    public void initialize(URL url, ResourceBundle rB) {
        mapView.addMapInitializedListener(this);
        mapView.setKey("AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o");
        addresseTest = "Hermannstr. 9, 45327 Essen";

    }

    @Override
    public void mapInitialized() {
        try {
            restaurantCreated();
        } catch (IOException e) {
            e.printStackTrace();
        }

        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(0, 0))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(17)
        ;
        map = mapView.createMap(mapOptions);
        MarkerOptions mOptions = new MarkerOptions();
        mOptions.visible(true);

        geocodingService.geocode(addresse, (GeocodingResult[] results, GeocoderStatus status) -> {
//        geocodingService.geocode(addresseTest, (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latlong = null;
            if (status == GeocoderStatus.ZERO_RESULTS) {
                System.out.println("noresult");
                return;
            } else if (results.length > 1) {
                latlong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                System.out.println("morethan1result");
                System.out.println(latlong.toString());
            } else {
                latlong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                System.out.println("1result");
                System.out.println(latlong.toString());
            }
            map.setCenter(latlong);
            mOptions.position(latlong);
            Marker marker = new Marker(mOptions);
            marker.setTitle(j.get("name").toString());
            marker.setPosition(latlong);
            map.addMarker(marker);
        });
    }

        public void restaurantCreated() throws IOException {
            JSONArray jarray = null;
            try {
                jarray = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(jarray.toString());
            for (int i = 0; i < jarray.length(); i++) {
                System.out.println(jarray.length());
                j = new JSONObject(jarray.getJSONObject(i).toString());
                if (j.get("restaurantId").equals(LoginController.userId)) {
                    addresse = j.get("strasse").toString() + ", " + j.get("plz").toString() + " " + j.get("stadt").toString();

                }
            }
            System.out.println(addresse);
            if (addresse == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error!");
                alert.setTitle("Kein Restaurant vorhanden!");
                alert.setContentText("Bitte Restaurant erstellen!");
                alert.showAndWait();
                Main m = new Main();
                m.ChangeScene("Startseite.fxml");
            }
        }

        @FXML
        public void onButtonClick () throws IOException {
            // Change Scenes
            Main m = new Main();
            m.ChangeScene("Startseite.fxml");
        }
    }

