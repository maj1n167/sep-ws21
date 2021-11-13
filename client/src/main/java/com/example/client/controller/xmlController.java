package com.example.client.controller;

import com.example.client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class xmlController {
@FXML
public ListView listView;

    @FXML
    public void öffnenButton(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null){
            listView.getItems().add(selectedFile.getAbsolutePath());
        } else {
            System.out.println("file is not valid");
        }
    }

    public void speichernButton() {

    }

    public void zurückButton() throws IOException {
        Main m = new Main();
        m.ChangeScene("Speisekarte.fxml");
    }


    public void fertigButton() {
    }
}
