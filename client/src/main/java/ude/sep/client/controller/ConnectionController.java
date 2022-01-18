package ude.sep.client.controller;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import ude.sep.client.Main;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;


public class ConnectionController {

    // Verbindet sich mit dem Server und führt einen GET request aus und gibt ein JSONOBject wieder
    public StringBuffer JSONObjectGET(String inputUrl) throws IOException {
        URL url = new URL(inputUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        return content;
    }

    //POST request um Daten über ein POSTMapping in die DB zu bekommen
    public void JSONObjectPOST(String inputUrl, String data) throws IOException {
        URL url = new URL(inputUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        con.getOutputStream().write(data.getBytes("UTF-8"));
        con.getInputStream();
    }

    //PUT request
    public void JSONObjectPUT(String inputUrl, String data) throws IOException {
        URL url = new URL(inputUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        con.getOutputStream().write(data.getBytes("UTF-8"));
        con.getInputStream();
    }

    //Delete request
    public void JSONObjectDELETE(String inputUrl) throws IOException {
        URL url = new URL(inputUrl);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
        httpCon.setRequestMethod("DELETE");
        httpCon.getResponseCode();
        httpCon.connect();
    }

    public static void changeScene(String fxml) throws IOException {
        Main m = new Main();
        m.changeScene(fxml);
    }
    public void stringToImageView(String image, ImageView imageView) {
        byte[] jpeg = Base64.getDecoder().decode(image);
        Image imageX = new Image(new ByteArrayInputStream(jpeg));
        imageView.setImage(imageX);
    }

    public Image stringToImage(String image) {
        byte[] jpeg = Base64.getDecoder().decode(image);
        Image imageX = new Image(new ByteArrayInputStream(jpeg));
        return imageX;
    }

    public String imageToString(String filename) {
        byte[] targetArray;
        try {
            InputStream inputStream = new FileInputStream(new File(filename));
            targetArray = new byte[inputStream.available()];
            inputStream.read(targetArray);
            return Base64.getEncoder().encodeToString(targetArray);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


// Ab hier Oguzhan
    public JSONArray getMenu() throws IOException {
        JSONArray output = new JSONArray();
        JSONObject menuObject = new JSONObject(JSONObjectGET("http://localhost:8080/menu/find/"+LoginController.userId).toString());
        JSONArray menu = menuObject.getJSONArray("kategories");
        for (int i = 0; i < menu.length(); i++) {
            JSONArray curCategory = menu.getJSONObject(i).getJSONArray("foods");
            for(int j=0;j<curCategory.length();j++) {
                JSONObject toAdd = new JSONObject();
                JSONObject curSpeise = curCategory.getJSONObject(j);
                toAdd.put("foodId", curSpeise.getInt("foodId"));
                toAdd.put("name", curSpeise.getString("name"));
                output.put(toAdd);
            }
        }
        return output;
    }

    public JSONArray getBestellungen(LocalDate datum) throws IOException {
        JSONArray output = new JSONArray();
        JSONArray all = new JSONArray(JSONObjectGET("http://localhost:8080/bestellung").toString());
        for (int i = 0; i < all.length(); i++) {
            JSONObject curOrder = all.getJSONObject(i);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            if(LocalDate.parse(curOrder.getString("date"), dtf).isAfter(datum)) {
                for(int j=0;j< curOrder.getJSONArray("liste").length(); j++) {
                    JSONObject curSpeise = curOrder.getJSONArray("liste").getJSONObject(j);
                    JSONObject toAdd = new JSONObject();
                    toAdd.put("datum", curOrder.getString("date"));
                    toAdd.put("name", curSpeise.getString("name"));
                    output.put(toAdd);
                }
            }
        }
        return output;
    }

    public void addDeliveryTime(int timeFor, int timeOf, int distance) throws IOException {
        String url = "http://localhost:8080/time/add/"+timeFor+"/"+timeOf+"/"+distance;
        JSONObjectPOST(url,"{}");
    }

    public int getDeliveryTime(int timeFor) throws IOException {
        String url = "http://localhost:8080/time/findfor/"+timeFor;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        JSONObject j = new JSONObject(JSONObjectGET(url).toString());
        System.out.println("Das ausgewaehlte Time: "+j);
        Duration output = Duration.between(LocalDateTime.now(), LocalDateTime.parse(j.getString("end"), dtf));
        return (int) output.toMinutes();
    }

    public JSONObject lookUpDistance(String address, int restaurantId) throws IOException {
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
        url = url+address;
        JSONObject rest = new JSONObject(JSONObjectGET("http://localhost:8080/restaurant/find/"+restaurantId).toString());
        url = url + "&destinations=" + rest.getString("strasse") + "%2C" + rest.getString("nummer") + "%2C" + rest.getString("plz") + "%2C" + rest.getString("stadt");
        url = url + "&departure_time=now&key=AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o";
        url = url.replaceAll(" ", "%2C");
        JSONObject result = new JSONObject(JSONObjectGET(url).toString());
        return result;
    }

    public void addSale(JSONObject input) throws IOException {
        String url = "http://localhost:8080/sale/add";
        JSONObjectPOST(url,input.toString());
    }

    public JSONArray getPromotion(int restaurantId) throws IOException {
        String url = "http://localhost:8080/sale/find/"+restaurantId;
        JSONArray output= new JSONArray(JSONObjectGET(url).toString());
        return output;
    }

    public Double avgFood(int id) throws IOException {
        double output = 0;
        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/rating/" + id).toString());
        for(int i=0;i<j.length();i++) {
            output=output+j.getJSONObject(i).getDouble("starsFood");
        }
        output = output/j.length();
        return Math.round(output*1e1)/1e1;
    }

    public Double avgDelivery(int id) throws IOException {
        double output = 0;
        JSONArray j = new JSONArray(JSONObjectGET("http://localhost:8080/rating/" + id).toString());
        for(int i=0;i<j.length();i++) {
            output=output+j.getJSONObject(i).getDouble("starsLieferung");
        }
        output = output/j.length();
        return Math.round(output*1e1)/1e1;
    }
    //Testfunktionen alle nachfolgend
    public Double avgFoodTest(JSONArray j){
        double output = 0;
        for(int i=0;i<j.length();i++) {
            output=output+j.getJSONObject(i).getDouble("starsFood");
        }
        output = output/j.length();
        return Math.round(output*1e1)/1e1;
    }

    public Double avgDeliveryTest(JSONArray j){
        double output = 0;
        for(int i=0;i<j.length();i++) {
            output=output+j.getJSONObject(i).getDouble("starsLieferung");
        }
        output = output/j.length();
        return Math.round(output*1e1)/1e1;
    }

    public int lookUpDistanceTest(JSONObject j) throws IOException {
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=";
        url = url+j.getString("address1");
        url = url + "&destinations=";
        url = url+j.getString("address2");
        url = url + "&departure_time=now&key=AIzaSyA-qLMdcnsAVwBvC0Xpi2N73coqLzq9v0o";
        url = url.replaceAll(" ", "%2C");
        JSONObject result = new JSONObject(JSONObjectGET(url).toString());
        return result.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getInt("value");
    }

    public boolean hasPromotionTest(JSONObject cur) throws IOException {
        boolean output = false;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate start = LocalDate.parse(cur.getString("start"), dtf);
        LocalDate end = LocalDate.parse(cur.getString("end"), dtf);
        if (LocalDate.now().isAfter(start) | LocalDate.now().isEqual(start) &&
                LocalDate.now().isBefore(end) | LocalDate.now().isEqual(end)) {
            output = true;

        }
        return output;
    }

    public int getDeliveryTimeTest(int toAdd) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String input = LocalDateTime.now().plusMinutes(toAdd).format(dtf);
        Duration output = Duration.between(LocalDateTime.now(), LocalDateTime.parse(input,dtf));
        return (int) output.toMinutes();
    }
    //ok ende
}
