package com.vue.sae_202_203;

import com.modele.sae_202_203.Distance;
import com.modele.sae_202_203.Membre;
import com.modele.sae_202_203.Scenario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HBoxRoot extends HBox {
    File membresFichier = new File("C:\\Users\\pmontagn\\Documents\\Fichiers Saé 2.01 - 2.02\\membres_APLI.txt");
    File distanceFichier = new File("C:\\Users\\pmontagn\\Documents\\Fichiers Saé 2.01 - 2.02\\distances.txt");
    Membre membre = new Membre();
    Distance distance = new Distance();
    Main main = new Main();
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
        File f = new File("C:\\Users\\pmontagn\\Documents\\Fichiers Saé 2.01 - 2.02");
        fichiers = f.listFiles();
        ArrayList<String> listeNomfichiers = new ArrayList<>();
        for (int i = 0; i < fichiers.length; i++) {
            listeNomfichiers.add(fichiers[i].getName());
        }
        ObservableList<String> obsNomfichiers = FXCollections.observableArrayList(listeNomfichiers);
        ComboBox cbScenarios = new ComboBox(obsNomfichiers);
        HBox vboxScenario = new HBox(txtChoisirScenario, cbScenarios);
        vboxScenario.setSpacing(10);

        Label txtChoix = new Label();
        Button boutonValider = new Button("Valider");
        boutonValider.setFocusTraversable(false);
        boutonValider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(cbScenarios.getValue()==null){
                    txtChoix.setText("Erreur: Aucun scénario sélectionné");
                } else {
                    File scenarioFichier = new File("C:\\Users\\pmontagn\\Documents\\Fichiers Saé 2.01 - 2.02\\" + cbScenarios.getValue().toString());
                    Scenario scenario = new Scenario();
                    try {
                        scenario = Scenario.listeScenarios(scenarioFichier);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    txtChoix.setText(Scenario.afficherDistances(scenario).toString());
                }
            }
        });
        membresPane.add(labelMembres,0,1);
        membresPane.add(paneMembres,0,2);
        membresPane.add(labelVilles, 1, 1);
        membresPane.add(paneVille, 1, 2);
        membresPane.add(vboxScenario, 0, 3);
        membresPane.add(boutonValider, 0, 4);
        membresPane.add(txtChoix, 1, 3);

        getChildren().addAll(membresPane);

    }
}
