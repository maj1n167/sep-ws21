module ude.sep.client {
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

    opens ude.sep.client to javafx.fxml;
    exports ude.sep.client;
    exports ude.sep.client.controller;
    opens ude.sep.client.controller to javafx.fxml;
}