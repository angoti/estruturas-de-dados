package estruturas;

/**
 * Verifica balanceamento de delimitadores em uma expressão Java/JSON.
 * Algoritmo: O(n) temporal, O(n) espacial (pilha no pior caso).
 */
public class VerificadorBalanceamento {

  public static boolean estaBalanceado(String expressao) {
    Pilha<Character> pilha = new PilhaArray<>();

    for (char c : expressao.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        pilha.push(c); // abre: empilha
      } else if (c == ')' || c == ']' || c == '}') {
        // fecha: pilha vazia → faltou abrir
        if (pilha.isEmpty())
          return false;
        if (!corresponde(pilha.pop(), c))
          return false;
      }
      // outros caracteres (letras, operadores) são ignorados
    }
    // se a pilha não ficou vazia, há delimitadores abertos sem fechar
    return pilha.isEmpty();
  }

  public static boolean estaBalanceadoJCF(String expressao) {
    java.util.Deque<Character> pilha = new java.util.ArrayDeque<>();
    for (char c : expressao.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        pilha.push(c);
      } else if (c == ')' || c == ']' || c == '}') {
        if (pilha.isEmpty() || !corresponde(pilha.pop(), c))
          return false;
      }
    }
    return pilha.isEmpty();
  }

  private static boolean corresponde(char aberto, char fechado) {
    return (aberto == '(' && fechado == ')')
        || (aberto == '[' && fechado == ']')
        || (aberto == '{' && fechado == '}');
  }

  public static void main(String[] args) {
    String expressao = "{\"key\": [1, 2, 3]}";
    System.out.println(expressao + " está balanceado? " + estaBalanceadoJCF(expressao)); // true
    expressao = "({[]})";
    System.out.println(expressao + " está balanceado? " + estaBalanceadoJCF(expressao)); // true
    expressao = "{[}]";
    System.out.println(expressao + " está balanceado? " + estaBalanceadoJCF(expressao)); // false
    expressao = "(((";
    System.out.println(expressao + " está balanceado? " + estaBalanceadoJCF(expressao)); // false
  }
}
