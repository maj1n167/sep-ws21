module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.json;
    requires com.dlsc.gmapsfx;

    opens com.example.client to javafx.fxml;
    exports com.example.client;
    exports com.example.client.controller;
    opens com.example.client.controller to javafx.fxml;
}