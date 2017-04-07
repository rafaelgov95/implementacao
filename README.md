
# Autor
## Rafael Viana


# Exercício 1128  -  Ir e Vir -  
(Solução -  Busca em profundidade para saber se todos vértices se conectam entre as intersecções) 
Numa certa cidade há N intersecções ligadas por ruas de mão única e ruas com mão dupla de direcão. É uma cidade moderna, de forma que muitas ruas atravessam túneis ou têm viadutos. Evidentemente é necessário que se possa viajar entre quaisquer duas intersecções, isto é, dadas duas intersecções V e W, deve ser possível viajar de V para W e de W para V.

# Exercício 1148 – Países em Guerra 
(Solução - Primeiramente foi feito uma funcao para ve se existe aresta entre os países,  para poder fazer a adição de aresta, depois fui feito um Dijkstra na posição ini para saber qual seria o menor tempo em horas se não for possível , sendo assim se a última posição for infinito quer dizer que não foi alcançado pelo Dijkstra )
Uma agência postal na cidade A pode enviar diretamente uma carta impressa para a agência postal da cidade B se houver um acordo de envio de cartas, que determina o tempo, em horas, que uma carta leva para chegar da cidade A à cidade B (e não necessariamente o contrário).a Se não houver um acordo entre as agências A e B, a agência A pode tentar enviar a carta a quantas agências for necessário para que a carta chegue ao seu destino, se isso for possível.

