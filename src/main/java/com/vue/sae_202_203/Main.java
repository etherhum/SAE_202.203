package com.vue.sae_202_203;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    public void start(Stage stage) throws IOException {
        VBoxRoot root = new VBoxRoot();
        ScenarioPane root2 = new ScenarioPane();
        root2.setStyle("-fx-background-color: #b7e4c7;");
        Scene scene = new Scene(root2, 800, 600);
        stage.setScene(scene);
        stage.setTitle("APLI - Saé 2.01");
        stage.show();
        stage.setScene(scene);

    }
    public static void main(String[] args){
        Application.launch(args);
    }

}
