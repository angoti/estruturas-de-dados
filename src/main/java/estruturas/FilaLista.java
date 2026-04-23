package estruturas;

import java.util.NoSuchElementException;

public class FilaLista<T> implements Fila<T> {

  private Node<T> cabeca; // primeiro a sair (frente da fila)
  private Node<T> cauda; // último a entrar (fundo da fila)
  private int quantidade;

  public FilaLista() {
    cabeca = null;
    cauda = null;
    quantidade = 0;
  }

  // ── enqueue: novo nó vai para após a cauda atual ─────────────────
  @Override // Θ(1)
  public void enqueue(T elemento) {
    Node<T> novo = new Node<>(elemento);
    if (isEmpty()) {
      cabeca = novo;
    } else {
      cauda.proximo = novo; // encadeia o novo nó ao final
    }
    cauda = novo;
    quantidade++;
  }

  // ── dequeue: remove cabeca e avança o ponteiro ───────────────────
  @Override // Θ(1)
  public T dequeue() {
    if (isEmpty())
      throw new NoSuchElementException("Fila vazia");
    T removido = cabeca.dado;
    cabeca = cabeca.proximo;
    if (cabeca == null)
      cauda = null; // lista ficou vazia
    quantidade--;
    return removido;
  }

  @Override
  public T peek() {
    if (isEmpty())
      throw new NoSuchElementException("Fila vazia");
    return cabeca.dado;
  }

  @Override
  public boolean isEmpty() {
    return quantidade == 0;
  }

  @Override
  public int size() {
    return quantidade;
  }
}
