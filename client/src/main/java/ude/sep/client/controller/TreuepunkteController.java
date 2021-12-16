package ude.sep.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class TreuepunkteController extends ConnectionController {

    @FXML
    TextField DiscountText;

    @FXML
    public int userId;
    public int treuepunkte;
    public double discountedsumme;

    @FXML
    public void onDiscountButtonClick(ActionEvent event) throws IOException {

        String url = "http://localhost:8080/coupon/delete/";
        String url1 = "http://localhost:8080/coupon";
        JSONArray jsonArray = new JSONArray(JSONObjectGET(url1).toString());
        Boolean found = false;
        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.get("coupon").equals(DiscountText.getText())) {
                JSONObjectDELETE(url + jsonObject.get("id"));
                found = true;
            }
        }
        if(!found) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Dieser Code ist ungueltig!");
            alert.setContentText("Dieser Code ist ungueltig!");
            alert.showAndWait();
            return;
        }

        JSONObject jsonObject4 = new JSONObject(JSONObjectGET("http://localhost:8080/warenkorb/find/"+userId).toString());
        discountedsumme = Double.parseDouble(jsonObject4.get("summe").toString());
        discountedsumme = discountedsumme * 0.9;
        jsonObject4.put("summe", discountedsumme);
        JSONObjectPOST("http://localhost:8080/warenkorb/add", jsonObject4.toString());
        initialize();
        changeScene("Warenkorb.fxml");
    }

    @FXML
    public void onGoBackButtonClick(ActionEvent event) throws IOException {

        changeScene("Warenkorb.fxml");
    }

    public void initialize() throws IOException{
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
        if(treuepunkte >= 10) {

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
            initialize();

        }
    }

//    @FXML
//    public void onAddTreuepunkt(ActionEvent event) throws IOException {
//
//
//            int EinTreuepunkt = 0;
//            EinTreuepunkt = EinTreuepunkt + 1;
//            treuepunkte += EinTreuepunkt;
//            String url = "http://localhost:8080/user";
//            JSONArray jsonArray = new JSONArray(JSONObjectGET(url).toString());
//            JSONObject jsonObject = new JSONObject();
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
//                if (jsonObject1.get("userId").equals(userId)) {
//                    jsonObject = jsonObject1;
//                }
//            }
//            jsonObject.put("treuepunkte", treuepunkte);
//            JSONObjectPOST("http://localhost:8080/user/add", jsonObject.toString());
//            initialize();
//
//        }





}
