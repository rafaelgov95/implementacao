package br.ufms.grafo.buscaProfundidade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] preVisita;
	static int[] posVisita;
	static int[][] mat; 
	static int cont=1;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numVertices, numArestas,ini, fim;
		String[] entrada = br.readLine().split(" ");
		
		numVertices = Integer.parseInt(entrada[0]);
		numArestas = Integer.parseInt(entrada[1]);
		
		mat = new int[numVertices][numVertices];
		preVisita = new int[numVertices];
		posVisita = new int[numVertices];
		
		for (int i = 0; i<numArestas; i++){
			entrada = br.readLine().split(" ");
			ini = Integer.parseInt(entrada[0]);
			fim = Integer.parseInt(entrada[1]);
			mat[ini][fim] = 1;
			mat[fim][ini] = 1;
		}
		
		for (int i = 0; i<numVertices;i++){
			preVisita[i] = -1;
			posVisita[i] = -1;
		}
		
		DFS(0);
		
		System.out.println("Pré Visita:");
		for (int i = 0; i<numVertices; i++){
			System.out.print(preVisita[i]+" ");
		}
		
		System.out.println("\nPós Visita:");
		for (int i = 0; i<numVertices; i++){
			System.out.print(posVisita[i]+" ");
		}
		
	}
	
	public static void DFS(int x){
		preVisita[x] =cont++;
		for(int i = 0; i<preVisita.length; i++){
			if (mat[x][i] == 1){
				if (preVisita[i]==-1){
					DFS(i);
				}
			}
		}
		posVisita[x] = cont++;
	}

}