package com.modele;

import java.io.*;
import java.util.*;

public class Membre {
    HashMap<String, String> membresVilles; //Structure Membre:Ville
    ArrayList<String> membres = new ArrayList<>();
    ArrayList<String> villes = new ArrayList<>();

    public Membre() {
        membresVilles = new HashMap<>();
    }

    public String toString() {
        return membresVilles + "\n";
    }

    public static void addMembres(HashMap<String, String> membres, String cle, String valeur) {
        membres.put(cle, valeur);
    }

    public static void addMembresListe(ArrayList<String> membres, String valeur) {
        membres.add(valeur);
    }

    public static void addVillesListe(ArrayList<String> villes, String valeur) {
        villes.add(valeur);
    }

    public static HashMap<String, String> convertMembres(File fichier) throws IOException {
        //////////////////
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

    public ArrayList<String> listeMembres(File fichier) throws IOException {
        //////////////////
        // Lire le fichier membre et retourner une ArrayList<String> des membres stylisée pour rentrer dans le textArea
        //////////////////
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;
        int a = 0;
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) { // Si la ligne n'est pas nulle
                tokenizer = new StringTokenizer(ligne, " ");
                String ligneMembre = a + " - " + tokenizer.nextToken() + "\n";
                tokenizer.nextToken();
                addMembresListe(membres, ligneMembre);
                a++;
            }
        }
        while (ligne != null);
        bufferEntree.close();
        return membres;
    }

    public ArrayList<String> listeVilles(File fichier) throws IOException {
        //////////////////
        // Lire le fichier membre et retourner une ArrayList<String> des villes stylisée pour rentrer dans le textArea
        //////////////////
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;
        int a = 0;
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                tokenizer.nextToken();
                String ligneVille = a + " - " + tokenizer.nextToken() + "\n";
                addVillesListe(villes, ligneVille);
                a++;
            }
        }
        while (ligne != null);
        bufferEntree.close();
        return villes;
    }
}