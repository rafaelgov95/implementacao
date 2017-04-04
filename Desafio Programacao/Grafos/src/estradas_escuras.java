//
//import br.ufms.uri.problem1931.Grafo;
//import br.ufms.uri.problem1931.AgmPrim;
//import br.ufms.uri.problem1931.FPHeapMinIndireto;
//import br.ufms.uri.problem1931.Lista;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
//public class estradas_escuras {
//
//    static StringBuilder saida = new StringBuilder();
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder saida = new StringBuilder();
//        int nvertices, narestas, v1, v2, peso, cont;
//        long somador, somatotal;
//        String[] entrada = br.readLine().split(" ");
//        nvertices = Integer.parseInt(entrada[0]);
//        narestas = Integer.parseInt(entrada[1]);
//        while (nvertices != 0 && narestas != 0) {
//            Grafo grafo = new Grafo(nvertices);
//            somatotal = 0;
//            for (int i = 0; i < narestas; i++) {
//                entrada = br.readLine().split(" ");
//                v1 = Integer.parseInt(entrada[0]);
//                v2 = Integer.parseInt(entrada[1]);
//                peso = Integer.parseInt(entrada[2]);
//                grafo.insereAresta(v1, v2, peso);
//                grafo.insereAresta(v2, v1, peso);
////                somatotal = somatotal + peso;
//            }
//            AgmPrim arvore_minima = new AgmPrim(grafo);
//            arvore_minima.obterAgm(0);
//            somador = arvore_minima.soma_pesos(0);
//            saida.append(somador);
//            saida.append("\n");
//            entrada = br.readLine().split(" ");
//            nvertices = Integer.parseInt(entrada[0]);
//            narestas = Integer.parseInt(entrada[1]);
//        }
//        System.out.print(saida.toString());
//    }
//}
//
//class Grafo {
////Classe para controlar as arestas
//
//    public static class Aresta {
//
//        int v1;
//        int v2;
//        int peso;
//
//        public Aresta(int v1, int v2, int peso) {
//            this.v1 = v1;
//            this.v2 = v2;
//            this.peso = peso;
//        }
//
//        public int peso() {
//            return this.peso;
//        }
//
//        public int v1() {
//            return this.v1;
//        }
//
//        public int v2() {
//            return this.v2;
//        }
//    }
//
//    public class Celula {
//
//        int vertice, peso;
//
//        public Celula(int v, int p) {
//            this.vertice = v;
//            this.peso = p;
//        }
//
//        public boolean equals(Object obj) {
//            Celula item = (Celula) obj;
//            return (this.vertice == item.vertice);
//        }
//    }
//    private Lista adj[];
//    private int numVertices;
//
//    public Grafo(int numVertices) {
//        this.adj = new Lista[numVertices];
//        this.numVertices = numVertices;
//        for (int i = 0; i < this.numVertices; i++) {
//            this.adj[i] = new Lista();
//
//        }
//    }
//
//    public void insereAresta(int v1, int v2, int peso) {
//        Celula item = new Celula(v2, peso);
//        this.adj[v1].insere(item);
//    }
//
//    public boolean existeAresta(int v1, int v2) {
//        Celula item = new Celula(v2, 0);
//        return (this.adj[v1].pesquisa(item) != null);
//    }
//
//    public boolean listaAdjVazia(int v) {
//        return this.adj[v].vazia();
//    }
//
//    public Aresta primeiroListaAdj(int v) {
//
//        Celula item = (Celula) this.adj[v].primeiro();
//        return item != null ? new Aresta(v, item.vertice, item.peso) : null;
//    }
//
//    public Aresta proxAdj(int v) {
//        Celula item = (Celula) this.adj[v].proximo();
//        return item != null ? new Aresta(v, item.vertice, item.peso) : null;
//    }
//
//    public Aresta retiraAresta(int v1, int v2) throws Exception {
//        Celula chave = new Celula(v2, 0);
//        Celula item = (Celula) this.adj[v1].retira(chave);
//        return item != null ? new Aresta(v1, v2, item.peso) : null;
//    }
//
//    public void imprime() {
//        for (int i = 0; i < this.numVertices; i++) {
//            System.out.println("Vertice " + i + ": ");
//            Celula item = (Celula) this.adj[i].primeiro();
//            while (item != null) {
//                System.out.println(" " + item.vertice + " (" + item.peso + ")");
//                item = (Celula) this.adj[i].proximo();
//            }
//        }
//    }
//
//    public int numVertices() {
//        return this.numVertices;
//
//    }
//}
//
//class Lista {
//
//    private static class Celula {
//
//        Object item;
//        Celula prox;
//    }
//    private Celula primeiro, ultimo, pos;
//
//    public Lista() {
//        this.primeiro = new Celula();
//        this.pos = this.primeiro;
//        this.ultimo = this.primeiro;
//        this.primeiro.prox = null;
//    }
//
//    public boolean vazia() {
//        return (this.primeiro == this.ultimo);
//    }
//
//    public Object pesquisa(Object chave) {
//        if (this.vazia() || chave == null) {
//            return null;
//        }
//        Celula aux = this.primeiro;
//        while (aux.prox != null) {
//            if (aux.prox.item.equals(chave)) {
//                return aux.prox.item;
//            }
//            aux = aux.prox;
//        }
//        return null;
//    }
//
//    public void insere(Object x) {
//        this.ultimo.prox = new Celula();
//        this.ultimo = this.ultimo.prox;
//        this.ultimo.item = x;
//        this.ultimo.prox = null;
//    }
//
//    public Object retira(Object chave) throws Exception {
//        if (this.vazia() || (chave == null)) {
//            throw new Exception("Erro: Lista vazia ou chave invalida");
//        }
//        Celula aux = this.primeiro;
//        while (aux.prox != null && !aux.prox.item.equals(chave)) {
//            aux = aux.prox;
//        }
//        if (aux.prox == null) {
//            return null;
//        }
//
//        Celula q = aux.prox;
//        Object item = q.item;
//        aux.prox = q.prox;
//        if (aux.prox == null) {
//            this.ultimo = aux;
//        }
//        return item;
//    }
//
//    public Object retiraPrimeiro() throws Exception {
//        if (this.vazia()) {
//            throw new Exception("Erro: Lista vazia");
//
//        }
//
//        Celula aux = this.primeiro;
//        Celula q = aux.prox;
//        Object item = q.item;
//        aux.prox = q.prox;
//        if (aux.prox == null) {
//            this.ultimo = aux;
//        }
//        return item;
//    }
//
//    public Object primeiro() {
//        this.pos = primeiro;
//        return proximo();
//    }
//
//    public Object proximo() {
//        this.pos = this.pos.prox;
//        if (this.pos == null) {
//            return null;
//        } else {
//            return this.pos.item;
//        }
//    }
//
//    public void imprime() {
//        Celula aux = this.primeiro.prox;
//        while (aux != null) {
//            System.out.println(aux.item.toString());
//            aux = aux.prox;
//        }
//    }
//}
//
//class FPHeapMinIndireto {
//
//    private double p[];
//    private int n, pos[], fp[];
//
//    public FPHeapMinIndireto(double p[], int v[]) {
//        this.p = p;
//        this.fp = v;
//        this.n = this.fp.length - 1;
//        this.pos = new int[this.n];
//        for (int u = 0; u < this.n; u++) {
//            this.pos[u] = u + 1;
//        }
//    }
//
//    public void refaz(int esq, int dir) {
//        int j = esq * 2;
//        int x = this.fp[esq];
//        while (j <= dir) {
//            if ((j < dir) && this.p[fp[j]] > this.p[fp[j + 1]]) {
//                j++;
//            }
//            if (this.p[x] <= this.p[fp[j]]) {
//                break;
//            }
//            this.fp[esq] = this.fp[j];
//            this.pos[fp[j]] = esq;
//            esq = j;
//            j = esq * 2;
//        }
//        this.fp[esq] = x;
//        this.pos[x] = esq;
//    }
//
//    public void constroi() {
//        int esq = n / 2 + 1;
//        while (esq > 1) {
//            esq--;
//            this.refaz(esq, this.n);
//        }
//    }
//
//    public int retiraMin() throws Exception {
//        int minimo;
//        if (this.n < 1) {
//            throw new Exception("Erro: heap vazio");
//        } else {
//            minimo = this.fp[1];
//            this.fp[1] = this.fp[this.n];
//            this.pos[fp[this.n--]] = 1;
//            this.refaz(1, this.n);
//        }
//        return minimo;
//    }
//
//    public void diminuiChave(int i, double chaveNova) throws Exception {
//        i = this.pos[i];
//        int x = fp[i];
//        if (chaveNova < 0) {
//            throw new Exception("Erro: chaveNova com valor Incorreto");
//        }
//        this.p[x] = chaveNova;
//        while ((i > 1) && (this.p[x] <= this.p[fp[i / 2]])) {
//            this.fp[i] = this.fp[i / 2];
//            this.pos[fp[i / 2]] = i;
//            i /= 2;
//        }
//        this.fp[i] = x;
//        this.pos[x] = i;
//    }
//
//    boolean vazio() {
//        return this.n == 0;
//    }
//}
//
//class AgmPrim {
//
//    private int antecessor[];
//    private double p[];
//    private Grafo grafo;
//
//    public AgmPrim(Grafo grafo) {
//        this.grafo = grafo;
//    }
//
//    public void obterAgm(int raiz) throws Exception {
//        int n = this.grafo.numVertices();
//        this.p = new double[n]; //peso dos v ́
//
//        int vs[] = new int[n + 1];
//        boolean itensHeap[] = new boolean[n];
//        this.antecessor = new int[n];
//        for (int u = 0; u < n; u++) {
//            this.antecessor[u] = -1;
//            p[u] = Double.MAX_VALUE; //infinito
//            vs[u + 1] = u; //Heap indireto a ser constru ́
//
//            itensHeap[u] = true;
//        }
//        p[raiz] = 0;
//        FPHeapMinIndireto heap = new FPHeapMinIndireto(p, vs);
//        heap.constroi();
//        while (!heap.vazio()) {
//            int u = heap.retiraMin();
//            itensHeap[u] = false;
//            if (!this.grafo.listaAdjVazia(u)) {
//                Grafo.Aresta adj = grafo.primeiroListaAdj(u);
//                while (adj != null) {
//                    int v = adj.v2();
//                    if (itensHeap[v] && (adj.peso() < this.peso(v))) {
//                        antecessor[v] = u;
//                        heap.diminuiChave(v, adj.peso());
//                    }
//                    adj = grafo.proxAdj(u);
//                }
//            }
//        }
//    }
//
//    public int antecessor(int u) {
//        return this.antecessor[u];
//    }
//
//    public double peso(int u) {
//        return this.p[u];
//    }
//
//    public void imprime() {
//        for (int u = 0; u < this.p.length; u++) {
//            if (this.antecessor[u] != -1) {
//                System.out.println("(" + antecessor[u] + "," + u + ")--p: " + peso(u));
//            }
//        }
//    }
//
//    public void imprime_lig() {
//        for (int u = 0; u < this.p.length; u++) {
//            if (this.antecessor[u] != -1) {
//                if (antecessor[u] < u) {
//                    System.out.println(antecessor[u] + 1 + " " + (u + 1));
//                } else {
//                    System.out.println((u + 1) + " " + (antecessor[u] + 1));
//                }
//            }
//        }
//    }
//
//    public long soma_pesos(int cont) {
//        long somapeso;
//        somapeso = 0;
//        for (int u = 0; u < this.p.length; u++) {
//            if (this.antecessor[u] != -1) {
//                somapeso = somapeso + (long) peso(u);
//            }
//        }
////System.out.println(somapeso);
//        return somapeso;
//    }
//}
