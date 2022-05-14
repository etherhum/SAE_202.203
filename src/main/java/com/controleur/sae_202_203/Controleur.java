package com.controleur.sae_202_203;

import com.modele.sae_202_203.Scenario;
import com.vue.sae_202_203.GlobalPane;
import com.vue.sae_202_203.MembresVillesPane;
import com.vue.sae_202_203.ScenarioDistancePane;
import javafx.event.Event;
import javafx.event.EventHandler;

import java.io.File;
import java.io.IOException;

public class Controleur implements EventHandler{
    @Override
    public void handle(Event event){
        ScenarioDistancePane scenarioPane = GlobalPane.getScenarioDistancePane();

        if(event.getSource()== scenarioPane.getBoutonValider()){
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
    }
}
