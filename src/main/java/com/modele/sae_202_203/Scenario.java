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

    public static ArrayList<Integer> convertirDistances(Scenario scenario, File fichierMembres, File fichierDistances) throws IOException {
        Distance distance = new Distance();
        List<String> acheteurs = scenario.getAcheteurs();
        List<String> vendeurs = scenario.getVendeurs();
        Membre membres = new Membre();
        //Conversion Pseudo->Ville
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
        //Conversion ville-->indice
        ArrayList<Integer> villesIndices = new ArrayList<>();
        for(String r: resultat){
            for(Map.Entry<String, Integer> entree: distance.ajoutVilles(fichierDistances).entrySet()){
                if(r.equals(entree.getKey())){
                    villesIndices.add(entree.getValue());
                }
            }
        }
        //Conversion indices-->distance
        int[][] tabDistance = distance.ajoutDistances(fichierDistances);
        ArrayList<Integer> distances = new ArrayList<>();
        for(int i=0; i< villesIndices.size();i++){
            distances.add(tabDistance[villesIndices.get(i)][villesIndices.get(i+1)]);
            i++;
        }
        return distances;
    }

    public static ArrayList<String> afficherDistances(Scenario scenario) throws IOException {
        ArrayList<String> resultat = new ArrayList<>();
        List<String> acheteurs = scenario.getAcheteurs();
        List<String> vendeurs = scenario.getVendeurs();
        File fichierMembres = new File("/Users/soulja/Desktop/Fichiers/membres_APLI.txt");
        File fichierDistances = new File("/Users/soulja/Desktop/Fichiers/distances.txt");
        for(int a=0; a<acheteurs.size();a++){
            resultat.add(vendeurs.get(a) + " -> " + acheteurs.get(a) + " // Distance: " + Scenario.convertirDistances(scenario, fichierMembres, fichierDistances).get(a) + "\n");
        }
        return resultat;
    }
}
