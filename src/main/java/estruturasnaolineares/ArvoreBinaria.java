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

}
