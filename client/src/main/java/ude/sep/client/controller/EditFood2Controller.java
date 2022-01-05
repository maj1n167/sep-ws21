package ude.sep.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;


public class EditFood2Controller extends ConnectionController  {

    @FXML
    TextField name;

    @FXML
    TextField  beschreibung;

    @FXML
    TextField preis;
    @FXML
    ImageView imageview;
    @FXML
    TextField path;
    @FXML
    Button uploadImage;
    @FXML
    Label kategorie;

    public int foodId;

    JSONObject food;



    public  void initialize() {
        try {
            food = EditFoodController.food;
            JSONArray jsonArray2 = new JSONArray(JSONObjectGET("http://localhost:8080/food").toString());
            System.out.println(food);
            System.out.println(jsonArray2);
            for (int i = 0; i < jsonArray2.length(); i++) {
                JSONObject jsonObject = jsonArray2.getJSONObject(i);
                System.out.println("H");
                if (jsonObject.get("foodId").equals(food.get("foodId"))) {

                    food = jsonObject;
                    System.out.println(food);
                    name.setText(food.get("name").toString());
                    beschreibung.setText(food.get("beschreibung").toString());
                    preis.setText(food.get("preis").toString());
                    imageview.setImage(stringToImage(food.get("bild").toString()));
                    String url="http://localhost:8080/kategorie/find/"+food.get("kategorieId");
                    JSONObject kategorie1= new JSONObject(JSONObjectGET(url).toString());
                    kategorie.setText(kategorie1.get("kategorie").toString());

                    return;


                }
            }

            changeScene("EditFood1.fxml");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }








    @FXML
    public void zurückButton() throws IOException {
        changeScene("Speisekarte.fxml");
    }


    public void deleteFood() throws IOException {
        JSONObjectDELETE("http://localhost:8080/food/delete/"+String.valueOf(food.getInt("foodId")));
        changeScene("EditFood1.fxml");
        initialize();


    }

    public void fertigButton() throws IOException{
        String bild=imageToString(path.getText());
        if(bild==null) {
            bild = "";
        }
        String Url = "http://localhost:8080/food/update";

        String data =
                                 "{\"foodId\": \"" + food.getInt("foodId") + "\",\n"+
                                         "\"name\": \"" + name.getText() + "\",\n" +
                        "        \"beschreibung\": \"" + beschreibung.getText() + "\",\n" +
                                        "        \"bild\": \"" + bild + "\",\n" +
                        "        \"preis\":" + Double.parseDouble(preis.getText()) + ",\n" +
                                         "        \"kategorieId\":" + food.getInt("kategorieId") + ",\n" +
                        "       \"menuId\":" + food.getInt("menuId") +"}";



        JSONObjectPUT(Url, data);
        changeScene("EditFood1.fxml");


    }


    public void uploadImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        File file = fileChooser.showOpenDialog(uploadImage.getScene().getWindow());
        path.setText(file.getAbsolutePath());
        Image image = new Image(file.toURI().toString());
        imageview.setImage(image);

    }
}
