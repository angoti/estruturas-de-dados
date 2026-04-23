package estruturas;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class FilaCircular<T> implements Fila<T> {

  private static final int CAP_INICIAL = 16;
  private Object[] elementos;
  private int inicio; // índice do próximo elemento a sair
  private int fim; // índice do próximo slot livre para entrar
  private int quantidade;

  public FilaCircular() {
    elementos = new Object[CAP_INICIAL];
    inicio = 0;
    fim = 0;
    quantidade = 0;
  }

  // ── enqueue: insere em fim, avança fim circularmente ─────────────
  @Override // Θ(1) amortizado
  public void enqueue(T elemento) {
    if (quantidade == elementos.length)
      redimensionar();
    elementos[fim] = elemento;
    fim = (fim + 1) % elementos.length; // wrap-around
    quantidade++;
  }

  // ── dequeue: retorna inicio, avança inicio circularmente ──────────
  @Override // Θ(1)
  @SuppressWarnings("unchecked")
  public T dequeue() {
    if (isEmpty())
      throw new NoSuchElementException("Fila vazia");
    T removido = (T) elementos[inicio];
    elementos[inicio] = null; // libera referência
    inicio = (inicio + 1) % elementos.length;
    quantidade--;
    return removido;
  }

  @Override // Θ(1)
  @SuppressWarnings("unchecked")
  public T peek() {
    if (isEmpty())
      throw new NoSuchElementException("Fila vazia");
    return (T) elementos[inicio];
  }

  @Override
  public boolean isEmpty() {
    return quantidade == 0;
  }

  @Override
  public int size() {
    return quantidade;
  }

  /**
   * Redimensiona para o dobro. Realinha os elementos a partir do índice 0.
   * O realinhamento é necessário porque os elementos podem estar fragmentados
   * em duas partes do array circular (ex.: [40,50,_,_,10,20,30] com inicio=4).
   */
  private void redimensionar() {
    Object[] novo = new Object[elementos.length * 2];
    for (int i = 0; i < quantidade; i++) {
      // (inicio + i) % length mapeia a posição lógica para a física
      novo[i] = elementos[(inicio + i) % elementos.length];
    }
    inicio = 0;
    fim = quantidade;
    elementos = novo;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Fila[frente→");
    for (int i = 0; i < quantidade; i++) {
      if (i > 0)
        sb.append(", ");
      sb.append(elementos[(inicio + i) % elementos.length]);
    }
    return sb.append("]").toString();
  }
}

4.5

Fila com
Lista Encadeada—
FilaLista<T> Com
a lista encadeada,
a implementação
é direta:
enqueue insere no
final usando a

referência cauda (Θ(1)) e dequeue remove do início usando a referência cabeca (Θ(1)).

package estruturas;

import java.util.NoSuchElementException;

public class FilaLista<T> implements Fila<T> {

    private Node<T> cabeca;   // primeiro a sair (frente da fila)
    private Node<T> cauda;    // último a entrar (fundo da fila)
    private int quantidade;

    public FilaLista() { cabeca = null; cauda = null; quantidade = 0; }

    // ── enqueue: novo nó vai para após a cauda atual ─────────────────
    @Override   // Θ(1)
    public void enqueue(T elemento) {
        Node<T> novo = new Node<>(elemento);
        if (isEmpty()) {
            cabeca = novo;
        } else {
            cauda.proximo = novo;   // encadeia o novo nó ao final
        }
        cauda = novo;
        quantidade++;
    }

    // ── dequeue: remove cabeca e avança o ponteiro ───────────────────
    @Override   // Θ(1)
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Fila vazia");
        T removido = cabeca.dado;
        cabeca = cabeca.proximo;
        if (cabeca == null) cauda = null;   // lista ficou vazia
        quantidade--;
        return removido;
    }

    @Override public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Fila vazia");
        return cabeca.dado;
    }

    @Override public boolean isEmpty() { return quantidade == 0; }
    @Override public int size()        { return quantidade; }
}
