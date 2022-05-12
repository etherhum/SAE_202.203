package com.modele.sae_202_203;

import java.io.*;
import java.util.*;

public class Scenario {

    List<String> vendeurs;
    List<String> acheteurs;

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

    public String toString(){
        return vendeurs +  "\n" + acheteurs;
    }

    public void ajoutVendeurAcheteur(String vendeur, String acheteur){
        vendeurs.add(vendeur);
        acheteurs.add(acheteur);
    }

    public static Scenario lectureScenario (File fichier) throws IOException{
        Scenario scenario = new Scenario();

        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;

        StringTokenizer tokenizer;
        do {
            ligne = bufferEntree.readLine();
            if(ligne != null){
                tokenizer = new StringTokenizer(ligne, " ->");
                while (tokenizer.hasMoreTokens()){
                    System.out.println(tokenizer.nextToken());
                }
            }
        }
        while(ligne != null);
        bufferEntree.close();
        return scenario;
    }

    public static Scenario listeScenarios(File fichier) throws IOException {
        Scenario scenario = new Scenario();
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;
        int compteur = Membre.lireMots(fichier);
        for (int a = 0; a < compteur; a++) {
            do {
                ligne = bufferEntree.readLine();
                if (ligne != null) { // Si la ligne n'est pas nulle
                    tokenizer = new StringTokenizer(ligne, " ->");
                    scenario.addVendeurs(tokenizer.nextToken());
                    scenario.addAcheteurs(tokenizer.nextToken());
                }
            }
            while (ligne != null);
        }
        bufferEntree.close();
        return scenario;
    }

    public static ArrayList<String> afficherDistances(Scenario scenario){
        ArrayList<String> resultat = new ArrayList<>();
        resultat.add(scenario.getAcheteurs().toString() + " -- " + scenario.getVendeurs().toString() + "// Distance:");
        return resultat;
    }
}
