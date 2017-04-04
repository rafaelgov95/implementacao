/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.exercicio1288;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafael
 */
//MOCHILA
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));

        int casosDeTeste = Integer.parseInt(ler.readLine());

        for (int k = 0; k < casosDeTeste; k++) {
            String entrada[] = ler.readLine().split(" ");
            int contItens = Integer.parseInt(entrada[0]);
//            System.out.println(contItens);
            int[] valor = new int[contItens + 1];
            int[] peso = new int[contItens + 1];

            for (int i = 1; i <= contItens; i++) {
                entrada = ler.readLine().split(" ");
                valor[i] = Integer.parseInt(entrada[0]);
                peso[i] = Integer.parseInt(entrada[1]);
            }

            entrada = ler.readLine().split(" ");
            int tamanhoMochila = Integer.parseInt(entrada[0]);

            int[][] Matriz = new int[contItens + 1][tamanhoMochila + 1];

            for (int i = 1; i < contItens + 1; i++) {
                for (int j = 1; j < tamanhoMochila + 1; j++) {
                    if (peso[i] > j) {
                        Matriz[i][j] = Matriz[i - 1][j];
                    } else if (Matriz[i - 1][j - peso[i]] + valor[i] > Matriz[i - 1][j]) {
                        Matriz[i][j] = Matriz[i - 1][j - peso[i]] + valor[i];

                    } else {
                        Matriz[i][j] = Matriz[i - 1][j];
                    }
                }
            }

            entrada = ler.readLine().split(" ");
            int forcaCastelo = Integer.parseInt(entrada[0]);

            if (forcaCastelo <= Matriz[contItens][tamanhoMochila]) {
                System.out.println("Missao completada com sucesso");
            } else {
                System.out.println("Falha na missao");
            }
        }
    }
}
