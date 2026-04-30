package estruturasnaolineares;

public class AVL<T extends Comparable<T>> extends BST<T> {

  int rotacoes = 0; // contador de rotações para análise

  // ── Nó AVL (estende No<T> com altura) ───────────────────────────────
  private static class NoAVL<T> extends No<T> {
    int altura;

    NoAVL(T dado) {
      super(dado);
      altura = 0;
    }
  }

  private int altura(No<T> no) {
    return no == null ? -1 : ((NoAVL<T>) no).altura;
  }

  private void atualizarAltura(NoAVL<T> no) {
    no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));
  }

  private int fb(No<T> no) {
    return no == null ? 0 : altura(no.esquerdo) - altura(no.direito);
  }

  // ── Rotação Simples à Direita (caso LL) ─────────────────────────────
  private No<T> rotacionarDireita(NoAVL<T> z) {
    rotacoes++;
    NoAVL<T> y = (NoAVL<T>) z.esquerdo;
    No<T> T3 = y.direito;

    y.direito = z; // y sobe
    z.esquerdo = T3; // T3 passa para z

    atualizarAltura(z); // IMPORTANTE: atualizar z antes de y
    atualizarAltura(y);
    return y;
  }

  // ── Rotação Simples à Esquerda (caso RR) ────────────────────────────
  private No<T> rotacionarEsquerda(NoAVL<T> z) {
    rotacoes++;
    NoAVL<T> y = (NoAVL<T>) z.direito;
    No<T> T2 = y.esquerdo;

    y.esquerdo = z;
    z.direito = T2;

    atualizarAltura(z);
    atualizarAltura(y);
    return y;
  }

  // ── Rebalancear após inserção/remoção ────────────────────────────────
  private No<T> rebalancear(NoAVL<T> no) {
    atualizarAltura(no);
    int fatorBalanco = fb(no);

    // Caso LL
    if (fatorBalanco > 1 && fb(no.esquerdo) >= 0) {
      return rotacionarDireita(no);
    }

    // Caso LR
    if (fatorBalanco > 1 && fb(no.esquerdo) < 0) {
      no.esquerdo = rotacionarEsquerda((NoAVL<T>) no.esquerdo);
      return rotacionarDireita(no);
    }

    // Caso RR
    if (fatorBalanco < -1 && fb(no.direito) <= 0)
      return rotacionarEsquerda(no);

    // Caso RL
    if (fatorBalanco < -1 && fb(no.direito) > 0) {
      no.direito = rotacionarDireita((NoAVL<T>) no.direito);
      return rotacionarEsquerda(no);
    }

    return no; // já balanceado
  }

  // ── Inserção AVL ─────────────────────────────────────────────────────
  @Override
  public void inserir(T valor) {
    raiz = inserirAVL(raiz, valor);
  }

  private No<T> inserirAVL(No<T> no, T valor) {
    // 1. Inserção BST padrão
    if (no == null)
      return new NoAVL<>(valor);
    int cmp = valor.compareTo(no.dado);
    if (cmp < 0)
      no.esquerdo = inserirAVL(no.esquerdo, valor);
    else if (cmp > 0)
      no.direito = inserirAVL(no.direito, valor);
    else
      return no; // duplicata

    // 2. Rebalancear na volta da recursão
    return rebalancear((NoAVL<T>) no);
  }

  // ── Remoção AVL ──────────────────────────────────────────────────────
  @Override
  public void remover(T valor) {
    raiz = removerAVL(raiz, valor);
  }

  private No<T> removerAVL(No<T> no, T valor) {
    if (no == null)
      return null;
    int cmp = valor.compareTo(no.dado);
    if (cmp < 0) {
      no.esquerdo = removerAVL(no.esquerdo, valor);
    } else if (cmp > 0) {
      no.direito = removerAVL(no.direito, valor);
    } else {
      // Remoção (igual à BST)
      if (no.esquerdo == null)
        return no.direito;
      if (no.direito == null)
        return no.esquerdo;
      // Dois filhos: substituir pelo sucessor
      No<T> minDir = no.direito;
      while (minDir.esquerdo != null)
        minDir = minDir.esquerdo;
      no.dado = minDir.dado;
      no.direito = removerAVL(no.direito, minDir.dado);
    }
    return rebalancear((NoAVL<T>) no);
  }

  public int alturaAVL() {
    return altura(raiz);
  }

  public int contarRotacoes() {
    return rotacoes;
  }
}
