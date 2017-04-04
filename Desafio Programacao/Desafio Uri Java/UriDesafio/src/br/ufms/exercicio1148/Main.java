/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.exercicio1148;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author rafael
 */
class Vertex implements Comparable<Vertex> {

    public final String name;
    List<Edge> adjacencies = new ArrayList<Edge>();

    public Vertex(String argName) {
        name = argName;
    }

    public String toString() {
        return name;
    }
//Caminhos Minimos
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }
//Busca Profundidade
    public int posvisita = -1;
//Busca Largura e Profundidade
    public int previsita = -1;
//A qual Componente conexo o vertice pertence
    public int componente = -1;
}

class Edge {

    public final Vertex target;
    public final double weight;

    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder saida = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double existeAresta;
        int numvertices, numarestas, posini, posfim, peso, pesquisa;
        String[] entrada = br.readLine().split(" ");
        numvertices = Integer.parseInt(entrada[0]);
        numarestas = Integer.parseInt(entrada[1]);
        while (numarestas != 0 || numvertices != 0) {
            Vertex[] vertices = new Vertex[numvertices];
            for (int i = 0; i < numvertices; i++) {
                vertices[i] = new Vertex(String.valueOf(i));
            }
            for (int i = 0; i < numarestas; i++) {
                entrada = br.readLine().split(" ");
                posini = Integer.parseInt(entrada[0]) - 1;
                posfim = Integer.parseInt(entrada[1]) - 1;
                peso = Integer.parseInt(entrada[2]);
                existeAresta = RetornaPesoAresta(vertices[posfim], vertices[posini]);
                if (!(existeAresta == -1)) {
                    vertices[posini].adjacencies.add(new Edge(vertices[posfim], 0));
                    for (Edge t : vertices[posfim].adjacencies) {
                        if (t.target.name.equals(vertices[posini].name)) {
                            vertices[posfim].adjacencies.remove(t);
                            vertices[posfim].adjacencies.add(new Edge(vertices[posini], 0));
                            break;
                        }
                    }
                } else {
                    vertices[posini].adjacencies.add(new Edge(vertices[posfim], peso));
                }
            }
            pesquisa = Integer.parseInt(br.readLine());
            for (int i = 0; i < pesquisa; i++) {
                entrada = br.readLine().split(" ");
                posini = Integer.parseInt(entrada[0]) - 1;
                posfim = Integer.parseInt(entrada[1]) - 1;

                for (int j = 0; j < vertices.length; j++) {
                    vertices[j].minDistance = Double.POSITIVE_INFINITY;
                }
                Dijkstra(vertices[posini]);
                if (vertices[posfim].minDistance != Double.POSITIVE_INFINITY) {
                    saida.append((int) vertices[posfim].minDistance).append("\n");
                } else {
                    saida.append("Nao e possivel entregar a carta\n");
                }
            }
            saida.append("\n");
            entrada = br.readLine().split(" ");
            numvertices = Integer.parseInt(entrada[0]);
            numarestas = Integer.parseInt(entrada[1]);
        }
        System.out.print(saida.toString());
    }

    public static double RetornaPesoAresta(Vertex ini, Vertex fim) {
        for (Edge t : ini.adjacencies) {
            if (t.target.name.equals(fim.name)) {
                return t.weight;
            }
        }
        return -1;
    }

    public static void Dijkstra(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);
        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();
            if (u.adjacencies != null) {
                for (Edge e : u.adjacencies) {
                    Vertex v = e.target;
                    double weight = e.weight;
                    double distanceThroughU = u.minDistance + weight;
                    if (distanceThroughU < v.minDistance) {
                        vertexQueue.remove(v);
                        v.minDistance = distanceThroughU;
                        v.previous = u;
                        vertexQueue.add(v);
                    }
                }
            }
        }
    }

}
