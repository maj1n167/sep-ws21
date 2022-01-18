package ude.sep.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

public class MenuSpeisekarteeController extends ConnectionController {

    @FXML
    TableView<Food> list;
    @FXML
    TableColumn Kategorie = new TableColumn("Kategorie");
    @FXML
    TableColumn AddButton = new TableColumn("zumWarenkorb");
    @FXML
    TableColumn Preis = new TableColumn("Preis");
    @FXML
    TableColumn Beschreibung = new TableColumn("Beschreibung");
    @FXML
    TableColumn Id = new TableColumn("Id");
    @FXML
    TableColumn Bild = new TableColumn("Bild");
    @FXML
    TableColumn Name = new TableColumn("Name");
    @FXML
    TableColumn addWarenkorb = new TableColumn("");



    public static int userId;
    public static int restaurantId;

    public void initialize() {
        try {

            restaurantId = RestaurantsController.id;
            userId = LoginController.userId;
            list.setEditable(true);
            Bild.setCellValueFactory(new PropertyValueFactory<Food, ImageView>("bild"));
            Kategorie.setCellValueFactory(new PropertyValueFactory<Food, String>("kategorie"));
            Name.setCellValueFactory(new PropertyValueFactory<Food, String>("name"));
            Beschreibung.setCellValueFactory(new PropertyValueFactory<Food, String>("beschreibung"));
            Preis.setCellValueFactory(new PropertyValueFactory<Food, Double>("preis"));
            Id.setCellValueFactory(new PropertyValueFactory<Food, Integer>("foodId"));
            addWarenkorb.setCellValueFactory(new PropertyValueFactory<Food, Button>("hinzufügen"));
            list.setFixedCellSize(65);
            list.setItems(getSpeisekarte());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Food> getSpeisekarte() throws IOException {
        ObservableList<Food> output = FXCollections.observableArrayList();
        JSONObject b = new JSONObject(JSONObjectGET("http://localhost:8080/menu/find/" + restaurantId).toString());
        JSONArray kategories = new JSONArray(b.get("kategories").toString());
        System.out.println(kategories);
        for (int i = 0; i < kategories.length(); i++) {

            JSONObject current = new JSONObject(kategories.get(i).toString());
            JSONArray foods = new JSONArray(current.get("foods").toString());
            System.out.println(foods);
            for (int j = 0; j < foods.length(); j++) {
                JSONObject currentfoods = new JSONObject(foods.get(j).toString());
                System.out.println(currentfoods);
                currentfoods.put("kategorie", current.get("kategorie"));
                Food r = new Food();
                System.out.println(r.preis);
                System.out.println("here");
                Image imgToSave = stringToImage(currentfoods.getString("bild"));
                output.add(r = new Food(new ImageView(imgToSave), currentfoods.getString("name"),
                        currentfoods.getString("beschreibung"), currentfoods.getDouble("preis"),
                        currentfoods.getInt("foodId"), currentfoods.getString("kategorie")));
                System.out.println(output);
            }
        }
        System.out.println(output);
        return output;
    }

    public void zurückButton(ActionEvent actionEvent) throws IOException{
        changeScene("Restaurants.fxml");
    }

    public void fertig(ActionEvent actionEvent) throws IOException {
        changeScene("Warenkorb.fxml");
    }


    public static class Food extends ConnectionController {
        private ImageView bild;
        private int foodId;
        private double preis;
        private String name;
        private String beschreibung;
        private String url;
        private Long kategorieId;
        private Long menuId;
        private String kategorie;

        public Food() {

        }

        public Button getHinzufügen() {
            return hinzufügen;
        }

        public void setHinzufügen(Button hinzufügen) {
            this.hinzufügen = hinzufügen;
        }

        private Button hinzufügen;


        public Food(int foodId, double preis, String name, String beschreibung, String url, Long kategorieId, Long menuId, String kategorie) {
            this.foodId = foodId;
            this.preis = preis;
            this.name = name;
            this.beschreibung = beschreibung;
            this.url = url;
            this.kategorieId = kategorieId;
            this.menuId = menuId;
            this.kategorie = kategorie;
            this.hinzufügen = new Button();
            this.hinzufügen.setText("Hinzufügen");
            this.hinzufügen.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        JSONObject jsonObject = new JSONObject(JSONObjectGET("http://localhost:8080/warenkorb/find/" + userId));
                        jsonObject.put("foodList", foodId);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public Food(ImageView bild, String name, String beschreibung, double preis, int id, String kategorie) {

            this.bild = bild;
            this.name = name;
            this.beschreibung = beschreibung;
            this.preis = preis;
            this.foodId = id;
            this.kategorie = kategorie;
            this.hinzufügen = new Button();
            this.hinzufügen.setText("Hinzufügen");
            this.hinzufügen.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        JSONObject jsonObject = new JSONObject(JSONObjectGET("http://localhost:8080/warenkorb/find/" + userId).toString());
                        JSONArray jsonArray = new JSONArray(jsonObject.get("foodList").toString());
                        JSONObject jsonObject1 = new JSONObject(JSONObjectGET("http://localhost:8080/food/find/" + foodId).toString());
                        JSONObject newFood = new JSONObject();
                        newFood.put("name",jsonObject1.get("name"));
                        newFood.put("beschreibung",jsonObject1.get("beschreibung"));
                        newFood.put("preis", jsonObject1.getDouble("preis"));
                        Date date = new Date();
                        newFood.put("date",date.toString());
                        newFood.put("warenFID",userId);
                        newFood.put("kategorieId", jsonObject1.get("kategorieId"));
                        JSONObjectPOST("http://localhost:8080/warenfood/add",newFood.toString());
                        JSONArray allWarenFood = new JSONArray(JSONObjectGET("http://localhost:8080/warenfood").toString());
                        for(int i = 0; i<allWarenFood.length(); i++){
                            JSONObject m = allWarenFood.getJSONObject(i);
                            if(m.get("date").equals(date.toString())){
                                jsonArray.put(m);
                            }
                        }


                        System.out.println(newFood);

                        jsonObject.put("foodList", jsonArray);
                        System.out.println(jsonObject.toString());
                        double x = jsonObject.getDouble("summe");
                        double summe = x + preis;
                        jsonObject.put("summe", summe);
                        JSONObjectPOST("http://localhost:8080/warenkorb/add", jsonObject.toString());

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Produkt zum Warenkorb Hinzugefügt!");
                        alert.setContentText("Produkt zum Warenkorb Hinzugefügt!");
                        alert.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public ImageView getBild() {
            return bild;
        }

        public void setBild(ImageView bild) {
            this.bild = bild;
        }

        public int getFoodId() {
            return foodId;
        }

        public void setFoodId(int foodId) {
            this.foodId = foodId;
        }

        public double getPreis() {
            return preis;
        }

        public void setPreis(double preis) {
            this.preis = preis;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBeschreibung() {
            return beschreibung;
        }

        public void setBeschreibung(String beschreibung) {
            this.beschreibung = beschreibung;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Long getKategorieId() {
            return kategorieId;
        }

        public void setKategorieId(Long kategorieId) {
            this.kategorieId = kategorieId;
        }

        public Long getMenuId() {
            return menuId;
        }

        public void setMenuId(Long menuId) {
            this.menuId = menuId;
        }

        public String getKategorie() {
            return kategorie;
        }

        public void setKategorie(String kategorie) {
            this.kategorie = kategorie;
        }


        public void zurückButton() throws IOException {
            changeScene("Restaurants.fxml");
        }



    }
}
