package com.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        //Habe es hier umgeschrieben damit ich es hinkriege beim LoginController Views zu switchen-lg

        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setTitle("Supreme Eating Program");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        //stage.setTitle("Supreme Eating Program");
        //stage.setScene(scene);
        //stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}