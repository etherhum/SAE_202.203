package com.vue.sae_202_203;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class HBoxRoot extends HBox {
    private static GlobalPane membresPane;

    static {
        membresPane = new GlobalPane();
    }

    public HBoxRoot(){

        Label labelTitre = new Label("APLI - SAÉ 2.01");
        labelTitre.setMaxWidth(Double.MAX_VALUE);
        labelTitre.setAlignment(Pos.CENTER);
        labelTitre.setStyle("-fx-font: 44 arial;");

        getChildren().addAll(membresPane);

    }
}
