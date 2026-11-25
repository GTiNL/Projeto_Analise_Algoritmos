public class MergeSortTest {

    public static boolean executar() {
        int falhasAntes = UtilTestes.getFalhas();
        String nome = "MergeSort";
        SortingAlgorithm algoritmo = new MergeSort();

        TestesComuns.executarTestesComuns(algoritmo, nome);

        System.out.println("Testes especificos - " + nome);

        algoritmo.sort(GeradorDados.crescente(8));
        UtilTestes.verificar(nome, "crescente n=8: 12 comparacoes e 48 movimentos",
                algoritmo.getComparisons() == 12 && algoritmo.getSwaps() == 48,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " movimentos");

        algoritmo.sort(GeradorDados.decrescente(8));
        UtilTestes.verificar(nome, "decrescente n=8: 12 comparacoes e 48 movimentos",
                algoritmo.getComparisons() == 12 && algoritmo.getSwaps() == 48,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " movimentos");

        algoritmo.sort(GeradorDados.aleatorio(1024, 13L));
        UtilTestes.verificar(nome, "aleatorio n=1024: movimentos = 2 * n * log2(n) = 20480 e comparacoes <= 10240",
                algoritmo.getSwaps() == 20480 && algoritmo.getComparisons() <= 10240,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " movimentos");

        algoritmo.sort(new int[]{5, 2, 4, 6, 1, 3});
        UtilTestes.verificar(nome, "exemplo da documentacao [5,2,4,6,1,3]: 11 comparacoes e 32 movimentos",
                algoritmo.getComparisons() == 11 && algoritmo.getSwaps() == 32,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " movimentos");

        return UtilTestes.getFalhas() == falhasAntes;
    }

    public static void main(String[] args) {
        boolean ok = executar();
        UtilTestes.imprimirResumo();
        System.exit(ok ? 0 : 1);
    }
}