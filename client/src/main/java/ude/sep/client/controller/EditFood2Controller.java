package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;


public class EditFood2Controller extends ConnectionController  {

    @FXML
    TextField name;

    @FXML
    TextField  beschreibung;

    @FXML
    TextField preis;

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
                    name.setText(food.get("name").toString());
                    beschreibung.setText(food.get("beschreibung").toString());
                    preis.setText(food.get("preis").toString());
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
        String Url = "http://localhost:8080/food/update";

        String data =
                                 "{\"foodId\": \"" + food.getInt("foodId") + "\",\n"+
                                         "\"name\": \"" + name.getText() + "\",\n" +
                        "        \"beschreibung\": \"" + beschreibung.getText() + "\",\n" +
                        "        \"preis\":" + Double.parseDouble(preis.getText()) + ",\n" +
                        "       \"menuId\":" + food.getInt("menuId") +"}";



        JSONObjectPUT(Url, data);
        changeScene("EditFood1.fxml");


    }


}
