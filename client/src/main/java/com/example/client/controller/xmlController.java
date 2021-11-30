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
  public   int katID1;
  public  String newKategorie;
  public int kategorieId;

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

        System.out.println(jsonObject);

        JSONObject  gerichte = (JSONObject) jsonObject.get("speisekarte");
        JSONObject kategorie = (JSONObject) gerichte.get("gerichte");
        System.out.println(kategorie);
        JSONArray jsonArray = new JSONArray(kategorie.names());


        for(int i=0; i<jsonArray.length(); i++){
            JSONArray jsonArray1 = kategorie.names();
            newKategorie =(String) jsonArray1.get(i);
            System.out.println(jsonArray1.get(i));
            JSONObject x = (JSONObject) kategorie.get(jsonArray.get(i).toString());
            System.out.println(x.get("gericht"));
            JSONArray jsonArray2 = x.optJSONArray("gericht");
            //Wenn ein jsonArray vorhanden ist
            if (jsonArray2 != null) {
                JSONArray kateg1 = new JSONArray(JSONObjectGET("http://localhost:8080/kategorie").toString());
                for (int j = 0; j < kateg1.length(); j++) {
                    JSONObject jsonObject1 = kateg1.getJSONObject(j);
                    if (jsonObject1.get("kategorie").equals(jsonArray1.get(i)) && jsonObject1.get("menuId").equals(id) ) {
                        kategorieId = (int) jsonObject1.get("kategorieId");
                    }
                }
                if(kategorieId == 0){
                    String allFoods = "{" +
                            "\"kategorie\":" + "\"" + newKategorie + "\"" + "\n," +
                            "\"menuId\":" + id + "\n}";

                    JSONObjectPOST("http://localhost:8080/kategorie/add", allFoods);
                }
                JSONArray kateg2 = new JSONArray(JSONObjectGET("http://localhost:8080/kategorie").toString());
                for (int j = 0; j < kateg2.length(); j++) {
                    JSONObject jsonObject1 = kateg2.getJSONObject(j);
                    if (jsonObject1.get("kategorie").equals(jsonArray1.get(i)) && jsonObject1.get("menuId").equals(id)) {
                        kategorieId = (int) jsonObject1.get("kategorieId");
                    }
                }
                for (int j = 0; j < jsonArray2.length(); j++) {
                    JSONObject y = jsonArray2.optJSONObject(j);
                    try{
                        String preis = y.getString("preis");
                        char [] b =preis.toCharArray();
                        for(int n = 0;  n<b.length; n++){
                            if(b[n] == ','){
                                b[n]= '.';


                            }

                        }
                        String string = new String(b);

                        double preis1 = Double.parseDouble(string);
                        y.put("preis", preis1);

                    }catch (Exception e){

                    }
                    Text text = new Text(y.get("name")+"\n");
                    textFlow.getChildren().add(text);
                    y.put("menuId",id);
                    y.put("kategorieId", kategorieId);
                    System.out.println(y.toString());

                    JSONObjectPOST("http://localhost:8080/food/add", y.toString());
                }
                System.out.println("Daten gesendet");
                String url1 = "http://localhost:8080/food";
                String allFoods = "{" +
                        "\"kategorieId\":" + kategorieId + "\n," +
                        "\"kategorie\":" + "\"" + newKategorie + "\"" + "\n," +
                        "\"menuId\":" + id + "\n," +
                        "\"foods\":";
                JSONArray jsonArray3 = new JSONArray();
                System.out.println(allFoods);
                System.out.println(kategorieId);
                JSONArray jsonArrayA = new JSONArray(JSONObjectGET(url1).toString());
                System.out.println(jsonArrayA);
                for (int v = 0; v < jsonArrayA.length(); v++) {
                    JSONObject jsonObject5 = jsonArrayA.getJSONObject(v);
                    System.out.println(jsonObject5);
                    if (jsonObject5.get("kategorieId").equals(kategorieId) && jsonObject5.get("menuId").equals(id)) {
                        jsonArray3.put(jsonObject5);

                    }
                }
                System.out.println(jsonArray3);
                System.out.println(allFoods+jsonArray3.toString());
                JSONObjectPOST("http://localhost:8080/kategorie/add", allFoods + jsonArray3.toString() + "}");
                kategorieId=0;
            }
            else{
                //Wenn es kein JSONArray gibt
                JSONArray kateg = new JSONArray(JSONObjectGET("http://localhost:8080/kategorie").toString());
                for (int j = 0; j < kateg.length(); j++) {
                    JSONObject jsonObject1 = kateg.getJSONObject(j);
                    if (jsonObject1.get("kategorie").equals(jsonArray1.get(i))&& jsonObject1.get("menuId").equals(id)) {
                        kategorieId = (int) jsonObject1.get("kategorieId");
                    }
                }
                if(kategorieId == 0){
                    String allFoods = "{" +
                            "\"kategorie\":" + "\"" + newKategorie + "\"" + "\n," +
                            "\"menuId\":" + id + "\n}";

                    JSONObjectPOST("http://localhost:8080/kategorie/add", allFoods);
                }


                JSONObject newFood = x.getJSONObject("gericht");
                System.out.println(newKategorie);
                System.out.println("xxx");
                JSONArray kateg2 = new JSONArray(JSONObjectGET("http://localhost:8080/kategorie").toString());
                for (int j = 0; j < kateg2.length(); j++) {
                    JSONObject jsonObject1 = kateg2.getJSONObject(j);
                    System.out.println(jsonObject1.get("kategorie"));

                    if (jsonObject1.get("kategorie").equals(newKategorie) && jsonObject1.get("menuId").equals(id)) {
                        katID1 = (int) jsonObject1.get("kategorieId");
                        newFood.put("kategorieId", jsonObject1.get("kategorieId"));
                        newFood.put("menuId", id);

                       try{
                           String preis = newFood.getString("preis");
                           char [] b =preis.toCharArray();
                           for(int n = 0;  n<b.length; n++){
                               if(b[n] == ','){
                                   b[n]= '.';


                               }

                           }
                           String string = new String(b);

                           double preis1 = Double.parseDouble(string);
                           newFood.put("preis", preis1);

                       }catch (Exception e){

                        }

                        System.out.println(newFood);
                        JSONObjectPOST("http://localhost:8080/food/add", newFood.toString());
                        }

                        }

                String url1 = "http://localhost:8080/food";
                String allFoods = "{" +
                        "\"kategorieId\":" + katID1 + "\n," +
                        "\"kategorie\":" + "\"" + newKategorie + "\"" + "\n," +
                        "\"menuId\":" + id + "\n," +
                        "\"foods\":";
                JSONArray jsonArray3 = new JSONArray();
                System.out.println(allFoods);
                System.out.println(katID1);
                JSONArray jsonArrayA = new JSONArray(JSONObjectGET(url1).toString());
                System.out.println(jsonArrayA);
                for (int v = 0; v < jsonArrayA.length(); v++) {
                    JSONObject jsonObject5 = jsonArrayA.getJSONObject(v);
                    System.out.println(jsonObject5);
                    if (jsonObject5.get("menuId").equals(id) && jsonObject5.get("kategorieId").equals(katID1)) {
                        Text text = new Text(jsonObject5.get("name")+"\n");
                        textFlow.getChildren().add(text);
                        jsonArray3.put(jsonObject5);
                    }
                }


                System.out.println(allFoods + jsonArray3.toString() + "}");
                System.out.println("H");
                JSONObjectPOST("http://localhost:8080/kategorie/add", allFoods + jsonArray3.toString() + "}");
                kategorieId = 0;
            }

        }



        /**
        JSONArray jsonArray= new JSONArray(jsonObject.get("foods").toString());
        JSONObject jsonObject2;
        for(int i = 0; i< jsonArray.length(); i++){
            jsonObject2 = jsonArray.getJSONObject(i);
            jsonObject2.put("menuId",id);
                allFood = allFood+
                        "Name: " + jsonObject2.get("name").toString() + "\nBeschreibung : "+jsonObject2.get("beschreibung")+
            "\nPreis : "+jsonObject2.get("preis")+"$"+"\nKategorie : "+jsonObject2.get("kategorie")+"\n\n";

        }
        jsonObject.put("foods",jsonArray);
        Text text = new Text(allFood);
        textFlow.getChildren().add(text);
        jsonObject.put("menuId",id);
       this.fertigeSpeiseK=jsonObject;
        bufferedReader.close();
         **/

    }

    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("Speisekarte.fxml");
    }


    public void fertigButton() throws IOException {
        String url = "http://localhost:8080/menu/update";
        String json = "{\"menuId\":" + id + ",\n" +
                "\"kategories\": [] }";
        JSONObjectPUT(url, json);
        String url1 = "http://localhost:8080/kategorie";


        String allFoods = "{\"menuId\":" + id+ "\n," +
                "\"kategories\":";
        JSONArray jsonArray1 = new JSONArray();


        JSONArray jsonArray4 = new JSONArray(JSONObjectGET(url1).toString());
        for (int i = 0; i < jsonArray4.length(); i++) {
            JSONObject jsonObject = jsonArray4.getJSONObject(i);
            if (jsonObject.get("menuId").equals(id)) {
                jsonArray1.put(jsonObject);
            }
        }
        allFoods =allFoods+jsonArray1.toString()+"}";
        System.out.println(allFoods);
        JSONObjectPOST("http://localhost:8080/menu/add", allFoods);
        System.out.println("Daten gesendet");

        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setTitle("Erfolg!");
        alert.setContentText("Speisekarte erfolgreich erstellt");
        alert.showAndWait();
        }
    }


