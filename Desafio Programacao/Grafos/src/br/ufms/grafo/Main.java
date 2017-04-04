///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package br.ufms.grafo;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
///**
// *
// * @author rafael
// */
//public class Main {
//
//    static int[] pre = new int[4];
//    static int[] pos = new int[4];
//    static int[][] mat = new int[4][4];
//    int cont = 0;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int numVertices, numAresta, ini, fim;
//        String[] entrada = br.readLine().split(" ");
//        numVertices = Integer.parseInt(entrada[0]);
//
//        mat = new int[numVertices][numVertices];
//
//        entrada = br.readLine().split(" ");
//        numAresta = Integer.parseInt(entrada[0]);
//        for (int i = 0; i < numAresta; i++) {
//            entrada = br.readLine().split(" ");
//            ini = Integer.parseInt(entrada[0]);
//            fim = Integer.parseInt(entrada[1]);
//            mat[ini - 1][fim - 1] = 1;
//            mat[fim - 1][ini - 1] = 1;
//
//        }
//    }
//
//    public static void BuscaProfundidade(int x) {
//        pre[x]=cont++;
//        for (int i = 0; i < 4; i++) {
//
//            if (mat[x][i] == 1) {
//                if (pre[x] != -1) {
//                    BuscaProfundidade(x);
//                }
//            }
//        }
//        pos[x]=cont++;
//    }
//}
