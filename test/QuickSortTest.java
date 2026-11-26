public class QuickSortTest {

    public static boolean executar() {
        int falhasAntes = UtilTestes.getFalhas();
        String nome = "QuickSort";
        SortingAlgorithm algoritmo = new QuickSort();

        TestesComuns.executarTestesComuns(algoritmo, nome);

        System.out.println("Testes especificos - " + nome);

        algoritmo.sort(GeradorDados.crescente(1000));
        UtilTestes.verificar(nome, "crescente n=1000: 0 trocas e comparacoes na ordem de n log n (< 20000)",
                algoritmo.getSwaps() == 0 && algoritmo.getComparisons() < 20000,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " trocas");

        algoritmo.sort(GeradorDados.decrescente(1000));
        UtilTestes.verificar(nome, "decrescente n=1000: comparacoes na ordem de n log n (< 20000)",
                algoritmo.getComparisons() < 20000,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " trocas");

        algoritmo.sort(TestesComuns.preenchido(10000, 3));
        UtilTestes.verificar(nome, "todos iguais n=10000: nao degrada para O(n^2) (< 300000 comparacoes)",
                algoritmo.getComparisons() < 300000,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " trocas");

        boolean semEstouroPilha = true;
        try {
            int[] grande = GeradorDados.crescente(100_000);
            algoritmo.sort(grande);
            semEstouroPilha = algoritmo.isSorted(grande);
        } catch (StackOverflowError e) {
            semEstouroPilha = false;
        }
        UtilTestes.verificar(nome, "crescente n=100000 ordena sem estouro de pilha", semEstouroPilha);

        algoritmo.sort(new int[]{5, 2, 4, 6, 1, 3});
        UtilTestes.verificar(nome, "exemplo da documentacao [5,2,4,6,1,3]: 17 comparacoes e 5 trocas",
                algoritmo.getComparisons() == 17 && algoritmo.getSwaps() == 5,
                algoritmo.getComparisons() + " comparacoes, " + algoritmo.getSwaps() + " trocas");

        return UtilTestes.getFalhas() == falhasAntes;
    }

    public static void main(String[] args) {
        boolean ok = executar();
        UtilTestes.imprimirResumo();
        System.exit(ok ? 0 : 1);
    }
}
