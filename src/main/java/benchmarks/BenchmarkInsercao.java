package benchmarks;

import java.util.ArrayList;
import arraysdinamicos.ArrayDinamico;

public class BenchmarkInsercao {

  private static final int NUMERO_OPERACOES = 10_000;
  private static final int NUMERO_RODADAS = 35; // Executa múltiplas vezes para média

  public static void main(String[] args) {
    System.out.println("=== BENCHMARK: Inserção em Estruturas de Dados ===\n");

    // Aquece a JVM executando algumas operações antes das medições reais
    // Isso força o compilador JIT a otimizar o código antes de medirmos
    aquecerJVM();

    System.out.println("Iniciando medições...\n");

    // Testa inserção no FINAL
    testarInsercaoFinal();

    System.out.println("\n" + "=".repeat(60) + "\n");

    // Testa inserção no INÍCIO
    testarInsercaoInicio();

    System.out.println("\n=== Fim do Benchmark ===");
  }

  /**
   * Aquece a JVM executando operações antes das medições reais.
   * Isso é necessário porque a JVM compila código para instruções de máquina
   * sob demanda (compilação JIT). As primeiras execuções são mais lentas.
   */
  private static void aquecerJVM() {
    System.out.println("Aquecendo a JVM...");

    ArrayDinamico<Integer> warmup1 = new ArrayDinamico<>();
    ArrayList<Integer> warmup2 = new ArrayList<>();

    for (int i = 0; i < 10_000; i++) {
      warmup1.adicionar(i);
      warmup2.add(i);
    }

    System.out.println("Aquecimento concluído.\n");
  }

