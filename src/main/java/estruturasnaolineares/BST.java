package estruturasnaolineares;

/**
 * Árvore Binária de Busca genérica.
 * T deve ser Comparable<T> OU um Comparator<T> deve ser fornecido.
 */
public class BST<T extends Comparable<T>> extends ArvoreBinaria<T> {

  // ── Busca ────────────────────────────────────────────────────────────
  // Melhor: O(1) – raiz é o alvo
  // Médio: O(log n) – árvore balanceada
  // Pior: O(n) – árvore degenerada (lista)
  public boolean contem(T valor) {
    return buscarNo(raiz, valor) != null;
  }

  private No<T> buscarNo(No<T> no, T valor) {
    if (no == null)
      return null;
    int cmp = valor.compareTo(no.dado);
    if (cmp == 0)
      return no;
    if (cmp < 0)
      return buscarNo(no.esquerdo, valor);
    return buscarNo(no.direito, valor);
  }

  // ── Inserção ─────────────────────────────────────────────────────────
  // Melhor/Médio: O(log n) | Pior: O(n)
  public void inserir(T valor) {
    raiz = inserirRecursivo(raiz, valor);
  }

  private No<T> inserirRecursivo(No<T> no, T valor) {
    if (no == null)
      return new No<>(valor);
    int cmp = valor.compareTo(no.dado);
    if (cmp < 0)
      no.esquerdo = inserirRecursivo(no.esquerdo, valor);
    else if (cmp > 0)
      no.direito = inserirRecursivo(no.direito, valor);
    // cmp == 0: duplicata ignorada
    return no;
  }

  // ── Mínimo e Máximo ──────────────────────────────────────────────────
  // O(h) onde h é a altura da árvore
  public T minimo() {
    if (raiz == null)
      throw new IllegalStateException("Árvore vazia");
    return minimoNo(raiz).dado;
  }

  private No<T> minimoNo(No<T> no) {
    while (no.esquerdo != null)
      no = no.esquerdo;
    return no;
  }

  public T maximo() {
    if (raiz == null)
      throw new IllegalStateException("Árvore vazia");
    No<T> no = raiz;
    while (no.direito != null)
      no = no.direito;
    return no.dado;
  }

  // ── Remoção (3 casos) ────────────────────────────────────────────────
  // Melhor/Médio: O(log n) | Pior: O(n)
  public void remover(T valor) {
    raiz = removerRecursivo(raiz, valor);
  }

  private No<T> removerRecursivo(No<T> no, T valor) {
    if (no == null)
      return null; // valor não encontrado

    int cmp = valor.compareTo(no.dado);
    if (cmp < 0) {
      no.esquerdo = removerRecursivo(no.esquerdo, valor);
    } else if (cmp > 0) {
      no.direito = removerRecursivo(no.direito, valor);
    } else {
      // Caso 1: sem filhos
      if (no.esquerdo == null && no.direito == null)
        return null;
      // Caso 2: um filho
      if (no.esquerdo == null)
        return no.direito;
      if (no.direito == null)
        return no.esquerdo;
      // Caso 3: dois filhos – substituir pelo sucessor in-order
      T sucessor = minimoNo(no.direito).dado;
      no.dado = sucessor;
      no.direito = removerRecursivo(no.direito, sucessor);
    }
    return no;
  }
}
