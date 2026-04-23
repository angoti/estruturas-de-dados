package recursao;

public class Fatorial {

  public static int fatorial(int n) {
    // Validação
    if (n < 0) {
      throw new IllegalArgumentException("n >= 0");
    }
    // CASO BASE: fatorial de 0 ou 1 é 1
    if (n == 0 || n == 1) {
      System.out.println("Caso base: " + n + "! = 1");
      return 1;
    }
    // CASO RECURSIVO: n! = n × (n-1)!
    System.out.println("Calculando " + n + "!");
    int resultado = n * fatorial(n - 1);
    System.out.println(n + "! = " + resultado);
    return resultado;
  }

  public static int fatorialErrado(int n) {
    System.out.println("Calculando " + n + "!");
    return n * fatorialErrado(n - 1); // Recursão infinita!
  }

  public static int fatorialIterativo(int n) {
    int acumulador = 1;
    for (int i = n; i > 1; i--) {
      acumulador *= i;
    }
    return acumulador;
  }

  public static void main(String[] args) {
    System.out.println("Resultado final: " + fatorialIterativo(3));
  }
}
