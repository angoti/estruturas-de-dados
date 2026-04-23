package estruturas;

/**
 * Nó para lista simplesmente encadeada.
 * Cada nó armazena um dado e uma referência para o próximo.
 */
public class Node<T> {
  T dado;
  Node<T> proximo;

  public Node(T dado) {
    this.dado = dado;
    this.proximo = null;
  }

  public Node(T dado, Node<T> proximo) {
    this.dado = dado;
    this.proximo = proximo;
  }
}
