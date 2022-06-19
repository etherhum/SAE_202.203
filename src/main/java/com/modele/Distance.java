package com.modele;

import com.Chemins;

import java.io.*;
import java.util.*;

public class Distance implements Chemins {
    public static ArrayList<String> listerVilles(File fichierDistance) {
        /*
         * Retourne une ArrayList listant toutes les villes du fichier distances.txt
         */
        ArrayList<String> villes = new ArrayList<>();
        String ligne;
        StringTokenizer tokenizer;
        try {
            BufferedReader bufferEntree = new BufferedReader(new FileReader(fichierDistance));
            do {
                // Parcours du fichier distances.txt
                ligne = bufferEntree.readLine();
                if (ligne != null) {
                    tokenizer = new StringTokenizer(ligne, " ");
                    while (tokenizer.hasMoreTokens()) {
                        String entier = tokenizer.nextToken();
                        villes.add(entier); // Ajout du nom de la ville à l'Array
                        for (int it = 0; it < 29; it++) { // Sauter les 29 distances suivant le nom de la ville
                            tokenizer.nextToken();
                        }
                    }
                }
            } while (ligne != null);
            bufferEntree.close();
        } catch (IOException e) { // En cas d'erreur
            e.printStackTrace();
        }
        return villes;
    }

    public static HashMap<ArrayList<String>, Integer> convertirDistance(File fichierDistance) {
        /*
         * Retourne un HashMap contenant l'intégralité des combinaisons de distances entre deux villes
         */
        HashMap<ArrayList<String>, Integer> distance = new HashMap<>();
        String ligne;
        StringTokenizer tokenizer;
        ArrayList<String> villes = Distance.listerVilles(new File(CHEMIN_DISTANCE));
        try {
            BufferedReader bufferEntree = new BufferedReader(new FileReader(fichierDistance));
            int it1 = 0;
            do {
                // Parcour du fichier distances.txt
                ligne = bufferEntree.readLine();
                if (ligne != null) {
                    tokenizer = new StringTokenizer(ligne, " ");
                    tokenizer.nextToken();
                    int it2 = 0;
                    while (it2 < villes.size()) {
                        String entier = tokenizer.nextToken();
                        ArrayList<String> cle = new ArrayList<>();
                        cle.add(villes.get(it1));
                        cle.add(villes.get(it2++));
                        distance.put(cle, Integer.parseInt(entier));
                    }
                }
                it1++;
            } while (ligne != null);
            bufferEntree.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distance;
    }
}