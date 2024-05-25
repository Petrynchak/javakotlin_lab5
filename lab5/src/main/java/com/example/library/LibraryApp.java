package com.example.library;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LibraryApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL fxmlLocation = LibraryApp.class.getResource("/com/example/library/main-view.fxml");
        if (fxmlLocation == null) {
            System.err.println("FXML file not found at specified path: /com/example/library/main-view.fxml");
            return;
        } else {
            System.out.println("FXML file found at: " + fxmlLocation);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
