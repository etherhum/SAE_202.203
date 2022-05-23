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
        setHgap(50);

        // Membres
        File membresFichier = new File(CHEMIN_MEMBRES);
        Label labelMembres = new Label("Membres:");
        labelMembres.setMaxWidth(Double.MAX_VALUE);
        labelMembres.setAlignment(Pos.CENTER);
        labelMembres.setStyle("-fx-font: 24 arial;");
        Label listeMembres = new Label(Membre.listeMembres(membresFichier).toString()
                .replace(" ", "")
                .replace(",","")
                .replace("[","")
                .replace("]",""));
        listeMembres.setFocusTraversable(false);
        listeMembres.setMinSize(150, 1400);
        listeMembres.setWrapText(true);
        ScrollPane paneMembres = new ScrollPane(listeMembres);
        paneMembres.setMinSize(200, 400);
        paneMembres.setStyle("-fx-background: #d8f3dc;");

        // Villes
        Label labelVilles = new Label("Villes:");
        labelVilles.setMaxWidth(Double.MAX_VALUE);
        labelVilles.setAlignment(Pos.CENTER);
        labelVilles.setStyle("-fx-font: 24 arial;");
        Label listeVilles = new Label(Membre.listeVilles(membresFichier).toString()
                .replace(" ", "")
                .replace(",","")
                .replace("[","")
                .replace("]","")
                .replace("_", " "));
        listeVilles.setFocusTraversable(false);
        listeVilles.setMinSize(150, 1400);
        listeVilles.setWrapText(true);
        ScrollPane paneVille = new ScrollPane(listeVilles);
        paneVille.setMinSize(200, 300);
        paneVille.setStyle("-fx-background: #d8f3dc;");

        add(labelMembres,0,1);
        add(paneMembres,0,2);
        add(labelVilles, 1, 1);
        add(paneVille, 1, 2);
    }
}
