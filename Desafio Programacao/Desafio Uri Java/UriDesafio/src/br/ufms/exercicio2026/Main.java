/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.exercicio2026;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author rafael
 */
public class Main {

    static int[] peso;
    static int[] valor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int galhos = Integer.parseInt(br.readLine());

        for (int i = 0; i < galhos; i++) {
            int pacotes = Integer.parseInt(br.readLine());
            int forcaDoGalho = Integer.parseInt(br.readLine());

            peso = new int[pacotes + 1];
            valor = new int[pacotes + 1];

            int[][] W = new int[pacotes + 1][forcaDoGalho + 1];
            
            for (int j = 1; j <= pacotes; j++) {
                String[] entrada = br.readLine().split(" ");
                valor[j] = Integer.parseInt(entrada[0]);
                peso[j] = Integer.parseInt(entrada[1]);
            }

            for (int k = 1; k <= pacotes; k++) {
                for (int w = 1; w <= forcaDoGalho; w++) {
                    if (peso[k] > w) {
                        W[k][w] = W[k - 1][w];
                    } else if (W[k - 1][w - peso[k]] + valor[k] > W[k - 1][w]) {
                        W[k][w] = W[k - 1][w - peso[k]] + valor[k];
                    } else {
                        W[k][w] = W[k - 1][w];
                    }
                }
            }
            System.out.println("Galho " + (i+1) + ":\n"
                    + "Numero total de enfeites: " + W[pacotes][forcaDoGalho] + "\n");
        }
    }

}