  /**
   * Testa inserção no FINAL da estrutura (operação mais eficiente)
   */
  private static void testarInsercaoFinal() {
    System.out.println("TESTE 1: Inserção no FINAL da estrutura");
    System.out.println("-".repeat(60));

    long[] temposArrayDinamico = new long[NUMERO_RODADAS];
    long[] temposArrayList = new long[NUMERO_RODADAS];

    // Executa múltiplas rodadas para calcular média
    for (int rodada = 0; rodada < NUMERO_RODADAS; rodada++) {
      // Força garbage collection antes de cada medição para minimizar interferência
      System.gc();

      try {
        Thread.sleep(100); // Pequena pausa para estabilizar o sistema
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      // Mede ArrayDinamico
      temposArrayDinamico[rodada] = medirInsercaoFinalArrayDinamico();

      // Pausa entre medições
      System.gc();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      // Mede ArrayList do JCF
      temposArrayList[rodada] = medirInsercaoFinalArrayList();

      System.out.printf("Rodada %d concluída.%n", rodada + 1);
    }

    // Calcula e exibe estatísticas
    exibirEstatisticas("ArrayDinamico (nosso)", temposArrayDinamico);
    exibirEstatisticas("ArrayList (JCF)", temposArrayList);

    // Calcula e exibe comparação
    double mediaArrayDinamico = calcularMedia(temposArrayDinamico);
    double mediaArrayList = calcularMedia(temposArrayList);
    double proporcao = mediaArrayDinamico / mediaArrayList;

    System.out.printf("%nCOMPARAÇÃO: ArrayDinamico é %.2fx %s que ArrayList%n",
        Math.abs(proporcao - 1.0) + 1.0,
        proporcao > 1.0 ? "mais lento" : "mais rápido");
  }

  /**
   * Mede tempo de inserção no final usando ArrayDinamico
   */
  private static long medirInsercaoFinalArrayDinamico() {
    ArrayDinamico<Integer> lista = new ArrayDinamico<>();

    long inicio = System.nanoTime();

    for (int i = 0; i < NUMERO_OPERACOES; i++) {
      lista.adicionar(i); // Inserção no final
    }

    long fim = System.nanoTime();

    return fim - inicio; // Retorna tempo em nanossegundos
  }

  /**
   * Mede tempo de inserção no final usando ArrayList
   */
  private static long medirInsercaoFinalArrayList() {
    ArrayList<Integer> lista = new ArrayList<>();

    long inicio = System.nanoTime();

    for (int i = 0; i < NUMERO_OPERACOES; i++) {
      lista.add(i); // Inserção no final
    }

    long fim = System.nanoTime();

    return fim - inicio;
  }

  /**
   * Testa inserção no INÍCIO da estrutura (operação mais custosa)
   */
  private static void testarInsercaoInicio() {
    System.out.println("TESTE 2: Inserção no INÍCIO da estrutura");
    System.out.println("-".repeat(60));

    long[] temposArrayDinamico = new long[NUMERO_RODADAS];
    long[] temposArrayList = new long[NUMERO_RODADAS];

    for (int rodada = 0; rodada < NUMERO_RODADAS; rodada++) {
      System.gc();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      temposArrayDinamico[rodada] = medirInsercaoInicioArrayDinamico();

      System.gc();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      temposArrayList[rodada] = medirInsercaoInicioArrayList();

      System.out.printf("Rodada %d concluída.%n", rodada + 1);
    }

    exibirEstatisticas("ArrayDinamico (nosso)", temposArrayDinamico);
    exibirEstatisticas("ArrayList (JCF)", temposArrayList);

    double mediaArrayDinamico = calcularMedia(temposArrayDinamico);
    double mediaArrayList = calcularMedia(temposArrayList);
    double proporcao = mediaArrayDinamico / mediaArrayList;

    System.out.printf("%nCOMPARAÇÃO: ArrayDinamico é %.2fx %s que ArrayList%n",
        Math.abs(proporcao - 1.0) + 1.0,
        proporcao > 1.0 ? "mais lento" : "mais rápido");

    // Comparação IMPORTANTE: inserção início vs. final
    System.out.println("\n" + "=".repeat(60));
    System.out.println("ANÁLISE CRÍTICA: Inserção no INÍCIO vs. no FINAL");
    System.out.println("=".repeat(60));

    // Carrega os tempos da inserção no final (precisamos guardá-los globalmente
    // ou recalcular aqui - por simplicidade, vou recalcular)
    long tempoFinalArrayDinamico = medirInsercaoFinalArrayDinamico();
    long tempoFinalArrayList = medirInsercaoFinalArrayList();

    double fatorArrayDinamico = (double) mediaArrayDinamico / tempoFinalArrayDinamico;
    double fatorArrayList = (double) mediaArrayList / tempoFinalArrayList;

    System.out.printf("ArrayDinamico: inserção no início é %.2fx mais lenta que no final%n",
        fatorArrayDinamico);
    System.out.printf("ArrayList: inserção no início é %.2fx mais lenta que no final%n",
        fatorArrayList);
  }

  /**
   * Mede tempo de inserção no início usando ArrayDinamico
   */
  private static long medirInsercaoInicioArrayDinamico() {
    ArrayDinamico<Integer> lista = new ArrayDinamico<>();

    long inicio = System.nanoTime();

    for (int i = 0; i < NUMERO_OPERACOES; i++) {
      lista.inserir(0, i); // Inserção sempre na posição 0 (início)
    }

    long fim = System.nanoTime();

    return fim - inicio;
  }

  /**
   * Mede tempo de inserção no início usando ArrayList
   */
  private static long medirInsercaoInicioArrayList() {
    ArrayList<Integer> lista = new ArrayList<>();

    long inicio = System.nanoTime();

    for (int i = 0; i < NUMERO_OPERACOES; i++) {
      lista.add(0, i); // Inserção sempre na posição 0
    }

    long fim = System.nanoTime();

    return fim - inicio;
  }

  /**
   * Calcula e exibe estatísticas de um conjunto de medições
   */
  private static void exibirEstatisticas(String nome, long[] tempos) {
    double media = calcularMedia(tempos);
    long minimo = calcularMinimo(tempos);
    long maximo = calcularMaximo(tempos);
    double desvioPadrao = calcularDesvioPadrao(tempos, media);

    System.out.printf("%n%s:%n", nome);
    System.out.printf("  Média:        %8.2f ms%n", media / 1_000_000.0);
    System.out.printf("  Mínimo:       %8.2f ms%n", minimo / 1_000_000.0);
    System.out.printf("  Máximo:       %8.2f ms%n", maximo / 1_000_000.0);
    System.out.printf("  Desvio Padrão: %8.2f ms%n", desvioPadrao / 1_000_000.0);
  }

  private static double calcularMedia(long[] valores) {
    long soma = 0;
    for (long valor : valores) {
      soma += valor;
    }
    return (double) soma / valores.length;
  }

  private static long calcularMinimo(long[] valores) {
    long min = Long.MAX_VALUE;
    for (long valor : valores) {
      if (valor < min)
        min = valor;
    }
    return min;
  }

  private static long calcularMaximo(long[] valores) {
    long max = Long.MIN_VALUE;
    for (long valor : valores) {
      if (valor > max)
        max = valor;
    }
    return max;
  }

  private static double calcularDesvioPadrao(long[] valores, double media) {
    double somaQuadrados = 0;
    for (long valor : valores) {
      double diferenca = valor - media;
      somaQuadrados += diferenca * diferenca;
    }
    return Math.sqrt(somaQuadrados / valores.length);
  }
}
