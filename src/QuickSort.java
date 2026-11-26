public class QuickSort implements SortingAlgorithm {
    private long comparacoes;
    private long trocas;
    private long tempoExecucao;

    @Override
    public void sort(int[] vetor) {
        comparacoes = 0;
        trocas = 0;
        long inicio = System.nanoTime();

        if (vetor.length > 1) {
            quickSort(vetor, 0, vetor.length - 1);
        }

        tempoExecucao = System.nanoTime() - inicio;
    }

    private void quickSort(int[] vetor, int esquerda, int direita) {
        int pivo = vetor[esquerda + (direita - esquerda) / 2];
        int i = esquerda;
        int j = direita;

        while (i <= j) {
            while (menor(vetor[i], pivo)) {
                i++;
            }
            while (menor(pivo, vetor[j])) {
                j--;
            }
            if (i <= j) {
                if (i < j) {
                    trocar(vetor, i, j);
                }
                i++;
                j--;
            }
        }

        if (esquerda < j) {
            quickSort(vetor, esquerda, j);
        }
        if (i < direita) {
            quickSort(vetor, i, direita);
        }
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
