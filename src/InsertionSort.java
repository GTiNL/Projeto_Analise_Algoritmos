public class InsertionSort implements SortingAlgorithm {
    private long comparacoes;
    private long movimentos;
    private long tempoExecucao;

    @Override
    public void sort(int[] vetor) {
        comparacoes = 0;
        movimentos = 0;
        long inicio = System.nanoTime();

        for (int i = 1; i < vetor.length; i++) {
            int chave = vetor[i];
            int j = i - 1;
            while (j >= 0 && menor(chave, vetor[j])) {
                vetor[j + 1] = vetor[j];
                movimentos++;
                j--;
            }
            if (j + 1 != i) {
                vetor[j + 1] = chave;
                movimentos++;
            }
        }

        tempoExecucao = System.nanoTime() - inicio;
    }

    private boolean menor(int a, int b) {
        comparacoes++;
        return a < b;
    }

    @Override
    public long getComparisons() {
        return comparacoes;
    }

    @Override
    public long getSwaps() {
        return movimentos;
    }

    @Override
    public long getExecutionTime() {
        return tempoExecucao;
    }

    @Override
    public boolean isSorted(int[] vetor) {
        for (int i = 1; i < vetor.length; i++) {
            if (vetor[i - 1] > vetor[i]) {
                return false;
            }
        }
        return true;
    }
}