# Exercício 1288 - Canhão da Destruição 
(Solução - Este exercício utilizo a técnica da mochila porque pode utilizar projéteis repetidos, o tamanho da mochila e capacidade do peso disparado por ela,  na matriz da mochila fico int[contItens + 1][tamanhoMochila + 1]; o objetivo e destruir o castelo com com peso igual ou maior possível respeitando o limite da mochila  )
A primeira linha de entrada contém o número de casos de teste. Cada caso de teste inicia com uma linha contendo um número inteiro N (1 ≤ N ≤ 50), que representa o número de projéteis de chumbo disponíveis. Seguem N linhas contendo dois inteiros X e Y, representando respectivamente o poder de destruição do projétil e o peso do projétil. A próxima linha contém um inteiro K (1 ≤ K ≤ 100) que representa a capacidade de carga do canhão e a última linha do caso de teste contém um inteiro R que indica a resistência total do castelo.
# Exercício 1475 - Remendo 
(Solução - este exercício utiliza a técnica de programação dinâmica, o objetivo e tampar todos buracos para e com os tamanhos remendos ofertados, e informa quantos remendos foi utilizado.
 A entrada contém vários casos de teste. Cada caso de teste consiste de duas linhas. A primeira linha contém quatro inteiros N (1 ≤ N ≤ 1000), C (1 ≤ C ≤ 106), (1 ≤ T1) e T2 (T2 ≤ C). O inteiro N corresponde ao número de furos no pneu e C corresponde ao comprimento da circunferência do pneu, em centímetros. Os comprimentos dos remendos, em centímetros, são dados pelos inteiros T1 e T2. A segunda linha da entrada contém N inteiros Fi (0 ≤ Fi ≤ C-1), um para cada furo, descrevendo a distância no sentido horário da marca de giz até o furo i (1 ≤ i ≤ N), em centímetros. O Final da entrada é determinado por EOF (fim de arquivo).
# Exercício 1549 - Dividindo Coca 
(Solução - utiliza cálculo do tronco do cone em geometria  )
A primeira linha de entrada contém um inteiro C que determina a quantidade de casos de teste. Cada caso de teste inicia com uma linha contendo dois inteiros, N e L (1 ≤ N ≤ 100, 1 ≤ L ≤ 108 ), indicando o número de pessoas no grupo e a quantia de Coca-Cola que deve ser dividida, em mililitros. A segunda linha contém três inteiros b, B e H (1 ≤ b ≤ B ≤ 100, 1 ≤ H ≤ 100), indicando o raio da base menor e maior do copo, assim como sua altura. Todas as medidas são dadas em centímetros.

# Exercício 1610 - Dudu faz Servico
(Solução - utiliza a tecnica de componentes conexos para saber se vai conseguir concluir o seu serviço)
Na primeira linha você terá um inteiro T (T = 100) indicando o número de casos de teste.
Na primeira linha de cada caso teremos os números inteiros N (2 ≤ N ≤ 100* ou 2 ≤ N ≤ 104**) e M (1 ≤ M ≤ 300* ou 1 ≤ M ≤ 3*104​**), indicando o número de documentos e as dependências existentes. Em cada uma das M linhas seguintes, terão dois inteiros A (1 ≤ A) e B (B ≤ N, com A != B), indicando que o documento A depender do documento B. Pode haver dependências repetidas!
*Ocorre em aproximadamente 90% dos casos de teste;
**Ocorre nos demais casos de teste

# Exercício 1799 - O Rato no Labirinto
(Solução -  A solução foi criar um vetor e abastece esse vetor com as palavras , sendo a V0 Entrada e Vn-1 saída, depois de abasteci foi utilizado dois busca em largura uma na entrada  ate o vetor que contém  o queixo e outra até a saída)
A primeira linha de entrada contém dois inteiros Pontos (4 ≤ Pontos ≤ 4000) e Ligacoes (4 ≤ Ligacoes ≤ 5000) representando respectivamente o número de pontos estrategicamente marcados no labirinto e quantidade de ligações existentes entre estes pontos. Seguem as linhas que indicam cada uma das ligações entre estes pontos. As ligação entre dois pontos indica que qualquer um dos dois pode ser a orige

# Exercício 1928- Jogo da Memória 
(Solução - Foi utilizar a busca em largura para solucionar esse problema, abastecemos um vetor com as posições das cartas e criamos um vértice para cada posição depois foi feito a conexão das arestas e então buscamos com uma junção de dois for um dividindo a quantidade de cartas em por 2 ja que seria duas cartas de cada o outro for ele busca posição da primeira carta e fazia um busca em largura e na segunda vez que o for achar uma carta igual ele conta quantas pre visitas está nesse vértice e soma em outra variável aux.)
A primeira linha da entrada contém o número de cartas N (2 ≤ N ≤ 50000, N é par). A segunda linha da entrada contém N inteiros Ci, indicando qual número está anotado na carta na posição i (1 ≤ Ci ≤ N/2, para 1 ≤ i ≤ N). As cartas são dadas na ordem crescente das posições: a primeira carta ocupa a posição 1, a segunda a posição 2, e assim por diante até a última carta, que ocupa a posição N. Cada uma das N − 1 linhas seguintes contém dois números A e B, indicando que existe uma linha desenhada entre as cartas nas posições A e B (1 ≤ A ≤ N e 1 ≤ B ≤ N).

# Exercício 1931- Mania de Par 
(Solução - a estratégia para esse problema está além das técnicas do Dijkstra , na hora da criação das arestas do vértice  teve que usar uma lógica de números impar e par para abastecer os grafos, depois foi só executar um dijkstra e saber se (vertices[numvertices * 2 - 2].minDistance ) for diferente de Infinity )
A entrada consiste de diversas linhas. A primeira linha contém 2 inteiros C e V, o número total de cidades e o número de estradas (2 ≤ C ≤ 104 e 0 ≤ V ≤ 50000). As cidades são identificadas por inteiros de 1 a C. Cada estrada liga duas cidades distintas, e há no máximo uma estrada entre cada par de cidades. Cada uma das V linhas seguintes contém três inteiros C1, C2 e G, indicando que o valor do pedágio da estrada que liga as cidades C1 e C2é G (1 ≤ C1, C2 ≤ C e 1 ≤ G ≤ 104). Patrícia está atualmente na cidade 1 e a cidade do cliente é C.

# Exercício 2027  Árvore de Natal 
(Solução - Para solucionar esse problema foi utilizado uma mochila binária, com matriz int[pacotes + 1][forcaDoGalho + 1].

A primeira linha de entrada possui um inteiro G para os galhos da árvore, e também representando o numero de casos de teste, a segunda linha de entrada possui um inteiro P (1 < P < 100) que indica o número de pacotes, a próxima linha possui um inteiro W (1 < W < 1000) que indica a capacidade de peso que o galho da árvore suporta. As próximas P linhas indicam o número de enfeites em cadas pacote E(1 < E ≤ 300) e o peso de cada pacote PC (1 ≤ PC ≤ W).

