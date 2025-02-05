package ude.sep.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SonderwünscheBeaController extends ConnectionController implements Initializable {

    private int  besId;

    private int userId;
    
    @FXML
    TextField neuerPreis;
    @FXML
    ListView speisenAusBestellung;
    @FXML
    Label summe;
    @FXML
    Label datum;
    @FXML
    Label kundenId;
    @FXML
    Label bestellId;
    @FXML
    Label sonderwünsche;

    public static double summe1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        besId = SonderwünscheController.bestellungsId;
        System.out.println(besId);
        try {
            JSONObject bestell = new JSONObject(JSONObjectGET("http://localhost:8080/bestellung/find/"+besId).toString());
            summe.setText(String.valueOf(round(bestell.getDouble("summe"),2)));
            summe1 = round(bestell.getDouble("summe"),2);
            datum.setText(bestell.getString("datum"));
            kundenId.setText(String.valueOf(bestell.getInt("userId")));
            userId = bestell.getInt("userId");
            bestellId.setText(String.valueOf(bestell.get("bestellungId")));
            sonderwünsche.setText(bestell.getString("sonderwunsch"));
            JSONArray ary = new JSONArray(bestell.getJSONArray("liste").toString());
            for(int i = 0; i<ary.length(); i++){
                JSONObject jsonObject = ary.getJSONObject(i);
                speisenAusBestellung.getItems().add(jsonObject.get("name") + "  " + "Preis: " + jsonObject.get("preis").toString() +
                        " " + "Beschreibung: " + jsonObject.get("beschreibung"));
            }
            speisenAusBestellung.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    
    
    public void annehmenButton(ActionEvent actionEvent) throws IOException {
        JSONObject curBes1 = new JSONObject(JSONObjectGET("http://localhost:8080/bestellung/find/"+besId).toString());
        String url4 = "http://localhost:8080/user/findbyid/" + userId;
        JSONObject user = new JSONObject(JSONObjectGET(url4).toString());
       double summe = Double.parseDouble(neuerPreis.getText());
       JSONObject object = new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/"+LoginController.userId).toString());
        System.out.println(object);
       double fullPrice =  summe+ summe1;
       double guthaben = user.getDouble("guthaben");
       if(guthaben < summe){
           Alert alert = new Alert (Alert.AlertType.INFORMATION);
           alert.setTitle("");
           alert.setTitle("Ups");
           alert.setContentText("Zu wenig Guthaben");
           alert.showAndWait();
           return;
       }
       guthaben -= summe;
       guthaben = round(guthaben,2);
        user.put("guthaben", guthaben);
        JSONObjectPOST("http://localhost:8080/user/add", user.toString());
        fullPrice = round(fullPrice,2);
        curBes1.put("summe",fullPrice);
       JSONObjectPOST("http://localhost:8080/bestellung/add",curBes1.toString());
       JSONObject jsonObject = new JSONObject(JSONObjectGET("http://localhost:8080/resBes/find/"+LoginController.userId).toString());
        JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("bestellungenList").toString());
        JSONArray jsonArray1 = new JSONArray();
        for(int i =0; i<jsonArray.length(); i++){
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            if(besId != jsonObject1.getInt("bestellungId")){
                jsonArray1.put(jsonObject1);
            }
        }
        jsonObject.put("bestellungenList",jsonArray1);
        System.out.println(jsonObject);
        JSONObjectPOST("http://localhost:8080/resBes/add",jsonObject.toString());
        // Entfernt aus dem zwischen Speicher
        // Hier wird die Bestellung zur BestellHistorie hinzugefügt
        JSONObject bestellHis = new JSONObject(JSONObjectGET("http://localhost:8080/bestellHistorie/find/"+userId).toString());
        System.out.println(bestellHis);
        JSONArray bestellungen = new JSONArray(bestellHis.getJSONArray("bestellungenList").toString());
        JSONObject curBes = new JSONObject(JSONObjectGET("http://localhost:8080/bestellung/find/"+besId).toString());
        bestellungen.put(curBes);
        bestellHis.put("bestellungenList",bestellungen);
        System.out.println(bestellHis);
        JSONObjectPOST("http://localhost:8080/bestellHistorie/add", bestellHis.toString());

        JSONObject user2 = new JSONObject(JSONObjectGET("http://localhost:8080/user/findbyid/"+userId).toString());

        JSONObjectGET("http://localhost:8080/user/send/accept/"+user2.get("email"));

        changeScene("Sonderwünsche.fxml");
    }






    public void ablehnenButton(ActionEvent actionEvent) throws IOException {

        JSONObject curBes1 = new JSONObject(JSONObjectGET("http://localhost:8080/bestellung/find/"+besId).toString());

        String sonderwunsch = "Sonderwunsch abgelehnt";
        curBes1.put("sonderwunsch",sonderwunsch);
        JSONObjectPOST("http://localhost:8080/bestellung/add",curBes1.toString());
        System.out.println(userId);

       JSONObject jsonObject = new JSONObject(JSONObjectGET("http://localhost:8080/resBes/find/"+LoginController.userId).toString());
        System.out.println(jsonObject);

       JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("bestellungenList").toString());
       JSONArray jsonArray1 = new JSONArray();
        for(int i =0; i<jsonArray.length(); i++){
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            if(besId != jsonObject1.getInt("bestellungId")){
                jsonArray1.put(jsonObject1);
            }
        }
        jsonObject.put("bestellungenList",jsonArray1);
        System.out.println(jsonObject);
        JSONObjectPOST("http://localhost:8080/resBes/add",jsonObject.toString());
        // Entfernt aus dem zwischen Speicher
        // Hier wird die Bestellung zur BestellHistorie hinzugefügt
        JSONObject bestellHis = new JSONObject(JSONObjectGET("http://localhost:8080/bestellHistorie/find/"+userId).toString());
        System.out.println(bestellHis);
        JSONArray bestellungen = new JSONArray(bestellHis.getJSONArray("bestellungenList").toString());
        JSONObject curBes = new JSONObject(JSONObjectGET("http://localhost:8080/bestellung/find/"+besId).toString());
        bestellungen.put(curBes);
        bestellHis.put("bestellungenList",bestellungen);
        System.out.println(bestellHis);
        JSONObjectPOST("http://localhost:8080/bestellHistorie/add", bestellHis.toString());

        JSONObject user = new JSONObject(JSONObjectGET("http://localhost:8080/user/findbyid/"+userId).toString());

        JSONObjectGET("http://localhost:8080/user/send/decline/"+user.get("email"));

        changeScene("Sonderwünsche.fxml");
    }

    public void zurückButton(ActionEvent actionEvent) throws IOException {
        changeScene("Sonderwünsche.fxml");
    }
    private double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }

}
