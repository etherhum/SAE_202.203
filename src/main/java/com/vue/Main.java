package com.vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    public void start(Stage stage) throws IOException {
        VBoxRoot root = new VBoxRoot();
        root.setStyle("-fx-background-color: #b7e4c7;");
        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("APLI - Saé 2.01 / 0.2");
        stage.show();
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
