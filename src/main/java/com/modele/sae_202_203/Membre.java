package com.modele.sae_202_203;

import java.io.*;
import java.util.*;

public class Membre {
    HashMap<String, String> membres; //Structure Membre:Ville

    public Membre() {
        membres = new HashMap<>();
    }

    public String toString() { return membres + "\n"; }

    public static void addMembres(HashMap<String, String> membres, String cle, String valeur) {
        membres.put(cle, valeur);
    }

    public static ArrayList<String> pairVille(String parNom1, String parNom2){
        ArrayList<String> cle = new ArrayList<>();
        cle.add(parNom1);
        cle.add(parNom2);
        return cle;
    }

    public static HashMap<String, String> convertMembres(File fichier) throws IOException {
        //////////////////
        // Check: OK
        // Lire le fichier membre et retourner un HashMap<Membre: Ville>
        //////////////////
        HashMap<String, String> membres = new HashMap<>();
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) { // Si la ligne n'est pas nulle
                tokenizer = new StringTokenizer(ligne, " ");
                String ligneMembre = tokenizer.nextToken();
                String ligneVille = tokenizer.nextToken();
                addMembres(membres, ligneMembre, ligneVille);
            }
        }
        while (ligne != null);
        bufferEntree.close();
        return membres;
    }

    public static ArrayList<String> lireVilles(File fichier) throws IOException {
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;
        ArrayList<String> arrayfile = new ArrayList<>();
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                tokenizer.nextToken();
                arrayfile.add(tokenizer.nextToken());
            }
        } while (ligne != null);
        bufferEntree.close();
        ArrayList<String> villes_tri = new ArrayList<>();
        for (String ville : arrayfile) {
            if (!villes_tri.contains(ville)) {
                villes_tri.add(ville);
            }
        }
        Collections.sort(villes_tri);
        return villes_tri;
    }
}