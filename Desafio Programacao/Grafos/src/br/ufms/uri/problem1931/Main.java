package br.ufms.uri.problem1931;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static class Vertex implements Comparable<Vertex> {

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

    static class Edge {

        public final Vertex target;
        public final double weight;

        public Edge(Vertex argTarget, double argWeight) {
            target = argTarget;
            weight = argWeight;
        }
    }
    static StringBuilder saida = new StringBuilder();

    public static void main(String[] args) throws Exception {

        StringBuilder saida = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numvertices, numarestas, posini, posfim, peso;
        String[] entrada = br.readLine().split(" ");
        numvertices = Integer.parseInt(entrada[0]);
        numarestas = Integer.parseInt(entrada[1]);

        Vertex[] vertices = new Vertex[numvertices * 2];
//        Vertex[] verticesT = new Vertex[numvertices];
        for (int i = 0; i < numvertices * 2; i++) {
            vertices[i] = new Vertex(String.valueOf(i));

        }

        for (int i = 0; i < numarestas; i++) {
            entrada = br.readLine().split(" ");
            posini = Integer.parseInt(entrada[0]) - 1;
            posfim = Integer.parseInt(entrada[1]) - 1;
            peso = Integer.parseInt(entrada[2]);
            vertices[posini * 2].adjacencies.add(new Edge(vertices[posfim * 2 + 1], peso));
            vertices[posfim * 2 + 1].adjacencies.add(new Edge(vertices[posini * 2], peso));
            vertices[posini * 2 + 1].adjacencies.add(new Edge(vertices[posfim * 2], peso));
            vertices[posfim * 2].adjacencies.add(new Edge(vertices[posini * 2 + 1], peso));
        }

        Dijkstra(vertices[0]);
        System.out.println(Double.POSITIVE_INFINITY);
        if ((vertices[numvertices * 2 - 2].minDistance) != Double.POSITIVE_INFINITY) {
            System.out.println((int) vertices[numvertices * 2 - 2].minDistance);
        } else {
            System.out.println("-1");
        }
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

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }
}
