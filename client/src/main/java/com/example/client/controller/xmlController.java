package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.*;
import java.security.cert.Extension;
import java.util.List;

public class xmlController {
@FXML
public ListView listview;
public static String path;
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
        File xmlFile= new File(path);
        Reader filereader = new FileReader(xmlFile);
        BufferedReader bufferedReader= new BufferedReader(filereader);

        StringBuilder sb = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line!= null){
            sb.append(line).append("\n");
            line= bufferedReader.readLine();
        }
        String xml2String = sb.toString();
        System.out.println("XML to String with Bufferreader :");
        System.out.println(xml2String);

        bufferedReader.close();

    }

    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("Speisekarte.fxml");
    }


    public void fertigButton() {
    }
}
