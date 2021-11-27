package com.example.client.controller;

import com.example.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class GuthabenAufladenController extends ConnectionController {
@FXML
private TextField textField;
@FXML
private ListView listView;




    //public void listview()throws IOException{

   //}




    public void AufladenButton() throws IOException{

        if (textField.getText().equals("")){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Geben sie was ein");
            alert.setContentText("Guthaben einfügen");


            alert.showAndWait();
        }else {




            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setTitle("Guthaben hinzugefügt");
            alert.setContentText("Guthaben wurde erfolgreich hinzugefügt");


            alert.showAndWait();

        }
    }




    public void zurückButton() throws IOException{
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }
    public void fertigButton() throws IOException{
        Main m = new Main();
        m.ChangeScene("Startseite.fxml");
    }

}
