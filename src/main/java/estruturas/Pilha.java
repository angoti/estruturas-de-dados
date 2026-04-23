package estruturas;

import java.util.EmptyStackException;

/**
 * TAD Pilha — coleção LIFO (Last In, First Out).
 * Toda implementação deve garantir push/pop/peek em O(1).
 *
 * @param <T> tipo dos elementos
 */
public interface Pilha<T> {

  /** Empilha elemento no topo. O(1) amortizado. */
  void push(T elemento);

  /** Remove e retorna o topo. O(1). Lança EmptyStackException se vazia. */
  T pop();

  /** Retorna o topo sem remover. O(1). */
  T peek();

  /** true se não houver elementos. */
  boolean isEmpty();

  /** Número de elementos. */
  int size();
}
