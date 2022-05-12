package com.modele.sae_202_203;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Distance {
    Map<String, Integer> villes;
    int[][] tabDistances;

    public Distance() {
        tabDistances = new int[29][29];
        villes = new HashMap<>();
    }

    public Map<String, Integer> getVilles(){ return villes; }
    public int[][] getTabDistances(){ return tabDistances; }

    public Map<String, Integer> ajoutVilles(File fichier) throws IOException {
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;
        ArrayList<String> arrayfile = new ArrayList<>();
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                arrayfile.add(tokenizer.nextToken());
            }
        } while (ligne != null);
        int a = 0;
        while (a != arrayfile.size()) {
            villes.put(arrayfile.get(a), a);
            a++;
        }
        bufferEntree.close();
        return villes;
    }

    public int[][] ajoutDistances(File fichier) throws IOException {
        ajoutVilles(fichier);
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;
        ArrayList<String> arrayfile = new ArrayList<>();
        do {
            ligne = bufferEntree.readLine();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                while(tokenizer.hasMoreTokens()){
                    arrayfile.add(tokenizer.nextToken());
                }

            }
        } while (ligne != null);
        int a = 0;
        while (a != arrayfile.size()) {
            if(arrayfile.get(a).matches(".*[a-z].*")){
                arrayfile.remove(a);
            }
            a++;
        }

        int x=0;
        for(int b=0; b<tabDistances.length; b++){
            for(int c=0; c< tabDistances.length; c++){
                tabDistances[b][c]=Integer.parseInt(arrayfile.get(x));
                x++;
            }
        }
        return tabDistances;
    }
}