package com.modele.sae_202_203;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Distance {
    String[][] distances;

    public Distance() {
        distances = new String[29][30];
    }

    public static String[][] ajoutDistances(File fichier) throws IOException {
        String[][] distances = new String[29][30];

        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne;
        StringTokenizer tokenizer;

        int a = 0;
        int b = 1;
        while (a != 29) {
            do {
                ligne = bufferEntree.readLine();
                if (ligne != null) {
                    tokenizer = new StringTokenizer(ligne, " ");
                    distances[a][0] = tokenizer.nextToken();
                    while (b != 30) {
                        distances[a][b] = tokenizer.nextToken();
                        b++;
                    }
                    b = 1;
                    a++;
                }
            } while (ligne != null);
        }
        bufferEntree.close();
        return distances;
    }
}
