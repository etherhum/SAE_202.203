package com.vue.sae_202_203;

import com.modele.sae_202_203.Distance;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws IOException {

        //Lecture des scenarios
        File scenario = new File("/Users/soulja/Desktop/Fichiers/scenario_0.txt");
        //System.out.println();

        //Lecture des membres
        File membres = new File("/Users/soulja/Desktop/Fichiers/membres_APLI.txt");
        //System.out.println(Membre.tableauMembres(membres));

        //Lecture des distances
        File distances = new File("/Users/soulja/Desktop/Fichiers/distances.txt");
        System.out.println(Arrays.deepToString(Distance.ajoutDistances(distances)));
    }
}
