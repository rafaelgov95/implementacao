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
            contcomponentes = 0;
            BuscaProfundidade(vertices[0]);
            cont = 0;
            Integer[] ordemvertices = OrdenaVerticesPosVisita(vertices);

            for (int i = ordemvertices.length - 1; i >= 0; i--) {
                if (verticesT[ordemvertices[i]].previsita == -1) {
                    ComponentesConexos(verticesT[ordemvertices[i]]);
                    contcomponentes++;
                }
            }

            if ((contcomponentes - 1) == 1) {
                saida.append("0\n");
            } else {
                saida.append("1\n");
            }

            entrada = br.readLine().split(" ");

            numvertices = Integer.parseInt(entrada[0]);
            numarestas = Integer.parseInt(entrada[1]);
        }
        System.out.println(saida.toString());
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
        while (right <= rightEnd) {
            temp2[k] = b[right];
            tmp[k++] = a[right++];
        }
        for (int i = 0; i < num; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
            b[rightEnd] = temp2[rightEnd];
        }
    }
}
