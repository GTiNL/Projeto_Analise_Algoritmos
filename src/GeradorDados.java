import java.util.Random;

public class GeradorDados {

    public static int[] gerar(String tipo, int tamanho, long semente) {
        if (tipo.equals("aleatorio")) {
            return aleatorio(tamanho, semente);
        }
        if (tipo.equals("crescente")) {
            return crescente(tamanho);
        }
        return decrescente(tamanho);
    }

    public static int[] aleatorio(int tamanho, long semente) {
        Random gerador = new Random(semente);
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = gerador.nextInt(1_000_000);
        }
        return vetor;
    }

    public static int[] crescente(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = i + 1;
        }
        return vetor;
    }

    public static int[] decrescente(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = tamanho - i;
        }
        return vetor;
    }
}
