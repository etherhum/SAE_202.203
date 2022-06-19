package com.modele;

import java.io.*;
import java.util.*;

public class Scenario {

    private final List<String> vendeurs;
    private final List<String> acheteurs;

    public Scenario(){
        vendeurs = new ArrayList<>();
        acheteurs = new ArrayList<>();
    }

    public List<String> getVendeurs(){
        return vendeurs;
    }

    public List<String> getAcheteurs(){
        return acheteurs;
    }

    public void addVendeurs(String string) {
        vendeurs.add(string);
    }

    public void addAcheteurs(String string) {
        acheteurs.add(string);
    }

    public void addVendeursAcheteurs(String strVendeurs, String strAcheteurs){
        addVendeurs(strVendeurs);
        addAcheteurs(strAcheteurs);
    }

    public String toString(){
        return vendeurs +  "\n" + acheteurs;
    }

    public static Scenario listeScenarios(File fichier) throws IOException {
        /*
        Lire le fichier scenario et séparer en deux listes les acheteurs + vendeurs
         */
        Scenario scenario = new Scenario();
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;
        do {
            // Parcours du fichier scénario
            ligne = bufferEntree.readLine();
            if (ligne != null) { // Si la ligne n'est pas nulle
                tokenizer = new StringTokenizer(ligne, " ->");
                String ligneVendeurs = tokenizer.nextToken();
                String ligneAcheteurs = tokenizer.nextToken();
                scenario.addVendeursAcheteurs(ligneVendeurs, ligneAcheteurs);
            }
        } while (ligne != null);
        bufferEntree.close();
        return scenario;
    }
}
