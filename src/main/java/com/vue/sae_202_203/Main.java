package com.vue.sae_202_203;

import com.modele.sae_202_203.Distance;
import com.modele.sae_202_203.Membre;
import com.modele.sae_202_203.Scenario;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    public void start(Stage stage){
        HBoxRoot root = new HBoxRoot();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("APLI - Saé 2.01");
        stage.show();
        stage.setScene(scene);

    }
    public static void main(String[] args) throws IOException {

        //Lecture des scenarios
        File scenarioFichier = new File("/Users/soulja/Desktop/Fichiers/scenario_0.txt");
        File membresFichier = new File("/Users/soulja/Desktop/Fichiers/membres_APLI.txt");
        File distancesFichier = new File("/Users/soulja/Desktop/Fichiers/distances.txt");

        Scenario scenario = Scenario.listeScenarios(scenarioFichier);

        //Lecture des membres
        Membre membre = new Membre();
        //membre.convertirPseudo(membresFichier, scenarioFichier);

        //Lecture des distances
        Distance distancess = new Distance();
        distancess.ajoutDistances(distancesFichier);
        //A FAIRE: DEUXIEME STRUCTURE

        //Vue
        Application.launch(args);
    }
}
