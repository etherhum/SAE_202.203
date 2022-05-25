package com.modele.sae_202_203;

import com.ConstantesChemins;

import java.io.*;
import java.util.*;

public class Distance implements ConstantesChemins {
    public static ArrayList<String> listerVilles(File fichierDistance) {
        //////////////////
        // Retourne une ArrayList listant toutes les villes du fichier distances.txt
        //////////////////
        ArrayList<String> villes = new ArrayList<>();
        String ligne;
        StringTokenizer tokenizer;
        try {
            BufferedReader bufferEntree = new BufferedReader(new FileReader(fichierDistance));
            do {
                ligne = bufferEntree.readLine();
                if (ligne != null) {
                    tokenizer = new StringTokenizer(ligne, " ");
                    while (tokenizer.hasMoreTokens()) {
                        String entier = tokenizer.nextToken();
                        villes.add(entier);
                        for (int a = 0; a < 29; a++) {
                            tokenizer.nextToken();
                        }
                    }
                }
            } while (ligne != null);
            bufferEntree.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return villes;
    }

    public static HashMap<ArrayList<String>, Integer> convertirDistance(File fichierDistance) {
        //////////////////
        // Retourne un HashMap contenant l'intégralité des combinaisons de distances entre deux villes
        //////////////////
        HashMap<ArrayList<String>, Integer> distance = new HashMap<>();
        String ligne;
        StringTokenizer tokenizer;
        ArrayList<String> villes = Distance.listerVilles(new File(CHEMIN_DISTANCE));
        try {
            BufferedReader bufferEntree = new BufferedReader(new FileReader(fichierDistance));
            int b = 0;
            do {
                ligne = bufferEntree.readLine();
                if (ligne != null) {
                    tokenizer = new StringTokenizer(ligne, " ");
                    tokenizer.nextToken();
                    int a = 0;
                    while (a < villes.size()) {
                        String entier = tokenizer.nextToken();
                        ArrayList<String> cle = new ArrayList<>();
                        cle.add(villes.get(b));
                        cle.add(villes.get(a++));
                        distance.put(cle, Integer.parseInt(entier));
                    }
                }
                b++;
            } while (ligne != null);
            bufferEntree.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distance;
    }
}