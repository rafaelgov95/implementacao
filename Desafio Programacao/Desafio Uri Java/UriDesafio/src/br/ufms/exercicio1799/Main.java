/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.exercicio1799;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        public final double weight = 0;

        public Edge(Vertex argTarget) {
            target = argTarget;
//            weight = argWeight;
        }
    }
    static String vetorReferencias[];

    public static void adicionarNoVetor(String aInserir) {
        int i = 1;

        if (aInserir.equals("Entrada")) {
            vetorReferencias[0] = "Entrada";
        } else if (aInserir.equals("Saida")) {
            vetorReferencias[vetorReferencias.length - 1] = "Saida";
        } else {
            while (vetorReferencias[i] != null && !vetorReferencias[i].equals(aInserir)) {
                i++;
            }
            if (i < vetorReferencias.length - 1) {
                vetorReferencias[i] = aInserir;
            }
        }
    }

    public static int getIndiceDoVetor(String procurado) {
        for (int i = 0; i < vetorReferencias.length; i++) {
            if (vetorReferencias[i] != null && vetorReferencias[i].equals(procurado)) {
                return i;
            }
        }
        return -1;
    }

    public static int cont; // para contar a pr´e-ordem e p´os-ordem da busca em largura e profundidade

    // Controla a visita, usado para a Busca em Largura -- Declara como Global
    static Queue<Vertex> aVisitar = new LinkedList<Vertex>();

    //Busca em Largura
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

        String[] entrada = br.readLine().split(" ");
        numvertices = Integer.parseInt(entrada[0]);
        numarestas = Integer.parseInt(entrada[1]);

        vetorReferencias = new String[numvertices];
        /*---------Declara¸c~ao de vertices----------*/
        Vertex[] vertices = new Vertex[numvertices];

        for (int i = 0; i < numvertices; i++) {
            vertices[i] = new Vertex(String.valueOf(i));
        }
        /*-----------------------------------------*/

        for (int i = 0; i < numarestas; i++) {
            entrada = br.readLine().split(" ");

            adicionarNoVetor(entrada[0]);
            adicionarNoVetor(entrada[1]);

            posini = getIndiceDoVetor(entrada[0]);
            posfim = getIndiceDoVetor(entrada[1]);

            /*---------Inserir uma aresta em um vertice----------*/
            vertices[posini].adjacencies.add(new Edge(vertices[posfim]));

            /*---------Inserir uma aresta em um vertice n~ao direcionado----------*/
            vertices[posfim].adjacencies.add(new Edge(vertices[posini]));

            /*---------Grafo Transposto para verificar os componentes conexos----------*/
//            verticesT[posfim].adjacencies.add(new Edge(verticesT[posini], peso));
        }

        BuscaLargura(vertices[0]);

        int indiceQueijo = getIndiceDoVetor("*");

        double distanciaInicioAteQueijo = vertices[indiceQueijo].previsita;
//        System.out.println(distanciaInicioAteQueijo);

        cont = 0;
        for (Vertex v : vertices) {
            v.previsita = -1;
        }

        BuscaLargura(vertices[indiceQueijo]);
        double distanciaAteASaida = vertices[numvertices - 1].previsita;

        System.out.println((int) (distanciaAteASaida + distanciaInicioAteQueijo));

//        double distanciaQueijoAteSaida = vertices[vertices.length - 1].minDistance;
//        System.out.println(distanciaInicioAteQueijo + distanciaQueijoAteSaida);
    }

    /*----------Busca de menor caminho para arestas n~ao negativas----------*/
    //Calcula a distancia Utilizando Dijkstra
    public static void Dijkstra(Vertex source) {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            if (u.adjacencies != null) {
                // Visita cada aresta de u
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

    /*----------Retorna o Peso de Determinada Aresta----------*/
    public static double RetornaPesoAresta(Vertex ini, Vertex fim) {
        for (Edge t : ini.adjacencies) {
            if (t.target.name.equals(fim.name)) {
                return t.weight;
            }
        }
        return -1;
    }

}
