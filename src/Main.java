import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {
    static final int[] TAMANHOS = {100, 1_000, 5_000, 10_000, 50_000, 100_000};
    static final String[] TIPOS_ENTRADA = {"aleatorio", "crescente", "decrescente"};
    static final int EXECUCOES_MEDIDAS = 5;
    static final int LIMITE_QUADRATICOS = 50_000;
    static final long SEMENTE = 42L;

    public static void main(String[] args) throws IOException {
        SortingAlgorithm[] algoritmos = {
            new InsertionSort(), new SelectionSort(), new MergeSort(), new QuickSort()
        };

        new File("resultados").mkdirs();
        PrintWriter csv = new PrintWriter("resultados/resultados.csv", "UTF-8");
        csv.println("algoritmo;tipo_entrada;tamanho;execucoes_validas;tempo_medio_ns;tempo_min_ns;"
                + "tempo_max_ns;comparacoes_media;trocas_movimentos_media;validacao");

        for (String tipo : TIPOS_ENTRADA) {
            for (int tamanho : TAMANHOS) {
                int[] original = GeradorDados.gerar(tipo, tamanho, SEMENTE + tamanho);
                for (SortingAlgorithm algoritmo : algoritmos) {
                    csv.println(testarAlgoritmo(algoritmo, tipo, tamanho, original));
                }
            }
        }

        csv.close();
        salvarAmbiente();
        System.out.println("Resultados salvos em resultados/resultados.csv");
    }

    static String testarAlgoritmo(SortingAlgorithm algoritmo, String tipo, int tamanho, int[] original) {
        String nome = algoritmo.getClass().getSimpleName();
        String inicioLinha = nome + ";" + tipo + ";" + tamanho + ";";

        if (ehQuadratico(nome) && tamanho > LIMITE_QUADRATICOS) {
            System.out.println(nome + " | " + tipo + " | n=" + tamanho + " | PULADO");
            return inicioLinha + "0;;;;;;PULADO_LIMITE_QUADRATICO";
        }

        int[] aquecimento = Arrays.copyOf(original, tamanho);
        algoritmo.sort(aquecimento);
        boolean valido = estaCorreto(algoritmo, aquecimento, original);

        long tempoTotal = 0;
        long tempoMinimo = Long.MAX_VALUE;
        long tempoMaximo = 0;
        long totalComparacoes = 0;
        long totalTrocas = 0;

        for (int execucao = 1; execucao <= EXECUCOES_MEDIDAS; execucao++) {
            int[] copia = Arrays.copyOf(original, tamanho);
            algoritmo.sort(copia);

            long tempo = algoritmo.getExecutionTime();
            tempoTotal += tempo;
            tempoMinimo = Math.min(tempoMinimo, tempo);
            tempoMaximo = Math.max(tempoMaximo, tempo);
            totalComparacoes += algoritmo.getComparisons();
            totalTrocas += algoritmo.getSwaps();

            if (!estaCorreto(algoritmo, copia, original)) {
                valido = false;
            }
        }

        long tempoMedio = Math.round((double) tempoTotal / EXECUCOES_MEDIDAS);
        long mediaComparacoes = Math.round((double) totalComparacoes / EXECUCOES_MEDIDAS);
        long mediaTrocas = Math.round((double) totalTrocas / EXECUCOES_MEDIDAS);
        String validacao = valido ? "OK" : "FALHOU";

        System.out.println(nome + " | " + tipo + " | n=" + tamanho
                + " | media " + (tempoMedio / 1_000_000.0) + " ms | " + validacao);

        return inicioLinha + EXECUCOES_MEDIDAS + ";" + tempoMedio + ";" + tempoMinimo + ";" + tempoMaximo + ";"
                + mediaComparacoes + ";" + mediaTrocas + ";" + validacao;
    }

    static boolean estaCorreto(SortingAlgorithm algoritmo, int[] ordenado, int[] original) {
        int[] esperado = Arrays.copyOf(original, original.length);
        Arrays.sort(esperado);
        return algoritmo.isSorted(ordenado) && Arrays.equals(ordenado, esperado);
    }

    static boolean ehQuadratico(String nome) {
        return nome.equals("InsertionSort") || nome.equals("SelectionSort");
    }

    static void salvarAmbiente() throws IOException {
        PrintWriter arquivo = new PrintWriter("resultados/resultados_ambiente.txt", "UTF-8");
        arquivo.println("data_execucao=" + java.time.LocalDateTime.now());
        arquivo.println("java_version=" + System.getProperty("java.version"));
        arquivo.println("sistema_operacional=" + System.getProperty("os.name"));
        arquivo.println("processadores_logicos=" + Runtime.getRuntime().availableProcessors());
        arquivo.close();
    }
}
