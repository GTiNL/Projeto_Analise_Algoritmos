import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilTestes {
    private static final List<String> registros = new ArrayList<>();
    private static int aprovados = 0;
    private static int falhas = 0;

    public static void verificar(String algoritmo, String teste, boolean condicao) {
        verificar(algoritmo, teste, condicao, "");
    }

    public static void verificar(String algoritmo, String teste, boolean condicao, String detalhes) {
        if (condicao) {
            aprovados++;
            System.out.println("  [OK]     " + teste);
        } else {
            falhas++;
            System.out.println("  [FALHOU] " + teste + (detalhes.isEmpty() ? "" : " -> " + detalhes));
        }
        registros.add(algoritmo + ";" + teste + ";" + (condicao ? "OK" : "FALHOU") + ";" + detalhes);
    }

    public static void verificarOrdenacao(SortingAlgorithm algoritmo, String nome, String teste, int[] entrada) {
        int[] esperado = Arrays.copyOf(entrada, entrada.length);
        Arrays.sort(esperado);
        int[] obtido = Arrays.copyOf(entrada, entrada.length);
        algoritmo.sort(obtido);
        boolean correto = Arrays.equals(obtido, esperado) && algoritmo.isSorted(obtido);
        String detalhes = correto || entrada.length > 20 ? "" :
                "esperado " + Arrays.toString(esperado) + ", obtido " + Arrays.toString(obtido);
        verificar(nome, teste, correto, detalhes);
    }

    public static void pendente(String algoritmo) {
        verificar(algoritmo, "algoritmo implementado", false, "ainda pendente");
    }

    public static int getAprovados() {
        return aprovados;
    }

    public static int getFalhas() {
        return falhas;
    }

    public static void imprimirResumo() {
        System.out.println();
        System.out.println("Testes aprovados: " + aprovados);
        System.out.println("Testes com falha: " + falhas);
        System.out.println(falhas == 0 ? "RESULTADO: TODOS OS TESTES PASSARAM" : "RESULTADO: EXISTEM FALHAS");
    }

    public static void gravarCsv(Path arquivo) throws IOException {
        Files.createDirectories(arquivo.toAbsolutePath().getParent());
        try (PrintWriter saida = new PrintWriter(Files.newBufferedWriter(arquivo, StandardCharsets.UTF_8))) {
            saida.println("algoritmo;teste;resultado;detalhes");
            for (String registro : registros) {
                saida.println(registro);
            }
        }
    }
}
