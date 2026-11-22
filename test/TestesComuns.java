import java.util.Arrays;

public class TestesComuns {

    public static void executarTestesComuns(SortingAlgorithm algoritmo, String nome) {
        System.out.println("Testes comuns - " + nome);

        UtilTestes.verificarOrdenacao(algoritmo, nome, "vetor vazio", new int[]{});
        UtilTestes.verificar(nome, "vetor vazio nao gera comparacoes nem trocas",
                algoritmo.getComparisons() == 0 && algoritmo.getSwaps() == 0);

        UtilTestes.verificarOrdenacao(algoritmo, nome, "vetor com um elemento", new int[]{7});
        UtilTestes.verificar(nome, "um elemento nao gera comparacoes nem trocas",
                algoritmo.getComparisons() == 0 && algoritmo.getSwaps() == 0);

        UtilTestes.verificarOrdenacao(algoritmo, nome, "dois elementos fora de ordem", new int[]{2, 1});
        UtilTestes.verificarOrdenacao(algoritmo, nome, "vetor ja ordenado", GeradorDados.crescente(10));
        UtilTestes.verificarOrdenacao(algoritmo, nome, "vetor inversamente ordenado", GeradorDados.decrescente(10));
        UtilTestes.verificarOrdenacao(algoritmo, nome, "elementos repetidos", new int[]{5, 3, 5, 1, 3, 5, 1});
        UtilTestes.verificarOrdenacao(algoritmo, nome, "todos os elementos iguais", preenchido(50, 7));
        UtilTestes.verificarOrdenacao(algoritmo, nome, "numeros negativos", new int[]{-3, 10, -50, 0, 7, -1, -3});
        UtilTestes.verificarOrdenacao(algoritmo, nome, "valores extremos de int",
                new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0, -1, Integer.MAX_VALUE, Integer.MIN_VALUE});

        int[] tamanhosAleatorios = {10, 100, 1000, 5000};
        for (int tamanho : tamanhosAleatorios) {
            UtilTestes.verificarOrdenacao(algoritmo, nome, "aleatorio n=" + tamanho,
                    GeradorDados.aleatorio(tamanho, tamanho * 31L));
        }
        UtilTestes.verificarOrdenacao(algoritmo, nome, "aleatorio com muitos repetidos n=1000", faixaPequena(1000, 10, 99L));
        UtilTestes.verificarOrdenacao(algoritmo, nome, "aleatorio com negativos n=1000", comNegativos(1000, 123L));

        UtilTestes.verificar(nome, "isSorted reconhece vetor ordenado", algoritmo.isSorted(new int[]{1, 2, 2, 3}));
        UtilTestes.verificar(nome, "isSorted reconhece vetor desordenado", !algoritmo.isSorted(new int[]{1, 3, 2}));
        UtilTestes.verificar(nome, "isSorted aceita vetor vazio e unitario",
                algoritmo.isSorted(new int[]{}) && algoritmo.isSorted(new int[]{5}));

        verificarReinicioContadores(algoritmo, nome);

        algoritmo.sort(GeradorDados.aleatorio(5000, 7L));
        UtilTestes.verificar(nome, "tempo de execucao medido (> 0 ns)", algoritmo.getExecutionTime() > 0,
                "tempo = " + algoritmo.getExecutionTime());
    }

    private static void verificarReinicioContadores(SortingAlgorithm algoritmo, String nome) {
        int[] pequeno = GeradorDados.aleatorio(50, 5L);
        algoritmo.sort(GeradorDados.aleatorio(2000, 3L));

        algoritmo.sort(Arrays.copyOf(pequeno, pequeno.length));
        long comparacoes1 = algoritmo.getComparisons();
        long trocas1 = algoritmo.getSwaps();

        algoritmo.sort(Arrays.copyOf(pequeno, pequeno.length));
        long comparacoes2 = algoritmo.getComparisons();
        long trocas2 = algoritmo.getSwaps();

        UtilTestes.verificar(nome, "contadores reiniciam a cada execucao",
                comparacoes1 == comparacoes2 && trocas1 == trocas2 && comparacoes1 > 0,
                "1a: " + comparacoes1 + "/" + trocas1 + " 2a: " + comparacoes2 + "/" + trocas2);
    }

    public static int[] preenchido(int tamanho, int valor) {
        int[] vetor = new int[tamanho];
        Arrays.fill(vetor, valor);
        return vetor;
    }

    public static int[] faixaPequena(int tamanho, int faixa, long semente) {
        java.util.Random gerador = new java.util.Random(semente);
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = gerador.nextInt(faixa);
        }
        return vetor;
    }

    public static int[] comNegativos(int tamanho, long semente) {
        java.util.Random gerador = new java.util.Random(semente);
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = gerador.nextInt(2001) - 1000;
        }
        return vetor;
    }
}
