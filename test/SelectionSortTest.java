public class SelectionSortTest {

    public static boolean executar() {
        int falhasAntes = UtilTestes.getFalhas();
        String nome = "SelectionSort";
        SortingAlgorithm algoritmo = new SelectionSort();

        TestesComuns.executarTestesComuns(algoritmo, nome);

        System.out.println("Testes especificos - " + nome);

        algoritmo.sort(GeradorDados.crescente(10));
        UtilTestes.verificar(nome, "crescente n=10: 45 comparacoes e 0 trocas",
                algoritmo.getComparisons() == 45 && algoritmo.getSwaps() == 0,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " trocas");

        algoritmo.sort(GeradorDados.decrescente(10));
        UtilTestes.verificar(nome, "decrescente n=10: 45 comparacoes e 5 trocas",
                algoritmo.getComparisons() == 45 && algoritmo.getSwaps() == 5,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " trocas");

        algoritmo.sort(GeradorDados.aleatorio(100, 11L));
        UtilTestes.verificar(nome, "aleatorio n=100: sempre n(n-1)/2 = 4950 comparacoes e no maximo n-1 trocas",
                algoritmo.getComparisons() == 4950 && algoritmo.getSwaps() <= 99,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " trocas");

        algoritmo.sort(new int[]{5, 2, 4, 6, 1, 3});
        UtilTestes.verificar(nome, "exemplo da documentacao [5,2,4,6,1,3]: 15 comparacoes e 3 trocas",
                algoritmo.getComparisons() == 15 && algoritmo.getSwaps() == 3,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " trocas");

        return UtilTestes.getFalhas() == falhasAntes;
    }

    public static void main(String[] args) {
        boolean ok = executar();
        UtilTestes.imprimirResumo();
        System.exit(ok ? 0 : 1);
    }
}