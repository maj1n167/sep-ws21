package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.IOException;

public class MenuSpeisekarteController extends ConnectionController{
    @FXML
    ListView<String> listView;

    public void initialize(){
        listView.getItems().addAll("Pizza","Döner");
        //listView.getItems().setAll("Pizza","Döner","Spaghetti");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }
    public void WarenkorbAdd(){
       String x = listView.getSelectionModel().getSelectedItems().toString();
        System.out.println(x);


    }

    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("KStartseite");
    }
}
