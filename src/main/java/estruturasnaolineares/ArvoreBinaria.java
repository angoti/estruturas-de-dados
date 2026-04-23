package estruturasnaolineares;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ArvoreBinaria<T> {
  public No<T> raiz;

  public ArvoreBinaria() {
    this.raiz = null;
  }

  // ── Percurso In-Order (esquerdo → raiz → direito) ──────────────────
  // Em uma BST, produz os elementos em ordem crescente.
  // Complexidade: Θ(n) tempo, O(h) espaço (pilha de chamadas)
  public List<T> inOrder() {
    List<T> resultado = new ArrayList<>();
    inOrderRecursivo(raiz, resultado);
    return resultado;
  }

  private void inOrderRecursivo(No<T> no, List<T> resultado) {
    if (no == null)
      return;
    inOrderRecursivo(no.esquerdo, resultado);
    resultado.add(no.dado);
    inOrderRecursivo(no.direito, resultado);
  }

  // ── Percurso Pre-Order (raiz → esquerdo → direito) ─────────────────
  // Útil para copiar a árvore ou serializar sua estrutura.
  // Complexidade: Θ(n) tempo, O(h) espaço
  public List<T> preOrder() {
    List<T> resultado = new ArrayList<>();
    preOrderRecursivo(raiz, resultado);
    return resultado;
  }

  private void preOrderRecursivo(No<T> no, List<T> resultado) {
    if (no == null)
      return;
    resultado.add(no.dado);
    preOrderRecursivo(no.esquerdo, resultado);
    preOrderRecursivo(no.direito, resultado);
  }

  // ── Percurso Post-Order (esquerdo → direito → raiz) ────────────────
  // Útil para deletar a árvore ou avaliar expressões.
  // Complexidade: Θ(n) tempo, O(h) espaço
  public List<T> postOrder() {
    List<T> resultado = new ArrayList<>();
    postOrderRecursivo(raiz, resultado);
    return resultado;
  }

  private void postOrderRecursivo(No<T> no, List<T> resultado) {
    if (no == null)
      return;
    postOrderRecursivo(no.esquerdo, resultado);
    postOrderRecursivo(no.direito, resultado);
    resultado.add(no.dado);
  }

  // ── Percurso Level-Order (BFS) ──────────────────────────────────────
  // Visita nós nível por nível, da esquerda para a direita.
  // Usa uma fila (Queue) em vez de recursão.
  // Complexidade: Θ(n) tempo, O(w) espaço (w = largura máxima da árvore)
  public List<T> levelOrder() {
    List<T> resultado = new ArrayList<>();
    if (raiz == null)
      return resultado;
    Queue<No<T>> fila = new ArrayDeque<>();
    fila.offer(raiz);
    while (!fila.isEmpty()) {
      No<T> atual = fila.poll();
      resultado.add(atual.dado);
      if (atual.esquerdo != null)
        fila.offer(atual.esquerdo);
      if (atual.direito != null)
        fila.offer(atual.direito);
    }
    return resultado;
  }

  // ── Utilitários ─────────────────────────────────────────────────────
  public int altura() {
    return alturaRecursivo(raiz);
  }

  private int alturaRecursivo(No<T> no) {
    if (no == null)
      return -1;
    return 1 + Math.max(alturaRecursivo(no.esquerdo),
        alturaRecursivo(no.direito));
  }

  public int tamanho() {
    return tamanhoRecursivo(raiz);
  }

  private int tamanhoRecursivo(No<T> no) {
    if (no == null)
      return 0;
    return 1 + tamanhoRecursivo(no.esquerdo)
        + tamanhoRecursivo(no.direito);
  }

  public boolean estaVazia() {
    return raiz == null;
  }

  public boolean ehArvoreCompleta() {
    if (raiz == null)
      return true;
    boolean achouFalha = false;
    Queue<No<T>> fila = new ArrayDeque<>();
    fila.offer(raiz);
    while (!fila.isEmpty()) {
      No<T> atual = fila.poll();
      if (atual.esquerdo != null) {
        if (achouFalha)
          return false;
        fila.offer(atual.esquerdo);
      } else {
        achouFalha = true; // Encontrou um nó sem filho esquerdo
      }
      if (atual.direito != null) {
        if (achouFalha)
          return false; // Encontrou um nó com filho direito após uma falha
        fila.offer(atual.direito);
      } else {
        achouFalha = true;
      }
    }
    return true;
  }

  public int contarFolhas() {
    if (raiz == null)
      return 0;
    int folhas = 0;
    Queue<No<T>> fila = new ArrayDeque<>();
    fila.offer(raiz);
    while (!fila.isEmpty()) {
      No<T> atual = fila.poll();
      if (atual.esquerdo == null && atual.direito == null) {
        folhas++;
      }
      if (atual.esquerdo != null) {
        fila.offer(atual.esquerdo);
      }
      if (atual.direito != null) {
        fila.offer(atual.direito);
      }
    }
    return folhas;
  }

  public int contarFolhasComRecursao() {
    return contarFolhasRec(raiz);
  }

  private int contarFolhasRec(No<T> no) {
    if (no == null)
      return 0; // árvore vazia
    if (no.esquerdo == null && no.direito == null)
      return 1; // é folha
    return contarFolhasRec(no.esquerdo)
        + contarFolhasRec(no.direito);
  }

  /**
   * Verifica se as subárvores enraizadas em 'a' e 'b' são espelhos.
   * Complexidade: Θ(n) tempo, O(h) espaço — n = total de nós, h = altura
   */
  public boolean saoEspelhos(No<T> a, No<T> b) {

    // caso 1: ambos nulos → espelhos vazios ✓
    if (a == null && b == null)
      return true;

    // caso 2: apenas um é nulo → assimétrico ✗
    if (a == null || b == null)
      return false;

    // caso 3: valores diferentes → não são espelhos ✗
    if (!a.dado.equals(b.dado))
      return false;

    // caso 4: verifica cruzado — esq(a) com dir(b) e dir(a) com esq(b)
    return saoEspelhos(a.esquerdo, b.direito)
        && saoEspelhos(a.direito, b.esquerdo);
  }

  /**
   * Verifica se a própria árvore é simétrica (espelho de si mesma).
   * Uma árvore é simétrica se suas subárvores esquerda e direita são espelhos.
   */
  public boolean ehSimetrica() {
    if (raiz == null)
      return true;
    return saoEspelhos(raiz.esquerdo, raiz.direito);
  }

  // List<List<T>> niveisComoListas()
  // Adicione à classe ArvoreBinaria<T>
  public List<List<T>> niveisComoListas() {
    List<List<T>> resultado = new ArrayList<>();
    if (raiz == null)
      return resultado;

    Queue<No<T>> fila = new ArrayDeque<>();
    fila.offer(raiz);

    while (!fila.isEmpty()) {
      int tamanhoNivel = fila.size(); // quantos nós estão neste nível
      List<T> nivel = new ArrayList<>();

      for (int i = 0; i < tamanhoNivel; i++) {
        No<T> atual = fila.poll();
        nivel.add(atual.dado);

        if (atual.esquerdo != null)
          fila.offer(atual.esquerdo);
        if (atual.direito != null)
          fila.offer(atual.direito);
      }

      resultado.add(nivel);
    }

    return resultado;
  }

  // ------------------------------------------------
  // Debug Visualizer
  // --------------------------------------------
  public String toDot() {
    StringBuilder sb = new StringBuilder("digraph BST {\n");
    sb.append("  node [shape=circle];\n");
    toDotRecursivo(raiz, sb);
    sb.append("}");
    return sb.toString();
  }

  private void toDotRecursivo(No<T> no, StringBuilder sb) {
    if (no == null)
      return;
    if (no.esquerdo != null) {
      sb.append("  ").append(no.dado)
          .append(" -> ").append(no.esquerdo.dado).append(";\n");
      toDotRecursivo(no.esquerdo, sb);
    }
    if (no.direito != null) {
      sb.append("  ").append(no.dado)
          .append(" -> ").append(no.direito.dado).append(";\n");
      toDotRecursivo(no.direito, sb);
    }
  }
}
