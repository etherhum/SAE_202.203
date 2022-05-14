package com.vue.sae_202_203;

import com.modele.sae_202_203.Membre;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;

public class MembresVillesPane extends GridPane {
    File membresFichier = new File("/Users/soulja/Desktop/Fichiers/membres_APLI.txt");
    Membre membre = new Membre();
    public MembresVillesPane() throws IOException {
        setHgap(10);
        setVgap(10);
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
        ScrollPane paneMembres = new ScrollPane(listeMembres);
        paneMembres.setMaxSize(200, 200);
        paneMembres.setMinSize(300, 200);
        paneMembres.setStyle("-fx-background: #d8f3dc;");


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
        ScrollPane paneVille = new ScrollPane(listeVilles);
        paneVille.setMaxSize(200, 200);
        paneVille.setMinSize(300, 200);
        paneVille.setStyle("-fx-background: #d8f3dc;");

        add(labelMembres,0,1);
        add(paneMembres,0,2);
        add(labelVilles, 1, 1);
        add(paneVille, 1, 2);
    }
}
