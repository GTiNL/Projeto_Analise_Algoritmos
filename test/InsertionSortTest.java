public class InsertionSortTest {

    public static boolean executar() {
        int falhasAntes = UtilTestes.getFalhas();
        String nome = "InsertionSort";
        SortingAlgorithm algoritmo = new InsertionSort();

        TestesComuns.executarTestesComuns(algoritmo, nome);

        System.out.println("Testes especificos - " + nome);

        algoritmo.sort(GeradorDados.crescente(10));
        UtilTestes.verificar(nome, "melhor caso n=10: 9 comparacoes e 0 movimentos",
                algoritmo.getComparisons() == 9 && algoritmo.getSwaps() == 0,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " movimentos");

        algoritmo.sort(GeradorDados.decrescente(10));
        UtilTestes.verificar(nome, "pior caso n=10: 45 comparacoes e 54 movimentos (45 deslocamentos + 9 insercoes)",
                algoritmo.getComparisons() == 45 && algoritmo.getSwaps() == 54,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " movimentos");

        algoritmo.sort(new int[]{5, 2, 4, 6, 1, 3});
        UtilTestes.verificar(nome, "exemplo da documentacao [5,2,4,6,1,3]: 12 comparacoes e 13 movimentos",
                algoritmo.getComparisons() == 12 && algoritmo.getSwaps() == 13,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " movimentos");

        return UtilTestes.getFalhas() == falhasAntes;
    }

    public static void main(String[] args) {
        boolean ok = executar();
        UtilTestes.imprimirResumo();
        System.exit(ok ? 0 : 1);
    }
}
