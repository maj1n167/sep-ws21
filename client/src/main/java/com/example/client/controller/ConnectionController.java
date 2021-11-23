package com.example.client.controller;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



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

}
