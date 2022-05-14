package com.vue.sae_202_203;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class GlobalPane extends GridPane {
    private static MembresVillesPane membresVilles;
    private static ScenarioDistancePane scenarioDistancePane = new ScenarioDistancePane();

    static {
        try {
            membresVilles = new MembresVillesPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GlobalPane(){
        setAlignment(Pos.CENTER);
        setPadding(new Insets(0, 0, 0, 100));
        setHgap(20);
        setVgap(10);

        add(membresVilles,0,1);
        add(scenarioDistancePane, 0, 2);
    }

    public static ScenarioDistancePane getScenarioDistancePane(){
        return scenarioDistancePane;
    }
}
