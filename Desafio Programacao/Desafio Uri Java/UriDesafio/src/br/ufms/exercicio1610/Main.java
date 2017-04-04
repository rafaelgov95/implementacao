package br.ufms.exercicio1610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    static int cont, contcomponentes;

    public static void main(String[] args) throws IOException {
        BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
        int posini, posfim;

        int t = Integer.parseInt(ler.readLine());

        for (int i = 0; i < t; i++) {
            contcomponentes = 0;
            String entrada[] = ler.readLine().split(" ");
            int numVertices = Integer.parseInt(entrada[0]);
            int numArestas = Integer.parseInt(entrada[1]);

//            for (int j = 0; j < numArestas; j++) {
//                entrada = ler.readLine().split(" ");
//            }
            Vertex[] vertices = new Vertex[numVertices];
            Vertex[] verticesT = new Vertex[numVertices];

            //Criando os Vertex
            for (int j = 0; j < numVertices; j++) {
                vertices[j] = new Vertex(String.valueOf(j));
                verticesT[j] = new Vertex(String.valueOf(j));

            }

            for (int j = 0; j < numArestas; j++) {
                entrada = ler.readLine().split(" ");
                posini = Integer.parseInt(entrada[0]) - 1;
                posfim = Integer.parseInt(entrada[1]) - 1;

                /*---------Inserir uma aresta em um vertice----------*/
                vertices[posini].adjacencies.add(new Edge(vertices[posfim]));
                /*---------Grafo Transposto para verificar os componentes conexos----------*/
                verticesT[posfim].adjacencies.add(new Edge(verticesT[posini]));
            }

            /*---------Procedimentos para Componentes Conexos----------*/
            //Efetua a busca em profundiade a partir de qualquer v´ertice
            cont = 0;
            BuscaProfundidade(vertices[0]);

            //Ordena os v´ertices a partir do posvisita
            Integer[] ordemvertices = OrdenaVerticesPosVisita(vertices);

            //Busca com o grafo transposto, buscando pelo maior valor para o menor
            cont = 0;
            contcomponentes = 1; //Preciso startar o componente em 1 e em cada chamada incrementar
            for (int j = ordemvertices.length - 1; j >= 0; j--) {
                if (verticesT[ordemvertices[j]].previsita == -1) {
                    ComponentesConexos(verticesT[ordemvertices[j]]);
                    contcomponentes++;
                }
            }
            contcomponentes--;
            // para contar a pr´e-ordem e p´os-ordem da busca em largura e profundidade
            //Busca em Profundidade
            if (contcomponentes < numVertices) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

        }
        //   public static int cont; // para contar a pr´e-ordem e p´os-ordem da busca em largura e profundidade
        //Busca em Profundidade

        /**
         *
         * @param t
         */
    }

    public static void ComponentesConexos(Vertex ent) {
        //pre Visitado
        ent.previsita = cont++;
        ent.componente = contcomponentes;
        if (ent.adjacencies != null) {
            for (Edge ver : ent.adjacencies) {
                if (ver.target.previsita == -1) {
                    ComponentesConexos(ver.target);
                }
            }
        }

        //pos visita - N~ao precisa do posvisita - "Mas vai que..."
        ent.posvisita = cont++;
    }

    //Ordenar as arestas - O retorno ´e um vetor onde indica quais s~ao os vertices que devem ser executados primeiro
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

    //MergeSort para ordena¸c~ao do Vetor
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

    private static void merge(Comparable[] a, Comparable[] tmp, int left, int right, int rightEnd, Comparable[] b, Comparable[] temp2) {
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

        while (left <= leftEnd) { // Copy rest of first half
            temp2[k] = b[left];
            tmp[k++] = a[left++];
        }

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

    public static void BuscaProfundidade(Vertex t) {
        //Controla a Pr´e Visita
        t.previsita = cont++;//insere a visita
        if (t.adjacencies != null) {
            for (Edge e : t.adjacencies) {
                if (e.target.previsita == -1) {
                    e.target.previous = t;
                    BuscaProfundidade(e.target);
                }
            }
        }
        //Controla a P´os Visita
        t.posvisita = cont++;//insere a finaliza¸c~ao do vertice
    }

    static class Edge {

        public final Vertex target;
//    public final double weight;

        public Edge(Vertex argTarget) {
            target = argTarget;
//        weight = argWeight;
        }
    }

    static class Vertex implements Comparable<Vertex> {

        public final String nome;
        List<Edge> adjacencies = new ArrayList<Edge>();

        public Vertex(String argName) {
            nome = argName;
        }

        public String toString() {
            return nome;
        }

        //Caminhos Minimos
        public double minDistance = Double.POSITIVE_INFINITY;
        public Vertex previous;

        public int compareTo(Vertex other) {
            return Double.compare(minDistance, other.minDistance);
        }

        //Busca Profundidade
        public int posvisita = -1;

        //Busca largura e Profundidade
        public int previsita = -1;

        //A qual Componente conexo o vertice pertence
        public int componente = -1;

    }
}