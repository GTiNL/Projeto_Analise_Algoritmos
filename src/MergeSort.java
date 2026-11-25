public void sort(int[] vetor) {
        comparacoes = 0;
        movimentos = 0;
        long inicio = System.nanoTime();

        if (vetor.length > 1) {
            int[] aux = new int[vetor.length];
            mergeSort(vetor, aux, 0, vetor.length - 1);
        }

        tempoExecucao = System.nanoTime() - inicio;
    }

    private void mergeSort(int[] vetor, int[] aux, int esquerda, int direita) {
        if (esquerda >= direita) {
            return;
        }
        int meio = esquerda + (direita - esquerda) / 2;
        mergeSort(vetor, aux, esquerda, meio);
        mergeSort(vetor, aux, meio + 1, direita);
        intercalar(vetor, aux, esquerda, meio, direita);
    }

    private void intercalar(int[] vetor, int[] aux, int esquerda, int meio, int direita) {
        for (int k = esquerda; k <= direita; k++) {
            aux[k] = vetor[k];
            movimentos++;
        }

        int i = esquerda;
        int j = meio + 1;
        for (int k = esquerda; k <= direita; k++) {
            if (i > meio) {
                vetor[k] = aux[j++];
            } else if (j > direita) {
                vetor[k] = aux[i++];
            } else if (menor(aux[j], aux[i])) {
                vetor[k] = aux[j++];
            } else {
                vetor[k] = aux[i++];
            }
            movimentos++;
        }
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

