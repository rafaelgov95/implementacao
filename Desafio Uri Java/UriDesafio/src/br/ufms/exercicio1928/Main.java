/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.exercicio1928;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 *
 * @author rafael
 */
public class Main {

    static class Vertex implements Comparable<Vertex> {

        public final String name;
        List<Edge> adjacencies = new ArrayList<Edge>();

        public Vertex(String argName) {
            name = argName;
        }

        @Override
        public String toString() {
            return name;
        }

        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;

        @Override
        public int compareTo(Vertex other) {
            return Double.compare(minDistance, other.minDistance);
        }

        public int posvisita = -1;

        public int previsita = -1;

        public int componente = -1;
    }

    static class Edge {

        public final Vertex target;
        public final double weight = 0;

        public Edge(Vertex argTarget) {
            target = argTarget;
        }
    }
    static String vetorReferencias[];

    public static int cont; // para contar a pr´e-ordem e p´os-ordem da busca em largura e profundidade

    static Queue<Vertex> aVisitar = new LinkedTransferQueue<Vertex>();

    public static void BuscaLargura(Vertex t) {
        if (t.previsita == -1) {
            t.previsita = cont++;
        }
        if (t.adjacencies != null) {
            for (Edge e : t.adjacencies) {
                if (e.target.previsita == -1) {
                    if (!aVisitar.contains(t)) {
                        e.target.previsita = t.previsita + 1;
                        aVisitar.add(e.target);
                    }
                }
            }
        }
        while (!aVisitar.isEmpty()) {
            BuscaLargura(aVisitar.poll());
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder saida = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numvertices, numarestas, posini, posfim, peso;

        numvertices = Integer.parseInt(br.readLine());

        String[] entrada = br.readLine().split(" ");

        Vertex[] vertices = new Vertex[numvertices];

        for (int i = 0; i < numvertices; i++) {
            vertices[i] = new Vertex(entrada[i]);
        }

        for (int i = 0; i < numvertices - 1; i++) {
            entrada = br.readLine().split(" ");
            posini = Integer.parseInt(entrada[0]) - 1;
            posfim = Integer.parseInt(entrada[1]) - 1;
            vertices[posini].adjacencies.add(new Edge(vertices[posfim]));
            vertices[posfim].adjacencies.add(new Edge(vertices[posini]));
        }

        int soma = 0;

        for (int i = 1; i <= numvertices / 2; i++) {
            for (int j = 0; j < numvertices; j++) {

                if (vertices[j].toString().equals(String.valueOf(i))) {
                    if (vertices[j].previsita > -1) {
                        soma += vertices[j].previsita;
                        for (Vertex v : vertices) {
                            v.previsita = -1;
                        }
                    } else {
                        cont = 0;
                        BuscaLargura(vertices[j]);
                    }

                }

            }

        }
        System.out.println(soma);

    }

}
