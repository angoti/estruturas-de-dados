package recursao;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

  private static Map<Integer, Long> memo = new HashMap<>();

  // Complexidade: O(2^n) - EXPONENCIAL!
  public static int fibonacciIngenuo(int n) {
    if (n == 0)
      return 0;
    if (n == 1)
      return 1;
    return fibonacciIngenuo(n - 1) + fibonacciIngenuo(n - 2);
  }

  // Complexidade: O(n) tempo, O(n) espaço
  public static long fibonacciMemoizada(int n) {
    // Casos base
    if (n == 0)
      return 0;
    if (n == 1)
      return 1;

    // Verifica se já foi calculado
    if (memo.containsKey(n)) {
      return memo.get(n);
    }

    // Calcula e armazena
    long resultado = fibonacciMemoizada(n - 1) + fibonacciMemoizada(n - 2);
    memo.put(n, resultado);

    return resultado;
  }

  public static void main(String[] args) {

    long inicio = System.nanoTime();
    System.out.println("Fibonacci Memoizada(35): " + fibonacciMemoizada(35));
    long fim = System.nanoTime();
    System.out.println("Tempo: " + (fim - inicio) / 1_000_000 + "ms");
    
    memo.clear();
    System.out.println("memo.size(): " + memo.size());
    inicio = System.nanoTime();
    System.out.println("inicio: " + inicio);
    System.out.println("Fibonacci Memoizada(40): " + fibonacciMemoizada(40));
    fim = System.nanoTime();
    System.out.println("fim: " + fim);
    System.out.println("Tempo: " + (fim - inicio) / 1_000_000 + "ms");
    
    inicio = System.nanoTime();
    System.out.println("Fibonacci Ingenuo(35): " + fibonacciIngenuo(35));
    fim = System.nanoTime();
    System.out.println("Tempo: " + (fim - inicio) / 1_000_000 + "ms");
    
    inicio = System.nanoTime();
    System.out.println("Fibonacci Ingenuo(40): " + fibonacciIngenuo(40));
    fim = System.nanoTime();
    System.out.println("Tempo: " + (fim - inicio) / 1_000_000 + "ms");

  }

}
