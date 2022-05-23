package com.vue.sae_202_203;

import com.modele.sae_202_203.Membre;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;

public class LecturePane extends GridPane implements ConstantesChemins {
    public LecturePane() throws IOException {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(50, 0, 50, 0));

        // Membres
        File membresFichier = new File(CHEMIN_MEMBRES);
        Label labelMembres = new Label("Membres:");
        labelMembres.setMaxWidth(Double.MAX_VALUE);
        labelMembres.setAlignment(Pos.CENTER);
        labelMembres.setStyle("-fx-font: 24 arial;");
        Label listeMembres = new Label(Membre.convertMembres(membresFichier).keySet().toString());
        listeMembres.setFocusTraversable(false);
        listeMembres.setMaxSize(200, 200);
        listeMembres.setMinSize(250, 1400);
        listeMembres.setWrapText(true);
        ScrollPane paneMembres = new ScrollPane(listeMembres);
        paneMembres.setMaxSize(200, 200);
        paneMembres.setMinSize(300, 400);
        paneMembres.setStyle("-fx-background: #d8f3dc;");

        // Villes
        Label labelVilles = new Label("Villes:");
        labelVilles.setMaxWidth(Double.MAX_VALUE);
        labelVilles.setAlignment(Pos.CENTER);
        labelVilles.setStyle("-fx-font: 24 arial;");
        Label listeVilles = new Label(Membre.convertMembres(membresFichier).values().toString());
        listeVilles.setFocusTraversable(false);
        listeVilles.setMaxSize(200, 200);
        listeVilles.setMinSize(250, 1400);
        listeVilles.setWrapText(true);
        ScrollPane paneVille = new ScrollPane(listeVilles);
        paneVille.setMaxSize(200, 200);
        paneVille.setMinSize(300, 400);
        paneVille.setStyle("-fx-background: #d8f3dc;");

        add(labelMembres,0,1);
        add(paneMembres,0,2);
        add(labelVilles, 1, 1);
        add(paneVille, 1, 2);
    }
}
