package com.vue.sae_202_203;

import com.controleur.sae_202_203.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class VBoxRoot extends VBox implements Constantes {
    private static MenuItem itemMembres = new MenuItem("Membres");
    private static MenuItem itemScenarios = new MenuItem("Scénarios");
    private static MenuItem itemAjout = new MenuItem("Ajout");
    public VBoxRoot() throws IOException {
        // Fenêtre (Pane): Membres - Villes
        LecturePane lecturePane = new LecturePane();
        ScenarioPane scenarioPane = new ScenarioPane();
        AjoutPane ajoutPane = new AjoutPane();

        // Menubar: Lecture - Scénario - Ajout
        MenuBar menuBar = new MenuBar();

        Menu actionsMenu = new Menu("Actions");
        itemMembres.setOnAction(e -> {
            System.out.println("SWITCH -> MEMBRES");
        });
        itemScenarios.setOnAction(e -> {
            System.out.println("SWITCH -> SCENARIOS");
        });
        itemAjout.setOnAction(e -> {
            System.out.println("SWITCH -> AJOUT");
        });
        actionsMenu.getItems().addAll(itemMembres, itemScenarios, itemAjout);

        menuBar.getMenus().addAll(actionsMenu);
        getChildren().addAll(menuBar, lecturePane);
    }

    public static MenuItem getItemMembres(){ return itemMembres; }

    public static MenuItem getItemScenarios(){ return itemScenarios; }

    public static MenuItem getItemAjout(){ return itemAjout; }
}
