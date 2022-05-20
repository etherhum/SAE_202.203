package com.vue.sae_202_203;

import com.modele.sae_202_203.Scenario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ScenarioPane extends GridPane implements Constantes {
    public ScenarioPane(){
        setGridLinesVisible(true);

        //////////////////////////////////////////////////////////////////
        /////////////////////////////SCENARIO/////////////////////////////
        //////////////////////////////////////////////////////////////////

        Label labelScenario = new Label("Scénario:");
        labelScenario.setMaxWidth(Double.MAX_VALUE);
        labelScenario.setAlignment(Pos.CENTER);
        labelScenario.setStyle("-fx-font: 24 arial;");

        // ZONE AFFICHAGE SCENARIO
        Label txtChoix = new Label();
        ScrollPane paneScenario = new ScrollPane(txtChoix);
        paneScenario.setStyle("-fx-background: #d8f3dc;");
        paneScenario.setMaxSize(200, 400);
        paneScenario.setMinSize(300, 400);

        // HBox -> Label "Scénario" + ComboBox
        Label txtChoisirScenario = new Label("Scénarios");
        File f = new File(CHEMIN_SCENARIOS);
        File[] fichiers = f.listFiles();
        ArrayList<String> listeNomfichiers = new ArrayList<>();
        assert fichiers != null;
        for (File fichier : fichiers) {
            listeNomfichiers.add(fichier.getName());
        }
        ObservableList<String> obsNomfichiers = FXCollections.observableArrayList(listeNomfichiers);
        ComboBox<String> cbScenarios = new ComboBox<>(obsNomfichiers);
        HBox hBoxScenario = new HBox(txtChoisirScenario, cbScenarios);
        hBoxScenario.setSpacing(10);

        // Bouton -> Valider
        Button boutonValider = new Button("Valider");
        boutonValider.setFocusTraversable(false);
        boutonValider.setOnAction(event -> {
            if(cbScenarios.getValue()==null){
                txtChoix.setText("Erreur: Aucun scénario sélectionné");
            } else {
                File scenarioFichier = new File(CHEMIN_SCENARIOS + cbScenarios.getValue());
                Scenario scenario = new Scenario();
                try {
                    scenario = Scenario.listeScenarios(scenarioFichier);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    txtChoix.setText(Scenario.afficherDistances(scenario).toString()
                            .replace(",", "")
                            .replace("[", "")
                            .replace("]", "")
                            .trim());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        add(labelScenario, 0, 1);
        add(paneScenario, 0, 2);
        add(hBoxScenario, 0, 3);
        add(boutonValider, 0, 4);

        //////////////////////////////////////////////////////////////////
        //////////////////////////////CHEMIN//////////////////////////////
        //////////////////////////////////////////////////////////////////
        Label labelChemin = new Label("Chemin:");
        labelChemin.setMaxWidth(Double.MAX_VALUE);
        labelChemin.setAlignment(Pos.CENTER);
        labelChemin.setStyle("-fx-font: 24 arial;");

        ScrollPane paneChemin = new ScrollPane();
        paneChemin.setStyle("-fx-background: #d8f3dc;");
        paneChemin.setMaxSize(200, 400);
        paneChemin.setMinSize(300, 400);

        add(labelChemin, 1, 1);
        add(paneChemin, 1, 2);


    }
}
