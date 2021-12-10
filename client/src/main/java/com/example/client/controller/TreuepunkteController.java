package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    @FXML
    public void onDiscountButtonClick(ActionEvent event) {
    }


    @FXML
    public void onGenerate(ActionEvent event) throws IOException {

        String url ="http://localhost:8080/coupon/send/";

        JSONObjectGET(url + LoginController.email);

    }

    @FXML
    public void onGoBackButtonClick(ActionEvent event) throws IOException {

        Main m = new Main();
        m.ChangeScene("KStartseite.fxml");
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

    @FXML
    public void onAddTreuepunkt(ActionEvent event) throws IOException {


            int EinTreuepunkt = 0;
            EinTreuepunkt = EinTreuepunkt + 1;
            treuepunkte += EinTreuepunkt;
            String url = "http://localhost:8080/user";
            JSONArray jsonArray = new JSONArray(JSONObjectGET(url).toString());
            JSONObject jsonObject = new JSONObject();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                if (jsonObject1.get("userId").equals(userId)) {
                    jsonObject = jsonObject1;
                }
            }
            jsonObject.put("treuepunkte", treuepunkte);
            JSONObjectPOST("http://localhost:8080/user/add", jsonObject.toString());
            initialize();

        }





}
