package com.modele.sae_202_203;

import java.io.*;
import java.util.*;

public class Membre {
    Map<String, String> membres;

    public Membre() {
        membres = new HashMap<>();
    }

    public Map<String, String> getMembres() { return membres; }

    public String toString() { return membres + "\n"; }

    public static void addMembres(Map<String, String> membres, String cle, String valeur) {
        membres.put(cle, valeur);
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

    public Map<String, String> convertMembres(File fichier) throws IOException {
        //Retourne un Map<Membre, Ville>
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

    public void convertirPseudo(File fichier, File fichier2) throws IOException {
        //Converti un pseudo en ville
        Scenario scenario = Scenario.listeScenarios(fichier2);
        membres = convertMembres(fichier);
        Iterator<String> a = membres.keySet().iterator(); // Membres clés
        Iterator<String> b = scenario.getVendeurs().iterator();
        Iterator<String> v = scenario.getAcheteurs().iterator();
        ArrayList<String> resultatA = new ArrayList<>(); //
        ArrayList<String> resultatB = new ArrayList<>();
        while(a.hasNext()){
            resultatA.add(a.next());
        }
        while(b.hasNext()){
            resultatB.add(b.next());
            resultatB.add(v.next());
        }
        Collections.sort(resultatA);
        Collections.sort(resultatB);
        for (String s : resultatA) {
            for (String value : resultatB) {
                if (s.equals(value)) {
                    System.out.println(s);
                }
            }
        }
    }


    /*
    Algorithme (convertirPseudo):
    Chercher dans liste scenario vendeurs et acheteurs:
    Si vendeur ou acheteur = membre_key:
        alors vendeur ou acheteur = membre_value

     return scenario
     */

    public void convertirPseudoTest(File fichier, File fichier2) throws IOException {
        //Converti un pseudo en ville
        Scenario scenario = Scenario.listeScenarios(fichier2);
        membres = convertMembres(fichier);
        Iterator<String> a = membres.keySet().iterator(); // Membres clés
        Iterator<String> b = scenario.getVendeurs().iterator();
        Iterator<String> v = scenario.getAcheteurs().iterator();

        ArrayList<String> resultatA = new ArrayList<>(); //
        ArrayList<String> resultatB = new ArrayList<>();
        while(a.hasNext()){
            resultatA.add(a.next());
        }
        while(b.hasNext()){
            resultatB.add(b.next());
            resultatB.add(v.next());
        }
        Collections.sort(resultatA);
        Collections.sort(resultatB);
        for (String s : resultatA) {
            for (String value : resultatB) {
                if (s.equals(value)) {
                    System.out.println(s);
                }
            }
        }
    }

}