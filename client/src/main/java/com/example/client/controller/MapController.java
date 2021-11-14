package com.example.client.controller;

import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.MapComponentInitializedListener;
import com.dlsc.gmapsfx.javascript.object.GoogleMap;
import com.dlsc.gmapsfx.javascript.object.LatLong;
import com.dlsc.gmapsfx.javascript.object.MapOptions;
import com.dlsc.gmapsfx.javascript.object.MapTypeIdEnum;
import com.dlsc.gmapsfx.javascript.object.Marker;
import com.dlsc.gmapsfx.javascript.object.MarkerOptions;
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
    private String address;
    private String inputUrl = "http://localhost:8080/restaurant/find/";
    private String urlTest = "http://localhost:8080/restaurant/find/65";
    private JSONObject j;
    private LatLong restaurantLocation;

    @Override
    public void initialize(URL url, ResourceBundle rB) {
        mapView.addMapInitializedListener(this);

        JSONArray jarray = null;
        try {
            jarray = new JSONArray(JSONObjectGET("http://localhost:8080/restaurant").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jarray.toString());
        for (int i = 0; i < jarray.length(); i++) {
            System.out.println(jarray.length());
            JSONObject j = new JSONObject(jarray.getJSONObject(i).toString());
            if (j.get("restaurantId").equals(LoginController.userId)) {
                address = j.get("strasse").toString() + ", " + j.get("plz").toString() + " " + j.get("stadt").toString();

            }
        }
        System.out.println(address);
        if(address == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error!");
            alert.setTitle("Kein Restaurant vorhanden!");
            alert.setContentText("Bitte Restaurant erstellen!");
            alert.showAndWait();
            Main m = new Main();
            try {
                m.ChangeScene("Startseite.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mapInitialized() {

        LatLong restaurantLocation = new LatLong(47.6197, -122.3231);
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(restaurantLocation)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
        map = mapView.createMap(mapOptions);
    }
}
