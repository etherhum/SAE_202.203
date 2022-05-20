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

    public static Scenario listeScenarios(File fichier) throws IOException {
        //////////////////
        // Check: OK
        // Lire le fichier scenario et séparer en deux listes les acheteurs + vendeurs
        //////////////////
        Scenario scenario = new Scenario();
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;
        for (int a = 0; a < Membre.lireMots(fichier); a++) {
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

    public static ArrayList<Integer> convertirDistances(Scenario scenario, File fichierMembres, File fichierDistances) throws IOException {
        //////////////////
        // PROBLEME DANS CETTE FONCTION
        //////////////////
        Distance distance = new Distance();
        ArrayList<String> resultat = convertirPseudoVille(scenario, fichierMembres);
        ArrayList<Integer> villesIndices = convertirVilleIndice(resultat, distance, fichierDistances);
        //Conversion indices-->distance
        int[][] tabDistance = distance.ajoutDistances(fichierDistances);
        ArrayList<Integer> distances = new ArrayList<>();
        System.out.println("OK1");
        for(int i=0; i< villesIndices.size();i++){
            distances.add(tabDistance[villesIndices.get(i)][villesIndices.get(i+1)]);
            i++;
        }
        System.out.println("OK2");
        return distances;
    }

    public static ArrayList<String> convertirPseudoVille(Scenario scenario, File fichierMembres) throws IOException {
        //
        // Convertir les pseudos vendeurs et acheteurs en ville, pour être ensuite converti en indice avec autre méthode
        //
        List<String> acheteurs = scenario.getAcheteurs();
        List<String> vendeurs = scenario.getVendeurs();
        Membre membres = new Membre();
        ArrayList<String> resultat = new ArrayList<>();
        for(int a=0; a<acheteurs.size();a++){
            for(int b=1; b<acheteurs.size();b++){
                for(Map.Entry<String, String> entree: membres.convertMembres(fichierMembres).entrySet()){
                    if(b%2==1){
                        if(vendeurs.get(a).equals(entree.getKey())){
                            resultat.add(entree.getValue());
                        }
                    } else {
                        if(acheteurs.get(a).equals(entree.getKey())){
                            resultat.add(entree.getValue());
                        }

                    }
                }
            }
        }
        return resultat;
    }

    public static ArrayList<Integer> convertirVilleIndice(ArrayList<String> resultat, Distance distance, File fichierDistances) throws IOException {
        ArrayList<Integer> villesIndices = new ArrayList<>();
        for(String r: resultat){
            for(Map.Entry<String, Integer> entree: distance.ajoutVilles(fichierDistances).entrySet()){
                if(r.equals(entree.getKey())){
                    villesIndices.add(entree.getValue());
                }
            }
        }
        return villesIndices;
    }

    public static ArrayList<String> afficherDistances(Scenario scenario) throws IOException {
        ArrayList<String> resultat = new ArrayList<>();
        // On récupère les vendeurs + acheteurs du scénario
        List<String> acheteurs = scenario.getAcheteurs();
        List<String> vendeurs = scenario.getVendeurs();
        // Importation des fichiers
        File fichierMembres = new File("/Users/soulja/Desktop/Fichiers/membres_APLI.txt");
        File fichierDistances = new File("/Users/soulja/Desktop/Fichiers/distances.txt");
        ArrayList<String> villes = convertirPseudoVille(scenario, fichierMembres);
        int b=0;
        int c=1;
        for(int a=0; a<acheteurs.size();a++){
            resultat.add(vendeurs.get(a) + " - " + acheteurs.get(a) + " | " + villes.get(b) + " - " + villes.get(c) + " | Distance: " + Scenario.convertirDistances(scenario, fichierMembres, fichierDistances).get(a) + "\n");
            b=b+2;
            c=c+2;
        }
        return resultat;
    }
}
