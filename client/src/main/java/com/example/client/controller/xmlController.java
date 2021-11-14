package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.net.URL;
import java.security.cert.Extension;
import java.util.List;
import java.util.ResourceBundle;

public class xmlController extends ConnectionController implements Initializable {
@FXML
public TextFlow textFlow;
public static String path;
public static int id;
public static JSONObject fertigeSpeiseK;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    this.id = LoginController.userId;
    }




    @FXML
    public void öffnenButton(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
       //fileChooser.setInitialDirectory(new File()); Für genaure paths
        File selectedFile = fileChooser.showOpenDialog(null);
        // fileChooser.getExtensionFilters().addAll(
           //     new FileChooser.ExtensionFilter("xml File","*.xml"));

        if(selectedFile != null){
           // listview.getItems().add(selectedFile.getAbsolutePath());
            System.out.println(selectedFile.getAbsolutePath().toString());
            path=selectedFile.getAbsolutePath().toString();
        } else {
            System.out.println("file is not valid");
        }
    }

    public void speichernButton() throws IOException {
        String allFood ="Ihre Speisen:\n\n ";
        File xmlFile = new File(path);
        Reader filereader = new FileReader(xmlFile);
        BufferedReader bufferedReader= new BufferedReader(filereader);

        StringBuilder sb = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line!= null){
            sb.append(line).append("\n");
            line= bufferedReader.readLine();
        }
        String xml2String = sb.toString();
        JSONObject jsonObject =  XML.toJSONObject(xml2String);

        JSONArray jsonArray= new JSONArray(jsonObject.get("foods").toString());
        JSONObject jsonObject2;
        for(int i = 0; i< jsonArray.length(); i++){
            jsonObject2 = jsonArray.getJSONObject(i);
            jsonObject2.put("menuId",id);
                allFood = allFood+
                        "Name: " + jsonObject2.get("name").toString() + "\nBeschreibung : "+jsonObject2.get("beschreibung")+
            "\nPreis : "+jsonObject2.get("preis")+"\nKategorie : "+jsonObject2.get("kategorie")+"\n\n";

        }
        jsonObject.put("foods",jsonArray);
        Text text = new Text(allFood);
        textFlow.getChildren().add(text);
        jsonObject.put("menuId",id);
       this.fertigeSpeiseK=jsonObject;
        bufferedReader.close();

    }

    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("Speisekarte.fxml");
    }


    public void fertigButton() throws IOException {
        String url = "http://localhost:8080/menu/update";
        String json = "{\"menuId\":" + id + ",\n" +
                "\"foods\": [] }";
        JSONObjectPUT(url, json);
        String url1 = "http://localhost:8080/food";
        JSONArray jsonArray = new JSONArray(JSONObjectGET(url1).toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.get("menuId").equals(id)) {
                JSONObjectDELETE("http://localhost:8080/food/delete/"+jsonObject.get("foodId").toString());
            }
        }
        JSONArray newFood = new JSONArray(fertigeSpeiseK.getJSONArray("foods"));

        for(int x = 0; x<newFood.length(); x++){
            JSONObject currentFood = newFood.getJSONObject(x);
            JSONObjectPOST("http://localhost:8080/food/add",currentFood.toString());
        }

        String allFoods = "{\"menuId\":" + id+ "\n," +
                "\"foods\":";
        JSONArray jsonArray1 = new JSONArray();


        JSONArray jsonArray4 = new JSONArray(JSONObjectGET(url1).toString());
        for (int i = 0; i < jsonArray4.length(); i++) {
            JSONObject jsonObject = jsonArray4.getJSONObject(i);
            if (jsonObject.get("menuId").equals(id)) {
                jsonArray1.put(jsonObject);
            }
        }
        allFoods =allFoods+jsonArray1.toString()+"}";
        JSONObjectPOST("http://localhost:8080/menu/add", allFoods);
        System.out.println("Daten gesendet");

        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setTitle("Erfolg!");
        alert.setContentText("Speisekarte erfolgreich erstellt");
        alert.showAndWait();
        }
    }


