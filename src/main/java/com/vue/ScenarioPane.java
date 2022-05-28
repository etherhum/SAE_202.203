package com.vue;

import com.Chemins;
import com.modele.Distance;
import com.modele.Membre;
import com.modele.Scenario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ScenarioPane extends GridPane implements Chemins {
    File membresFichier = new File(CHEMIN_MEMBRES);
    static HashMap<ArrayList<String>, Integer> distance;
    Button boutonValider = new Button("Valider");

    public ScenarioPane(){
        // Paramètres
        setAlignment(Pos.CENTER);
        setPadding(new Insets(50, 0, 0, 0));
        setHgap(50);

        // Labels
        Label labelCheminOriginal = new Label("Chemin (Original):");
        Label labelCheminAlgorithme = new Label("Chemin (Algorithme):");
        Label labelScenario = new Label("Scénario:");
        Label txtChoisirScenario = new Label("Scénarios"); // Texte associé au Bouton Valider
        Label txtCheminAlgorithme = new Label(); // Affichage du Chemin Algorithmique
        Label txtCheminOriginal = new Label(); // Affichage du Chemin Original
        Label txtScenario = new Label(); // Affichage du scénario

        //////////////////////////////////////////////////////////////////
        /////////////////////////////SCENARIO/////////////////////////////
        //////////////////////////////////////////////////////////////////
        labelScenario.setMaxWidth(Double.MAX_VALUE);
        labelScenario.setAlignment(Pos.CENTER);
        labelScenario.setStyle("-fx-font: 24 arial;");

        // ZONE AFFICHAGE SCENARIO
        ScrollPane paneScenario = new ScrollPane(txtScenario);
        paneScenario.setStyle("-fx-background: #d8f3dc;");
        paneScenario.setMinSize(350, 400);

        // HBox -> Label "Scénario" + ComboBox
        File f = new File(CHEMIN_SCENARIOS);
        File[] fichiers = f.listFiles();
        ArrayList<String> listeNomfichiers = new ArrayList<>();
        assert fichiers != null;
        for (File fichier : fichiers) {
            listeNomfichiers.add(fichier.getName());
        }
        ObservableList<String> obsNomfichiers = FXCollections.observableArrayList(listeNomfichiers);
        ComboBox<String> cbScenarios = new ComboBox<>(obsNomfichiers);
        HBox hBoxScenarioChoix = new HBox(txtChoisirScenario, cbScenarios);
        hBoxScenarioChoix.setSpacing(10);

        // Bouton -> Valider
        boutonValider.setFocusTraversable(false);
        distance = Distance.convertirDistance(new File(CHEMIN_DISTANCE));
        boutonValider.setOnAction(actionEvent -> {
            lireScenario(cbScenarios, txtScenario, membresFichier, distance);
            cheminOriginal(lireScenario(cbScenarios, txtScenario, membresFichier, distance), txtCheminOriginal);
            cheminAlgorithme(lireScenario(cbScenarios, txtScenario, membresFichier, distance), txtCheminAlgorithme);
        });

        VBox vBoxScenarioAffichage = new VBox();
        vBoxScenarioAffichage.getChildren().addAll(labelScenario, paneScenario);

        add(vBoxScenarioAffichage, 0, 1);
        add(hBoxScenarioChoix, 0, 3);
        add(boutonValider, 0, 4);

        //////////////////////////////////////////////////////////////////
        //////////////////////////////CHEMIN//////////////////////////////
        //////////////////////////////////////////////////////////////////
        labelCheminOriginal.setMaxWidth(Double.MAX_VALUE);
        labelCheminOriginal.setAlignment(Pos.CENTER);
        labelCheminOriginal.setStyle("-fx-font: 24 arial;");

        labelCheminAlgorithme.setMaxWidth(Double.MAX_VALUE);
        labelCheminAlgorithme.setAlignment(Pos.CENTER);
        labelCheminAlgorithme.setStyle("-fx-font: 24 arial;");

        ScrollPane paneCheminOriginal = new ScrollPane(txtCheminOriginal);
        paneCheminOriginal.setStyle("-fx-background: #d8f3dc;");
        paneCheminOriginal.setMinSize(350,200);
        paneCheminOriginal.setMaxSize(350, 200);

        ScrollPane paneCheminAlgorithme = new ScrollPane(txtCheminAlgorithme);
        paneCheminAlgorithme.setStyle("-fx-background: #d8f3dc;");
        paneCheminAlgorithme.setMinSize(350,200);
        paneCheminAlgorithme.setMaxSize(350, 200);

        VBox vBoxChemin = new VBox();
        vBoxChemin.getChildren().addAll(labelCheminOriginal, paneCheminOriginal, labelCheminAlgorithme, paneCheminAlgorithme);
        add(vBoxChemin, 1, 1);
    }

    private static ArrayList<String> lireScenario(ComboBox<String> cbScenarios, Label txtChoix, File membresFichier, HashMap<ArrayList<String>, Integer> distance) {
        /*
         * Affiche le scénario choisi, avec ses distances
         */
        ArrayList<String> nomvilles = new ArrayList<>();
        if (cbScenarios.getValue() == null) {
            txtChoix.setText("Erreur: Aucun scénario sélectionné");
        } else {
            File scenarioFichier = new File(CHEMIN_SCENARIOS + cbScenarios.getValue());
            try {
                HashMap<String, String> membre = Membre.convertMembres(membresFichier);
                Scenario scenario = Scenario.listeScenarios(scenarioFichier);
                List<String> vendeurs = scenario.getVendeurs();
                List<String> acheteurs = scenario.getAcheteurs();
                String[] resultats = new String[vendeurs.size()];
                nomvilles = new ArrayList<>();
                for (int a = 0; a < vendeurs.size(); a++) {
                    String vendeur = vendeurs.get(a);
                    String acheteur = acheteurs.get(a);
                    ArrayList<String> pairVendeurAcheteur = new ArrayList<>();
                    pairVendeurAcheteur.add(membre.get(vendeur));
                    pairVendeurAcheteur.add(membre.get(acheteur));
                    resultats[a] = vendeur + " -> " + acheteur + " | " + membre.get(vendeur) + " -> " + membre.get(acheteur) + " | " + distance.get(pairVendeurAcheteur) + "\n";
                    nomvilles.add(membre.get(vendeur));
                    nomvilles.add(membre.get(acheteur));
                }
                String resultat = Arrays.toString(resultats)
                        .replace(",", "")
                        .replace("[", " ")
                        .replace("]", "")
                        .replace("_", " ");
                txtChoix.setText(resultat);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return nomvilles;
    }

    private static void cheminOriginal(ArrayList<String> nomvilles, Label txtCheminOriginal){
        /*
         * Affiche le chemin de base ainsi que sa distance totale, en partant et arrivant à Vélizy
         */
        nomvilles.add(0, "Velizy");
        nomvilles.add(nomvilles.size(), "Velizy");
        String[] resultats = new String[nomvilles.size()];
        int distanceTotale = 0;
        for(int a=0; a<nomvilles.size()-1; a++){

            ArrayList<String> pairVendeurAcheteur = new ArrayList<>();
            pairVendeurAcheteur.add(nomvilles.get(a));
            pairVendeurAcheteur.add(nomvilles.get(a+1));
            resultats[a] = nomvilles.get(a) + " -> " + nomvilles.get(a+1) + " | " + distance.get(pairVendeurAcheteur) + "km \n";
            distanceTotale += distance.get(pairVendeurAcheteur);
        }
        resultats[nomvilles.size()-1] = "\nTotal: " + distanceTotale + "km";
        txtCheminOriginal.setText(Arrays.toString(resultats)
                .replace(",", "")
                .replace("[", " ")
                .replace("]", ""));
    }

    private static void cheminAlgorithme(ArrayList<String> nomvilles, Label txtCheminAlgorithme){
        /* ALGORITHME CHEMIN OPTIMISE
         * TOUT LES DEUX RESULTS DANS NOMVILLES:
         * FAIRE UNE LISTE NEWLISTE ET AJOUTER CES RESULTS --> SUPPRIMER LES RESULTS DE LA LISTE NOMVILLES
         * (?) INVERSER NEWLISTE
         * AJOUTER VELIZY AU DEBUT ET FIN DE NEWLISTE
         * PARCOUR DE NEWLISTE:
         * AJOUTERPAIR PARCOUR ET PARCOUR+1 --> DISTANCE.GET(PAIR)*/
        ArrayList<String> ordonnancement = new ArrayList<>();
        for(int a=0; a<nomvilles.size(); a++){
            if(a%2!=0){
                ordonnancement.add(nomvilles.get(a));
                nomvilles.remove(a);
            }
        }
        ordonnancement.addAll(nomvilles);
        cheminOriginal(ordonnancement, txtCheminAlgorithme);
    }

}
