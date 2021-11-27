package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.controlsfx.control.action.Action;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddFoodController extends ConnectionController implements Initializable {
    @FXML
    private Button bearbeiten;
    @FXML
    private Button speichern;
    @FXML
    private Button dateiXml;
    @FXML
    private Button dateiUrl;
    @FXML
    private TextField kategorie;
    @FXML
    private TextField name;
    @FXML
    private TextField beschreibung;
    @FXML
    private TextField preis;

    private int userId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.userId = LoginController.userId;
    }


    @FXML
    public void speichernClick() throws IOException {
        String Url = "http://localhost:8080/food/add";
        JSONArray jsonArray = new JSONArray(JSONObjectGET("http://localhost:8080/kategorie").toString());
        int katID = 0;
        int katID1 = 0;
        if (name.getText().equals("") || beschreibung.getText().equals("") || preis.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Error: Fehlende Zeile");
            alert.setContentText("Bitte füllen Sie alle Felder aus");

            alert.showAndWait();
        }else{
            // Wenn die Kategorie schon Vorhaden ->

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.get("kategorie").equals(kategorie.getText()) && jsonObject.get("menuId").equals(userId)) {
                        int kategorieId = (int) jsonObject.get("kategorieId");
                        katID = kategorieId;
                        String data =
                                "{\"name\": \"" + name.getText() + "\",\n" +
                                        "        \"beschreibung\": \"" + beschreibung.getText() + "\",\n" +
                                        "        \"preis\":" + Double.parseDouble(preis.getText()) + ",\n" +
                                        "        \"kategorie\": \"" + kategorie.getText() + "\",\n" +
                                        " \"kategorieId\": \"" + kategorieId + "\",\n" +
                                        "\"menuId\":" + userId + "}";
                        JSONObjectPOST(Url, data);
                        System.out.println("POST FOOD");


                        String url1 = "http://localhost:8080/food";
                        String allFoods =
                                "{\"kategorieId\":"+katID+"\n,"+
                                "\"kategorie\":" + "\"" + kategorie.getText() + "\"" + "\n," +
                                "\"menuId\":" + userId + "\n," +
                                "\"foods\":";
                        JSONArray jsonArray1 = new JSONArray();
                        JSONArray jsonArray3 = new JSONArray(JSONObjectGET(url1).toString());
                        for (int j = 0; j < jsonArray3.length(); j++) {
                            JSONObject jsonObject6 = jsonArray3.getJSONObject(j);
                            if (jsonObject6.get("menuId").equals(userId) && jsonObject6.get("kategorieId").equals(katID)) {
                                jsonArray1.put(jsonObject6);
                            }
                        }
                        System.out.println(allFoods + jsonArray1 + "}");
                        System.out.println("H1");
                        JSONObjectPOST("http://localhost:8080/kategorie/add", allFoods + jsonArray1.toString() + "}");
                        System.out.println("A1"+"POST KATEGORIE");


                        JSONArray newJsonArray1 = new JSONArray();
                        JSONArray newJsonArray = new JSONArray((JSONObjectGET("http://localhost:8080/kategorie")).toString());
                        System.out.println(newJsonArray);
                        for (int x = 0; x < newJsonArray.length(); x++) {
                            JSONObject jsonObject0 = newJsonArray.getJSONObject(x);
                            if (jsonObject0.get("menuId").equals(userId)) {
                                newJsonArray1.put(jsonObject0);
                                System.out.println(jsonObject0);
                            }
                        }
                        String allKata = "{\"menuId\":" + userId + "\n," +
                                "\"kategories\":";

                        System.out.println(allKata + newJsonArray1.toString() + "}");
                        JSONObjectPOST("http://localhost:8080/menu/add", allKata + newJsonArray1.toString() + "}");
                        System.out.println("POST MENU");


                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Speise hinzugefügt");
                        alert.setContentText("Speise wurde erfolgreich hinzugefügt");


                        alert.showAndWait();
                        return;
                    }
                }

            }
                    //falls die Kategorie nicht existiert
                String katag = "{"+
                "\"kategorie\":" + "\"" + kategorie.getText() + "\"" + "\n," +
                "\"menuId\":" + userId + "\n,"+
                 "\"foods\":[]}";
        System.out.println(katag);
        JSONObjectPOST("http://localhost:8080/kategorie/add",katag);
        System.out.println("Post ketegorie");



                JSONArray kja = new JSONArray(JSONObjectGET("http://localhost:8080/kategorie").toString());

                for (int j = 0; j < kja.length(); j++) {
                    JSONObject kob = kja.getJSONObject(j);
                    if (kob.get("menuId").equals(userId) && kob.get("kategorie").equals(kategorie.getText())) {
                        katID1= kob.getInt("kategorieId");

                        String data =
                                "{\"name\": \"" + name.getText() + "\",\n" +
                                        "        \"beschreibung\": \"" + beschreibung.getText() + "\",\n" +
                                        "        \"preis\":" + Double.parseDouble(preis.getText()) + ",\n" +
                                        "        \"kategorie\": \"" + kategorie.getText() + "\",\n" +
                                        " \"kategorieId\": \"" + kob.get("kategorieId") + "\",\n" +
                                        "\"menuId\":" + userId + "}";


                        JSONObjectPOST("http://localhost:8080/food/add", data);
                        System.out.println("Post Food");
                    }
                }

        String url1 = "http://localhost:8080/food";
        String allFoods = "{"+
                "\"kategorieId\":"+ katID1+"\n,"+
                "\"kategorie\":" + "\"" + kategorie.getText() + "\"" + "\n," +
                "\"menuId\":" + userId + "\n," +
                "\"foods\":";
                JSONArray jsonArray1 = new JSONArray();


                JSONArray jsonArrayA = new JSONArray(JSONObjectGET(url1).toString());
                for (int i = 0; i < jsonArrayA.length(); i++) {
                    JSONObject jsonObject = jsonArrayA.getJSONObject(i);
                    if (jsonObject.get("menuId").equals(userId) && jsonObject.get("kategorieId").equals(katID1)) {
                        jsonArray1.put(jsonObject);
                    }
                }


                System.out.println(allFoods + jsonArray1.toString() + "}");
                System.out.println("H");
                JSONObjectPOST("http://localhost:8080/kategorie/add", allFoods + jsonArray1.toString() + "}");
                System.out.println("A POST Kategorie");

                JSONArray newJsonArray1 = new JSONArray();
                JSONArray newJsonArray = new JSONArray((JSONObjectGET("http://localhost:8080/kategorie")).toString());
                System.out.println(newJsonArray);
                for (int i = 0; i < newJsonArray.length(); i++) {
                    JSONObject jsonObject = newJsonArray.getJSONObject(i);
                    if (jsonObject.get("menuId").equals(userId)) {
                        newJsonArray1.put(jsonObject);
                        System.out.println(jsonObject);
                    }
                }
                String allKata = "{\"menuId\":" + userId + "\n," +
                        "\"kategories\":";

                System.out.println(allKata + newJsonArray1.toString() + "}");
                JSONObjectPOST("http://localhost:8080/menu/add", allKata + newJsonArray1.toString() + "}");
                System.out.println("done POST MENU");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Speise hinzugefügt");
                alert.setContentText("Speise wurde erfolgreich hinzugefügt");

            }






    @FXML
    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("Speisekarte.fxml");
    }


    public void fertigButton(ActionEvent event) throws IOException {
        String url1 = "http://localhost:8080/food";
        String allFoods = "{\"menuId\":" + userId+ "\n," +
                "\"katagories\":";
        JSONArray jsonArray1 = new JSONArray();


        JSONArray jsonArray = new JSONArray(JSONObjectGET(url1).toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.get("menuId").equals(userId)) {
                jsonArray1.put(jsonObject);
            }
        }
        allFoods =allFoods+jsonArray1.toString()+"}";
        JSONObjectPOST("http://localhost:8080/menu/add", allFoods);
        Main m = new Main();
        m.ChangeScene("Speisekarte.fxml");
    }

    @FXML
    public void uploadImage(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();


        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");

        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");

        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");

        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");

        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        File file = fileChooser.showOpenDialog(null);
    }
}
