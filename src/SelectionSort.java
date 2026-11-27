public class SelectionSort implements SortingAlgorithm {
    private long comparacoes;
    private long trocas;
    private long tempoExecucao;

    @Override
    public void sort(int[] vetor) {
        comparacoes = 0;
        trocas = 0;
        long inicio = System.nanoTime();

        for (int i = 0; i < vetor.length - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                if (menor(vetor[j], vetor[indiceMenor])) {
                    indiceMenor = j;
                }
            }
            if (indiceMenor != i) {
                trocar(vetor, i, indiceMenor);
            }
        }

        tempoExecucao = System.nanoTime() - inicio;
    }

    private boolean menor(int a, int b) {
        comparacoes++;
        return a < b;
    }

    private void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
        trocas++;
    }

    @Override
    public long getComparisons() {
        return comparacoes;
    }

    @Override
    public long getSwaps() {
        return trocas;
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