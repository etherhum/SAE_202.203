package com.vue.sae_202_203;

import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class VBoxRoot extends VBox implements ConstantesChemins {
    private static final MenuItem itemMembres = new MenuItem("Membres");
    private static final MenuItem itemScenarios = new MenuItem("Scénarios");
    public VBoxRoot() throws IOException {
        // Fenêtre (Pane): Membres - Villes
        LecturePane lecturePane = new LecturePane();
        ScenarioPane scenarioPane = new ScenarioPane();

        // Menubar: Lecture - Scénario - Ajout
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #d8f3dc;");

        StackPane stackPane = new StackPane(lecturePane, scenarioPane);
        lecturePane.setVisible(true);
        scenarioPane.setVisible(false);

        Menu actionsMenu = new Menu("Actions");
        itemMembres.setOnAction(e -> {
            lecturePane.setVisible(true);
            scenarioPane.setVisible(false);
        });
        itemScenarios.setOnAction(e -> {
            scenarioPane.setVisible(true);
            lecturePane.setVisible(false);
        });
        actionsMenu.getItems().addAll(itemMembres, itemScenarios);

        menuBar.getMenus().addAll(actionsMenu);
        getChildren().addAll(menuBar, stackPane);
    }
}
