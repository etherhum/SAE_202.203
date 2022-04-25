package com.modele.sae_202_203;

import java.io.*;
import java.util.*;

public class Membre {
    Map<String, String> membres;

    public Membre() {
        membres = new HashMap<>();
    }

    public static void addMembres(Map<String, String> membres, String cle, String valeur) {
        membres.put(cle, valeur);
    }

    public Map<String, String> getMembres() {
        return membres;
    }

    public String toString() {
        return membres + "\n";
    }

    public static int lireMots(File fichier) throws IOException {
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;
        int compteur = 0;

        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                while (tokenizer.hasMoreTokens()) {
                    compteur++;
                    tokenizer.nextToken();
                }
            }
        }
        while (ligne != null);

        bufferEntree.close();
        return compteur;
    }

    public static Map<String, String> tableauMembres(File fichier) throws IOException {
        Map<String, String> membres = new HashMap<>();
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;
        int compteur = Membre.lireMots(fichier);

        for (int a = 0; a < compteur; a++) {
            do {
                ligne = bufferEntree.readLine();
                if (ligne != null) { // Si la ligne n'est pas nulle
                    tokenizer = new StringTokenizer(ligne, " ");
                    addMembres(membres, tokenizer.nextToken(), tokenizer.nextToken());
                }
            }
            while (ligne != null);
        }

        bufferEntree.close();
        return membres;
    }
}