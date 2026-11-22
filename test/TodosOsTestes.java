import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class TodosOsTestes {

    public static void main(String[] args) throws IOException {
        System.out.println("=== Testes de todos os algoritmos ===");
        System.out.println();
        InsertionSortTest.executar();
        System.out.println();
        SelectionSortTest.executar();
        System.out.println();
        MergeSortTest.executar();
        System.out.println();
        QuickSortTest.executar();
        System.out.println();
        executarTesteIntegracao();

        Path arquivo = Path.of("resultados", "testes.csv");
        UtilTestes.gravarCsv(arquivo);
        UtilTestes.imprimirResumo();
        System.out.println("Relatorio dos testes salvo em: " + arquivo.toAbsolutePath());
        System.exit(UtilTestes.getFalhas() == 0 ? 0 : 1);
    }

    private static void executarTesteIntegracao() {
        System.out.println("Teste de integracao - todos os algoritmos sobre a mesma entrada");
        SortingAlgorithm[] algoritmos = {new InsertionSort(), new SelectionSort(), new MergeSort(), new QuickSort()};
        for (String tipo : Main.TIPOS_ENTRADA) {
            int[] original = GeradorDados.gerar(tipo, 3000, 2024L);
            int[] esperado = Arrays.copyOf(original, original.length);
            Arrays.sort(esperado);
            boolean todosIguais = true;
            String detalhes = "";
            for (SortingAlgorithm algoritmo : algoritmos) {
                int[] copia = Arrays.copyOf(original, original.length);
                try {
                    algoritmo.sort(copia);
                } catch (RuntimeException e) {
                    detalhes += algoritmo.getClass().getSimpleName() + " lancou " + e.getClass().getSimpleName() + " ";
                }
                if (!Arrays.equals(copia, esperado)) {
                    todosIguais = false;
                    detalhes += algoritmo.getClass().getSimpleName() + " diferente de Arrays.sort ";
                }
            }
            UtilTestes.verificar("Integracao", "4 algoritmos iguais a Arrays.sort (" + tipo + ", n=3000)",
                    todosIguais, detalhes.trim());
        }
    }
}
