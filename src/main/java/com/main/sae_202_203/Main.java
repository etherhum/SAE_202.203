package com.main.sae_202_203;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File scenario0 = new File("/Users/soulja/Desktop/Fichiers/scenario_0.txt");
        Scenario.lectureScenario(scenario0);


    }
}
