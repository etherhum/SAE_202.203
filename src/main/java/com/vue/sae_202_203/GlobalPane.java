package com.vue.sae_202_203;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class GlobalPane extends GridPane {
    public GlobalPane() throws IOException {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(0, 0, 0, 100));
        setHgap(20);
        setVgap(10);

        MembresVillesPane membresVilles = new MembresVillesPane();
        ScenarioDistancePane scenarioDistancePane = new ScenarioDistancePane();

        add(membresVilles,0,1);
        add(scenarioDistancePane, 0, 2);
    }
}
