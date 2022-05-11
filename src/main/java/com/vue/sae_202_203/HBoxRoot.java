package com.vue.sae_202_203;

import com.modele.sae_202_203.Distance;
import com.modele.sae_202_203.Membre;
import com.modele.sae_202_203.Scenario;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class HBoxRoot extends HBox {
    File scenarioFichier = new File("/Users/soulja/Desktop/Fichiers/scenario_0.txt");
    File membresFichier = new File("/Users/soulja/Desktop/Fichiers/membres_APLI.txt");
    File distanceFichier = new File("/Users/soulja/Desktop/Fichiers/distances.txt");
    Scenario scenario = Scenario.listeScenarios(scenarioFichier);
    Membre membre = new Membre();
    Distance distance = new Distance();
    File f = null;
    File[] fichiers;


    public HBoxRoot() throws IOException {

        Label labelTitre = new Label("APLI - SAÉ 2.01");
        labelTitre.setMaxWidth(Double.MAX_VALUE);
        labelTitre.setAlignment(Pos.CENTER);
        labelTitre.setStyle("-fx-font: 44 arial;");


        GridPane membresPane = new GridPane();
        membresPane.setAlignment(Pos.CENTER);
        membresPane.setPadding(new Insets(0, 0, 0, 100));
        membresPane.setHgap(20);
        membresPane.setVgap(10);


        //Groupe "Affichage des membres"
        Label labelMembres = new Label("Membres:");
        labelMembres.setMaxWidth(Double.MAX_VALUE);
        labelMembres.setAlignment(Pos.CENTER);
        labelMembres.setStyle("-fx-font: 24 arial;");
        Label listeMembres = new Label(membre.convertMembres(membresFichier).keySet().toString());
        listeMembres.setFocusTraversable(false);
        listeMembres.setMaxSize(200, 200);
        listeMembres.setMinSize(250, 1400);
        listeMembres.setWrapText(true);
        listeMembres.setStyle("-fx-background-color: #d8f3dc;");
        ScrollPane paneMembres = new ScrollPane(listeMembres);
        paneMembres.setMaxSize(200, 200);
        paneMembres.setMinSize(300, 200);


        //Groupe "Affichage des villes"
        Label labelVilles = new Label("Villes:");
        labelVilles.setMaxWidth(Double.MAX_VALUE);
        labelVilles.setAlignment(Pos.CENTER);
        labelVilles.setStyle("-fx-font: 24 arial;");
        Label listeVilles = new Label(membre.convertMembres(membresFichier).values().toString());
        listeVilles.setFocusTraversable(false);
        listeVilles.setMaxSize(200, 200);
        listeVilles.setMinSize(250, 1400);
        listeVilles.setWrapText(true);
        listeVilles.setStyle("-fx-background-color: #d8f3dc;");
        ScrollPane paneVille = new ScrollPane(listeVilles);
        paneVille.setMaxSize(200, 200);
        paneVille.setMinSize(300, 200);


        //Groupe "Choix du scénario"
        Label txtChoisirScenario = new Label("Scénarios");
        File f = new File("/Users/soulja/Desktop/Fichiers");
        fichiers = f.listFiles();
        ArrayList<String> listeNomfichiers = new ArrayList<>();
        for (int i = 0; i < fichiers.length; i++) {
            listeNomfichiers.add(fichiers[i].getName());
        }
        ObservableList<String> obsNomfichiers = FXCollections.observableArrayList(listeNomfichiers);
        ComboBox cbScenarios = new ComboBox(obsNomfichiers);
        HBox vboxScenario = new HBox(txtChoisirScenario, cbScenarios);
        vboxScenario.setSpacing(10);

        Button boutonValider = new Button("Valider");
        boutonValider.setFocusTraversable(false);





        membresPane.add(labelMembres,0,1);
        membresPane.add(paneMembres,0,2);
        membresPane.add(labelVilles, 1, 1);
        membresPane.add(paneVille, 1, 2);
        membresPane.add(vboxScenario, 0, 5);
        membresPane.add(boutonValider, 0, 6);


        getChildren().addAll(membresPane);

    }
}
