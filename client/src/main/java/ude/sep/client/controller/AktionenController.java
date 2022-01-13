package ude.sep.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.IOException;

public class AktionenController extends ConnectionController {

    @FXML
    TextField datum1;
    @FXML
    TextField datum2;


    @FXML
    public void goSaleClick() throws IOException {
        JSONObject input = new JSONObject();
        input.put("rId" , LoginController.userId);
        input.put("start", datum1.getText());
        if(datum2.getText().matches("")) {input.put("end", datum1.getText());}
        else {input.put("end", datum2.getText());}
        addSale(input);
        changeScene("Startseite.fxml");
    }
}
