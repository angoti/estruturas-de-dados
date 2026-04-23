package estruturas;

import java.util.NoSuchElementException;

/**
 * TAD Fila — coleção FIFO (First In, First Out).
 * Toda implementação deve garantir enqueue/dequeue/peek em O(1).
 *
 * @param <T> tipo dos elementos
 */
public interface Fila<T> {

  /** Insere elemento no final da fila. O(1). */
  void enqueue(T elemento);

  /** Remove e retorna o elemento do início. O(1). */
  T dequeue();

  /** Retorna o início sem remover. O(1). */
  T peek();

  boolean isEmpty();

  int size();
}
