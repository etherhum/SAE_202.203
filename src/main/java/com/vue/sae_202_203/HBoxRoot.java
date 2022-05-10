package com.vue.sae_202_203;

import com.modele.sae_202_203.Membre;
import com.modele.sae_202_203.Scenario;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;

public class HBoxRoot extends HBox {
    //Lecture des scenarios


    //Lecture des membres

    public HBoxRoot(){

        Label labelMembre = new Label("Membre");
        Label labelVille = new Label("Ville");
        getChildren().addAll(labelMembre, labelVille);
    }
}
