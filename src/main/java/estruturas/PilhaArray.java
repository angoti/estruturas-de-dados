package estruturas;

import java.util.Arrays;
import java.util.EmptyStackException;

public class PilhaArray<T> implements Pilha<T> {

  private static final int CAP_INICIAL = 16;
  private Object[] elementos; // array interno — não pode ser T[] por type erasure
  private int topo; // índice do próximo slot LIVRE (não do último elemento)

  public PilhaArray() {
    elementos = new Object[CAP_INICIAL];
    topo = 0;
  }

  // ── push: insere no índice topo e incrementa o ponteiro ──────────
  @Override // Θ(1) amortizado
  public void push(T elemento) {
    garantirCapacidade();
    elementos[topo] = elemento;
    topo++;
  }

  // ── pop: decrementa topo, retorna e anula a referência ───────────
  @Override // Θ(1)
  @SuppressWarnings("unchecked")
  public T pop() {
    if (isEmpty())
      throw new EmptyStackException();
    topo--;
    T removido = (T) elementos[topo];
    elementos[topo] = null; // libera referência — evita memory leak
    return removido;
  }

  // ── peek: lê sem alterar o ponteiro ─────────────────────────────
  @Override // Θ(1)
  @SuppressWarnings("unchecked")
  public T peek() {
    if (isEmpty())
      throw new EmptyStackException();
    return (T) elementos[topo - 1];
  }

  @Override
  public boolean isEmpty() {
    return topo == 0;
  }

  @Override
  public int size() {
    return topo;
  }

  // ── redimensionamento: dobra o array quando esgota a capacidade ──
  private void garantirCapacidade() {
    if (topo == elementos.length) {
      // Arrays.copyOf aloca novo array e copia — O(n), mas amortizado
      elementos = Arrays.copyOf(elementos, elementos.length * 2);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Pilha[topo→");
    for (int i = topo - 1; i >= 0; i--) {
      if (i < topo - 1)
        sb.append(", ");
      sb.append(elementos[i]);
    }
    return sb.append("]").toString();
  }
}
