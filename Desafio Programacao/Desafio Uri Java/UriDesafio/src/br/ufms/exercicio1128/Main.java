/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.exercicio1128;

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

    public static int cont;

    public static int contcomponentes = 1;

    public static void main(String[] args) throws IOException {
//         = 1;

        StringBuilder saida = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numvertices, numarestas, posini, posfim, peso;
        String[] entrada = br.readLine().split(" ");

        numvertices = Integer.parseInt(entrada[0]);
        numarestas = Integer.parseInt(entrada[1]);

        while (numarestas != 0 && numvertices != 0) {

            Vertex[] vertices = new Vertex[numvertices];
            Vertex[] verticesT = new Vertex[numvertices];
            for (int i = 0; i < numvertices; i++) {
                vertices[i] = new Vertex(String.valueOf(i));
                verticesT[i] = new Vertex(String.valueOf(i));
            }
            for (int i = 0; i < numarestas; i++) {
                entrada = br.readLine().split(" ");
                posini = Integer.parseInt(entrada[0]) - 1;
                posfim = Integer.parseInt(entrada[1]) - 1;
                peso = Integer.parseInt(entrada[2]);
                if (peso > 1) {
                    verticesT[posfim].adjacencies.add(new Edge(verticesT[posini], 1));
                    verticesT[posini].adjacencies.add(new Edge(verticesT[posfim], 1));
                    vertices[posini].adjacencies.add(new Edge(vertices[posfim], 1));
                    vertices[posfim].adjacencies.add(new Edge(vertices[posini], 1));
                } else {
                    vertices[posini].adjacencies.add(new Edge(vertices[posfim], 1));
                    verticesT[posfim].adjacencies.add(new Edge(verticesT[posini], 1));
                }
            }
            cont = 0;
            contcomponentes = 1;
            BuscaProfundidade(vertices[0]);

            if (cont != (numvertices * 2)) {
                contcomponentes = 4;
            }

            if (contcomponentes == 1) {
                cont = 0;
                Integer[] ordemvertices = OrdenaVerticesPosVisita(vertices);

                ComponentesConexos(verticesT[ordemvertices[ordemvertices.length - 1]]);
                if (cont != numvertices * 2) {
                    contcomponentes = 4;
                }
                for (int i = ordemvertices.length - 1; i >= 0; i--) {
                    if (verticesT[ordemvertices[i]].previsita == -1) {
                        contcomponentes++;
                    }
                }
            }
            if ((contcomponentes) == 1) {
                saida.append("1\n");
            } else {
                saida.append("0\n");
            }

            entrada = br.readLine().split(" ");

            numvertices = Integer.parseInt(entrada[0]);
            numarestas = Integer.parseInt(entrada[1]);
        }
        System.out.print(saida.toString());
    }

    static void imprimirDijkstra(Vertex[] vertices) {
        for (Vertex v : vertices) {
            System.out.println("Distance to " + v + ": " + v.minDistance);
            List<Vertex> path = getShortestPathTo(v);
            System.out.println("Path: " + path);
        }
    }

    public static double RetornaPesoAresta(Vertex ini, Vertex fim) {
        for (Edge t : ini.adjacencies) {
            if (t.target.name.equals(fim.name)) {
                return t.weight;
            }
        }
        return -1;
    }

    public static void BuscaProfundidade(Vertex t) {
        t.previsita = cont++;
        if (t.adjacencies != null) {
            for (Edge e : t.adjacencies) {
                if (e.target.previsita == -1) {
                    e.target.previous = t;
                    BuscaProfundidade(e.target);
                }
            }
        }
        t.posvisita = cont++;
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

    public static Integer[] OrdenaVerticesPosVisita(Vertex[] t) {
        Integer[] d = new Integer[t.length];
        Integer[] e = new Integer[t.length];
        for (int i = 0; i < t.length; i++) {
            d[i] = t[i].posvisita;
            e[i] = i;
        }
        mergeSort(d, e);
        return e;
    }

    public static void ComponentesConexos(Vertex ent) {

        ent.previsita = cont++;
        ent.componente = contcomponentes;
        if (ent.adjacencies != null) {
            for (Edge ver : ent.adjacencies) {
                if (ver.target.previsita == -1) {
                    ComponentesConexos(ver.target);
                }
            }
        }

        ent.posvisita = cont++;
    }

    public static void mergeSort(Comparable[] a, Comparable[] b) {
        Comparable[] tmp = new Comparable[a.length];
        Comparable[] temp2 = new Comparable[b.length];
        mergeSort(a, tmp, 0, a.length - 1, b, temp2);
    }

    private static void mergeSort(Comparable[] a, Comparable[] tmp, int left, int right, Comparable[] b, Comparable[] temp2) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center, b, temp2);
            mergeSort(a, tmp, center + 1, right, b, temp2);
            merge(a, tmp, left, center + 1, right, b, temp2);
        }
    }

    static void merge(Comparable[] a, Comparable[] tmp, int left, int right, int rightEnd, Comparable[] b, Comparable[] temp2) {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;
        while (left <= leftEnd && right <= rightEnd) {
            if (a[left].compareTo(a[right]) <= 0) {
                temp2[k] = b[left];
                tmp[k++] = a[left++];
            } else {
                temp2[k] = b[right];
                tmp[k++] = a[right++];
            }
        }
        while (left <= leftEnd) {
            temp2[k] = b[left];
            tmp[k++] = a[left++];
        }
// Copy rest of first half
        while (right <= rightEnd) { // Copy rest of right half
            temp2[k] = b[right];
            tmp[k++] = a[right++];
        }
// Copy tmp back
        for (int i = 0; i < num; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
            b[rightEnd] = temp2[rightEnd];
        }
    }
}
