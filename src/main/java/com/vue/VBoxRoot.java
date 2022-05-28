package com.vue;

import com.Chemins;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class VBoxRoot extends VBox implements Chemins {
    private static final MenuItem itemMembresVilles = new MenuItem("Membres");
    private static final MenuItem itemScenarios = new MenuItem("Scénarios");
    public VBoxRoot() throws IOException {
        LecturePane membresVillesPane = new LecturePane();
        ScenarioPane scenarioPane = new ScenarioPane();
        StackPane stackPane = new StackPane(membresVillesPane, scenarioPane);

        // Menubar: Lecture - Scénario - Ajout
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #d8f3dc;");

        // Etat initial au lancement: Membres affichés
        membresVillesPane.setVisible(true);
        scenarioPane.setVisible(false);

        Menu actionsMenu = new Menu("Actions");
        // Actions pour changer de fenêtre
        itemMembresVilles.setOnAction(e -> {
            membresVillesPane.setVisible(true);
            scenarioPane.setVisible(false);
        });
        itemScenarios.setOnAction(e -> {
            scenarioPane.setVisible(true);
            membresVillesPane.setVisible(false);
        });
        actionsMenu.getItems().addAll(itemMembresVilles, itemScenarios);

        menuBar.getMenus().addAll(actionsMenu);
        getChildren().addAll(menuBar, stackPane);
    }
}
