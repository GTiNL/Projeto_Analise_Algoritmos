# Projeto de Análise de Algoritmos - Algoritmos de ordenação de vetores

Implementação e comparação de Insertion Sort, Selection Sort, Merge Sort e Quick Sort em Java. o programa mede o tempo de execução, o número de comparações e o número de trocas/movimentos de cada algoritmo.

## Integrantes

Gabriel - Insertion Sort  
Felipe - Selection Sort  
Pérola - Merge Sort  
Rodrigo - Quick Sort

## Interface comum

Todos os algoritmos implementam a interface orientada:

```java
public interface SortingAlgorithm {
void sort(int[] vetor);
long getComparisons();
long getSwaps();
long getExecutionTime();
boolean isSorted(int[] vetor);
}
```

## Estrutura

```text
Projeto_Analise_Algoritmos
src/
SortingAlgorithm.java - interface comum
GeradorDados.java - gera vetores aleatórios, crescentes e decrescentes
Main.java - benchmark: executa e mede os 4 algoritmos
InsertionSort.java
SelectionSort.java
MergeSort.java
QuickSort.java

test/
UtilTestes.java - verificação e registro dos testes
TestesComuns.java - testes que valem para os 4 algoritmos
TodosOsTestes.java - roda tudo + teste de integração
<Algoritmo>Test.java - testes específicos de cada algoritmo
```

## Requisitos

Java JDK 17 ou +

## Como executar

```powershell
javac -encoding UTF-8 -d bin src/*.java test/*.java
java -cp bin TodosOsTestes
java -cp bin Main
```

TodosOsTestes roda os testes dos 4 algoritmos e um teste de integração.

Main testa os algoritmos com n = 100, 1.000, 5.000, 10.000, 50.000 e 100.000, nas entradas aleatória, crescente e decrescente, e grava resultados/resultados.csv.

Testes de um algoritmo só: java -cp bin InsertionSortTest (ou SelectionSortTest, MergeSortTest, QuickSortTest).

## Procedimento de medição

Para cada tipo de entrada e tamanho é gerado um vetor (aleatório com semente fixa). Cada algoritmo recebe cópias dele: 1 execução inicial (que é descartada) + 5 execuções medidas. O tempo é medido com System.nanoTime() somente dentro de sort(), e toda execução é validada com isSorted() e comparada com Arrays.sort(). Insertion e Selection (O(n²)) não são executados com n acima de 50.000.
