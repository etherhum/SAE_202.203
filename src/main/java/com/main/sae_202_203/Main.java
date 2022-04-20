package com.main.sae_202_203;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File scenario0 = new File("/Users/soulja/Desktop/Fichiers/scenario_0.txt");

        Scenario test = new Scenario();
        Scenario.ecritureScenario("/Users/soulja/Desktop/Fichiers/scenario_0.txt", test);
        System.out.println(test.toString());

        File membres = new File("/Users/soulja/Desktop/Fichiers/membres_APLI.txt");
        Membres.lectureMembres(membres);

    }
}
