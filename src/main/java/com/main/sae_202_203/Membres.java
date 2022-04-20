package com.main.sae_202_203;

import java.io.*;
import java.util.*;

public class Membres {
    Map<String, String> membres;

    public Membres(){
        membres = new HashMap<>();
    }

    public Map<String, String> getMembres(){
        return membres;
    }

    public String toString(){
        return membres + "\n";
    }

    public static Membres lectureMembres (File fichier) throws IOException{
        Membres membres = new Membres();

        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;

        StringTokenizer tokenizer;
        do {
            ligne = bufferEntree.readLine();
            if(ligne != null){
                tokenizer = new StringTokenizer(ligne, " ");
                int longueurTokens = 0;
                while (tokenizer.hasMoreTokens()){
                    //Récupérer le nombre de mots du fichier --> Tant que le nombre de mot n'est pas atteint, afficher les pairs --> Plus tard faire fonction pour ajouter pseudos et villes
                    longueurTokens++;
                    tokenizer.nextToken();
                    System.out.println(longueurTokens);
                    for(int i=0; i<longueurTokens;i++){
                        int pair = i%2;
                        if(i==0){
                            System.out.println(tokenizer.nextToken());
                        }
                    }
                    // int i = 0 --> i++ dans la boucle --> Si i est pair alors ajouter en clé, sinon valeur
                }
            }
        }
        while(ligne != null);
        bufferEntree.close();
        return membres;
    }

}
