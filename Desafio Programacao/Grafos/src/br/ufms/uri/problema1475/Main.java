/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.uri.problema1475;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int resp1, resp2, t1, t2, f;
    static int vetor[];
    static int vetor2[];

    public static void main(String[] args) throws IOException {
        BufferedReader leia = new BufferedReader(new InputStreamReader(System.in));
        String entrada[];
        try {
            while (true) {
                entrada = leia.readLine().split(" ");
                f = Integer.parseInt(entrada[0]);
                vetor = new int[f];
                t1 = Integer.parseInt(entrada[2]);
                t2 = Integer.parseInt(entrada[3]);
                entrada = leia.readLine().split(" ");

                for (int i = 0; i < vetor.length; i++) {
                    vetor[i] = Integer.parseInt(entrada[i]) - Integer.parseInt(entrada[0]) + 1;
                }
                int result = 0;

                vetor2 = new int[f];
                if (t1 < t2) {
                    vetor2[0] = t1;
                } else {
                    vetor2[0] = t2;
                }
                
                for (int i = 1; i < vetor.length; i++) {
                    result = cobertura(i);
                    vetor2[i] = result;
//                    System.out.println(cobertura(i));
                }
                
                System.out.println(cobertura(vetor.length-1));
            }
        } catch (Exception e) {
        }
    }

    static int cobertura(int i) {
        if (vetor[i] - vetor[0] <= t1) {
            resp1 = t1;
        } else {
            int j = i - 1;
            while (vetor[i] - vetor[j] <= t1) {
                j--;
            }
            resp1 = vetor2[j] + t1;
        }
        if (vetor[i] - vetor[0] <= t2) {
            resp2 = t2;
        } else {
            int j = i - 1;
            while (vetor[i] - vetor[j] <= t2) {
                j--;
            }
            resp2 = vetor2[j] + t2;
        }

        return menor(resp1, resp2);
    }

    static int menor(int a, int b) {
        if (a > b) {
            return b;
        } else if (b > a) {
            return a;
        } else if (a == b) {
            return a;
        }
        return -1;
    }

}

