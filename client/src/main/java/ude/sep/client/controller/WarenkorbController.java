package ude.sep.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class WarenkorbController extends ConnectionController {
    @FXML
    ListView<String> listView;
    @FXML
    public Label summe;

    @FXML
    public TextField id;

    @FXML
    public TextField sonderwunsch;

    @FXML
    public Label lieferkosten;

    public int userId;
    public int restaurantid;
    public int treuepunkte;
    public double profuenfer;
    public double lieferkosten2;


    public void initialize() throws IOException {
        userId = LoginController.userId;
        restaurantid = MenuSpeisekarteeController.restaurantId;
        JSONObject jsonObject5 = new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/" + restaurantid).toString());
        JSONObject jsonObject4 = new JSONObject(JSONObjectGET("http://localhost:8080/warenkorb/find/" + userId).toString());
        lieferkosten.setText(jsonObject5.get("lieferkosten").toString());
        lieferkosten2 = jsonObject5.getDouble("lieferkosten");
        double gesamtsumme = jsonObject4.getDouble("summe");
        double round = round(gesamtsumme,2);
        summe.setText(String.valueOf(round));
        JSONArray jsonArray = new JSONArray(jsonObject4.getJSONArray("foodList").toString());
        System.out.println(jsonArray);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject);
            listView.getItems().add(jsonObject.get("name") + "  " + "Preis: " + jsonObject.get("preis").toString() +
                    " " + "Beschreibung: " + jsonObject.get("beschreibung") +" Id: "+jsonObject.get("bestellfoodid"));
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void initialize2() throws IOException{
        userId = LoginController.userId;
        String url1 = "http://localhost:8080/user";
        JSONArray jsonArray =  new JSONArray(JSONObjectGET(url1).toString());
        JSONObject jsonObject = new JSONObject();

        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

            if (jsonObject1.get("userId").equals(userId)){
                jsonObject = jsonObject1;
            }
        }
        treuepunkte=Integer.parseInt(jsonObject.get("treuepunkte").toString());
        while(treuepunkte >= 10) {

            String url2 ="http://localhost:8080/coupon/send/";

            JSONObjectGET(url2 + LoginController.email);

            treuepunkte = treuepunkte - 10;
            String url = "http://localhost:8080/user";
            JSONArray jsonArray1 = new JSONArray(JSONObjectGET(url).toString());
            JSONObject jsonObjectx = new JSONObject();

            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                if (jsonObject1.get("userId").equals(userId)) {
                    jsonObjectx = jsonObject1;
                }
            }
            jsonObject.put("treuepunkte", treuepunkte);
            JSONObjectPOST("http://localhost:8080/user/add", jsonObjectx.toString());
            initialize2();

        }
    }

    @FXML
    public void ichHabCodeButtonClick(ActionEvent event) throws IOException {

        changeScene("Treuepunkte.fxml");
    }

    @FXML
    public void bestellen(ActionEvent actionEvent) throws IOException {
        String x = listView.getSelectionModel().getSelectedItems().toString();
        System.out.println(x);
        String url1 = "http://localhost:8080/warenkorb";
        String url2 = "http://localhost:8080/bestellung/add";
        String url3 = "http://localhost:8080/bestellHistorie/";
        String url4 = "http://localhost:8080/user/findbyid/" + userId;
        String url5 = "http://localhost:8080/restaurant/find/" + restaurantid;

        JSONArray jsonArray = new JSONArray(JSONObjectGET(url1).toString());
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

            if (jsonObject1.get("warenkorbId").equals(userId)) {
                jsonObject = jsonObject1;
            }
        }
        JSONObject restaurant = new JSONObject(JSONObjectGET(url5).toString());
        JSONObject user = new JSONObject(JSONObjectGET(url4).toString());

        if (Double.parseDouble(user.get("guthaben").toString()) >= Double.parseDouble(jsonObject.get("summe").toString()) &&
                Double.parseDouble(jsonObject.get("summe").toString()) >= Double.parseDouble(restaurant.get("mbw").toString())) {
            System.out.println(jsonObject);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            // Sonderwünsche
            System.out.println(date.toString());
            System.out.println(jsonObject.getJSONArray("foodList"));
            JSONArray jsonArray8 = new JSONArray(jsonObject.get("foodList").toString());
            for (int z = 0; z < jsonArray8.length(); z++) {
                JSONObject currentFood = jsonArray8.getJSONObject(z);
                currentFood.put("date", date.toString());
                JSONObjectPOST("http://localhost:8080/bestellFood/add",
                        currentFood.toString());
            }

            JSONArray fObject = new JSONArray();
            JSONArray bestellFood = new JSONArray(JSONObjectGET("http://localhost:8080/bestellFood").toString());
            for (int b = 0; b < bestellFood.length(); b++) {
                JSONObject newFood = bestellFood.getJSONObject(b);
                if (date.toString().equals(newFood.get("date"))) {
                    fObject.put(newFood);
                }
            }


            double gesamtsumme = jsonObject.getDouble("summe");
            gesamtsumme += lieferkosten2;
            JSONObject bestellung = new JSONObject();
            bestellung.put("restaurantId", restaurantid);
            bestellung.put("userId", userId);
            bestellung.put("summe", gesamtsumme);
            bestellung.put("datum", date.toString());
            bestellung.put("liste", fObject);

            if(sonderwunsch.getText().equals("")) {
                JSONObjectPOST(url2, bestellung.toString());
                double neuesGuthaben = Double.parseDouble(user.get("guthaben").toString()) - Double.parseDouble(jsonObject.get("summe").toString()) - Double.parseDouble(restaurant.get("lieferkosten").toString());
                user.put("guthaben", neuesGuthaben);
                JSONObjectPOST("http://localhost:8080/user/add", user.toString());

            }else {
                //Falls Sonderwunsch vorhanden
                bestellung.put("sonderwunsch",sonderwunsch.getText());
                JSONObjectPOST(url2, bestellung.toString());
                System.out.println(bestellung+"bestellung ");
                JSONArray jsonObject1 = new JSONArray(JSONObjectGET("http://localhost:8080/bestellung").toString());
                JSONObject curBes = new JSONObject();
                for(int i = 0; i<jsonObject1.length(); i++){
                    JSONObject jsonObject2 = jsonObject1.getJSONObject(i);
                    System.out.println(jsonObject2);
                    System.out.println("Vergleich");
                    System.out.println(bestellung);
                    if(jsonObject2.get("datum").equals(bestellung.get("datum"))){
                        curBes = jsonObject2;
                        System.out.println(curBes);

                    }
                }
                JSONObject resBes = new JSONObject(JSONObjectGET("http://localhost:8080/resBes/find/"+restaurantid).toString());
                System.out.println(resBes);
                JSONArray jsonArray1 = new JSONArray(resBes.getJSONArray("bestellungenList").toString());
                jsonArray1.put(curBes);
                resBes.put("bestellungenList",jsonArray1);

                System.out.println(resBes);
                JSONObjectPOST("http://localhost:8080/resBes/add",resBes.toString());
                //Sonderwunsch an Restaurant weitergeleitet

                JSONArray y = new JSONArray();
                JSONObject Warenkorb = new JSONObject(JSONObjectGET("http://localhost:8080/warenkorb/find/"+userId).toString());
                JSONArray jsonArray3 = new JSONArray(Warenkorb.get("foodList").toString());
                System.out.println(jsonArray3);
                for (int i = 0;i<jsonArray3.length(); i++){
                    JSONObject jsonObject3 = jsonArray3.getJSONObject(i);
                    System.out.println(jsonObject3);
                    JSONObjectDELETE("http://localhost:8080/warenfood/delete/"+jsonObject3.get("bestellfoodid"));
                }

                JSONObject emptyWarenkorb = new JSONObject();
                emptyWarenkorb.put("warenkorbId", userId);
                emptyWarenkorb.put("summe", 0);
                emptyWarenkorb.put("foodList", y);

                JSONObjectPOST(url1 + "/add", emptyWarenkorb.toString());

                changeScene("Bewertung.fxml");
                return;
            }

            JSONArray jsonArray1 = new JSONArray(JSONObjectGET(url3).toString());
            JSONArray bestellungen = new JSONArray();

            JSONArray jsonArray2 = new JSONArray(JSONObjectGET("http://localhost:8080/bestellung").toString());

            for (int j = 0; j < jsonArray2.length(); j++) {
                JSONObject jsonObject1 = jsonArray2.getJSONObject(j);
                if (jsonObject1.get("userId").equals(userId)) {
                    bestellungen.put(jsonObject1);
                }
            }
            System.out.println(bestellung);


            //Treuepunkte Anfang - LG

            initialize2();
            JSONObject jsonObject31 = new JSONObject(JSONObjectGET("http://localhost:8080/warenkorb/find/" + userId).toString());
            profuenfer = Double.parseDouble(jsonObject31.get("summe").toString());
            while (profuenfer >= 5) {
                int EinTreuepunkt = 0;
                EinTreuepunkt = EinTreuepunkt + 1;
                treuepunkte += EinTreuepunkt;
                String url = "http://localhost:8080/user";
                JSONArray jsonArrayABC = new JSONArray(JSONObjectGET(url).toString());
                JSONObject jsonObjectABC = new JSONObject();

                for (int i = 0; i < jsonArrayABC.length(); i++) {
                    JSONObject jsonObjectXYZ = jsonArrayABC.getJSONObject(i);

                    if (jsonObjectXYZ.get("userId").equals(userId)) {
                        jsonObjectABC = jsonObjectXYZ;
                    }
                }
                jsonObjectABC.put("treuepunkte", treuepunkte);
                JSONObjectPOST("http://localhost:8080/user/add", jsonObjectABC.toString());
                profuenfer = profuenfer - 5;
                initialize2();
            }

            //Treuepunkte Ende - LG


            JSONObject jsonObject2 = new JSONObject();
            for (int i = 0; i < jsonArray1.length(); i++) {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);

                if (jsonObject1.get("bestellHisId").equals(userId)) {
                    jsonObject2 = jsonObject1;
                }
            }
            jsonObject2.put("bestellHis", userId);
            jsonObject2.put("bestellungenList", bestellungen);

            JSONObjectPOST("http://localhost:8080/bestellHistorie/add", jsonObject2.toString());
            System.out.printf("done");
            JSONArray y = new JSONArray();
            JSONObject jsonObject1 = new JSONObject(JSONObjectGET("http://localhost:8080/warenkorb/find/"+userId).toString());
            JSONArray jsonArray3 = new JSONArray(jsonObject1.get("foodList").toString());
            System.out.println(jsonArray3);
            for (int i = 0;i<jsonArray3.length(); i++){
                JSONObject jsonObject3 = jsonArray3.getJSONObject(i);
                System.out.println(jsonObject3);
                JSONObjectDELETE("http://localhost:8080/warenfood/delete/"+jsonObject3.get("bestellfoodid"));
            }

            JSONObject emptyWarenkorb = new JSONObject();
            emptyWarenkorb.put("warenkorbId", userId);
            emptyWarenkorb.put("summe", 0);
            emptyWarenkorb.put("foodList", y);

            JSONObjectPOST(url1 + "/add", emptyWarenkorb.toString());

            JSONObjectGET("http://localhost:8080/user/send/orderVerification/" + user.get("email"));
            addDeliveryTime(RestaurantsController.id, RestaurantsController.distance);
            changeScene("Bewertung.fxml");

        } else {
            //   Mindestbestellwert oder Guthaben reicht nicht aus
            System.out.println("zu wenig Guthaben");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Mbw nicht erreicht oder nicht genügend Guthaben ");
            alert.setContentText("Mbw nicht erreicht oder nicht genügend Guthaben");
            alert.showAndWait();
        }

    }


    public void deleteButton() throws IOException{
        String url1 = "http://localhost:8080/warenkorb/find/"+userId;
        JSONObject jsonObject1 = new JSONObject(JSONObjectGET(url1).toString());
        JSONArray jsonArray = new JSONArray(jsonObject1.get("foodList").toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int x = Integer.parseInt(id.getText());
            if(jsonObject.get("bestellfoodid").equals(x)) {
                double preis = jsonObject1.getDouble("summe");
                preis -= jsonObject.getDouble("preis");
                jsonObject1.put("summe",preis);
                JSONObjectPOST("http://localhost:8080/warenkorb/add",jsonObject1.toString());
                JSONObjectDELETE("http://localhost:8080/warenfood/delete/"+jsonObject.get("bestellfoodid"));
                listView.getItems().clear();
                initialize();
            }
        }




    }

    private double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }


    public void zurückButton() throws IOException {
        changeScene("Menu.fxml");
    }

    public void FertigButton(ActionEvent actionEvent) throws IOException {
        changeScene("KStartseite.fxml");
    }
}
