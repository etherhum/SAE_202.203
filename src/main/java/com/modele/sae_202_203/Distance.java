package com.modele.sae_202_203;

import com.vue.sae_202_203.Constantes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.System.err;

public class Distance implements Constantes {
    Map<String, Integer> villes;
    int[][] tabDistances;

    public Distance() {
        tabDistances = new int[29][29];
        villes = new HashMap<>();
    }

    public Map<String, Integer> getVilles(){ return villes; }

    public int[][] getTabDistances(){ return tabDistances; }

    public static HashMap <TreeSet <String>, Integer> lectureDistanceMap(File fichier) {
        HashMap <TreeSet <String>, Integer> distance = new HashMap<>();
        String ligne ;
        StringTokenizer tokenizer ;
        try {
            BufferedReader bufferEntree = new BufferedReader(new FileReader (fichier));
            int noLig = 0;
            do {
                ligne = bufferEntree.readLine();
                if (ligne != null) {
                    tokenizer = new StringTokenizer(ligne, " ");
                    tokenizer.nextToken();  // sauter nom ville
                    int noCol = 0;
                    while (tokenizer.hasMoreTokens() && noCol < NOM_VILLE.length) {
                        String entier = tokenizer.nextToken();
                        //System.out.println(noLig + " " + noCol);
                        if (noLig == noCol)  // m ville, trien à faire
                            continue;

                        TreeSet cle = Membre.pairVille(NOM_VILLE[noLig], NOM_VILLE[noCol++]);
                        if (distance.containsKey(cle))  // les 2 villes sont dejà dans map
                            continue;

                        distance.put(cle, Integer.parseInt(entier));
                    }
                }
                noLig++;
            }  while (ligne != null);
            bufferEntree.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distance;
    }
}