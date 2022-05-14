package com.vue.sae_202_203;

import com.modele.sae_202_203.Scenario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ScenarioDistancePane extends GridPane {
    File[] fichiers;
    public ScenarioDistancePane(){
        //Groupe "Choix du scénario"`
        setVgap(5);
        Label txtChoisirScenario = new Label("Scénarios");
        File f = new File("/Users/soulja/Desktop/Fichiers");
        fichiers = f.listFiles();
        ArrayList<String> listeNomfichiers = new ArrayList<>();
        assert fichiers != null;
        for (File fichier : fichiers) {
            listeNomfichiers.add(fichier.getName());
        }
        ObservableList<String> obsNomfichiers = FXCollections.observableArrayList(listeNomfichiers);
        ComboBox<String> cbScenarios = new ComboBox<>(obsNomfichiers);
        HBox hBoxScenario = new HBox(txtChoisirScenario, cbScenarios);
        hBoxScenario.setSpacing(10);

        Label txtChoix = new Label();
        Button boutonValider = new Button("Valider");
        boutonValider.setFocusTraversable(false);
        boutonValider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(cbScenarios.getValue()==null){
                    txtChoix.setText("Erreur: Aucun scénario sélectionné");
                } else {
                    File scenarioFichier = new File("/Users/soulja/Desktop/Fichiers/" + cbScenarios.getValue());
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
            }
        });
        ScrollPane paneScenario = new ScrollPane(txtChoix);
        paneScenario.setStyle("-fx-background: #d8f3dc;");
        paneScenario.setMaxSize(200, 200);
        paneScenario.setMinSize(300, 100);

        add(paneScenario, 0, 3);
        add(hBoxScenario, 0, 4);
        add(boutonValider, 0, 5);

    }
}
