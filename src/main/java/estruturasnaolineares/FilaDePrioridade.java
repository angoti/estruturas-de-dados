package estruturasnaolineares;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Fila de Prioridade genérica baseada em min-heap.
 * O elemento de MENOR prioridade (comparator) é o topo.
 */
public class FilaDePrioridade<T> {
    private static final int CAPACIDADE_INICIAL = 16;
    private Object[] heap;
    private int tamanho;
    private final Comparator<T> comparador;

    public FilaDePrioridade(Comparator<T> comparador) {
        this.heap = new Object[CAPACIDADE_INICIAL];
        this.tamanho = 0;
        this.comparador = comparador;
    }

    // Construtor para tipos naturalmente comparáveis
    @SuppressWarnings("unchecked")
    public FilaDePrioridade() {
        this((a, b) -> ((Comparable<T>) a).compareTo(b));
    }

    // ── Inserção ─────────────────────────────────────────────────────────
    // Adiciona ao final e sobe. Complexidade: O(log n)
    public void inserir(T elemento) {
        garantirCapacidade();
        heap[tamanho] = elemento;
        siftUp(tamanho);
        tamanho++;
    }

    private void siftUp(int i) {
        while (i > 0) {
            int pai = (i - 1) / 2;
            if (comparar(i, pai) < 0) {
                trocar(i, pai);
                i = pai;
            } else break;
        }
    }

    // ── Acesso ao topo ────────────────────────────────────────────────────
    // O(1) – a raiz é sempre o mínimo
    @SuppressWarnings("unchecked")
    public T topo() {
        if (tamanho == 0) throw new NoSuchElementException("Heap vazio");
        return (T) heap[0];
    }

    // ── Remoção do topo ───────────────────────────────────────────────────
    // Remove raiz, coloca o último no lugar, desce. O(log n)
    @SuppressWarnings("unchecked")
    public T removerTopo() {
        if (tamanho == 0) throw new NoSuchElementException("Heap vazio");
        T topo = (T) heap[0];
        heap[0] = heap[--tamanho];
        heap[tamanho] = null;     // permite GC
        if (tamanho > 0) siftDown(0);
        return topo;
    }

    private void siftDown(int i) {
        while (true) {
            int menor = i;
            int esq = 2 * i + 1;
            int dir = 2 * i + 2;
            if (esq < tamanho && comparar(esq, menor) < 0) menor = esq;
            if (dir < tamanho && comparar(dir, menor) < 0) menor = dir;
            if (menor == i) break;
            trocar(i, menor);
            i = menor;
        }
    }

    // ── Construção em O(n) ────────────────────────────────────────────────
    public static <T> FilaDePrioridade<T> buildHeap(T[] array,
                                                    Comparator<T> cmp) {
        FilaDePrioridade<T> fila = new FilaDePrioridade<>(cmp);
        fila.heap = Arrays.copyOf(array, array.length * 2);
        fila.tamanho = array.length;
        // Aplicar siftDown de (n/2 - 1) até 0
        for (int i = fila.tamanho / 2 - 1; i >= 0; i--) {
            fila.siftDown(i);
        }
        return fila;
    }

    // ── Utilitários ──────────────────────────────────────────────────────
    public int tamanho() { return tamanho; }
    public boolean estaVazio() { return tamanho == 0; }

    @SuppressWarnings("unchecked")
    private int comparar(int i, int j) {
        return comparador.compare((T) heap[i], (T) heap[j]);
    }

    private void trocar(int i, int j) {
        Object tmp = heap[i]; heap[i] = heap[j]; heap[j] = tmp;
    }

    private void garantirCapacidade() {
        if (tamanho == heap.length) {
            heap = Arrays.copyOf(heap, tamanho * 2);
        }
    }

    public static void main(String[] args) {
        // Exemplo de uso
        FilaDePrioridade<Integer> fila = new FilaDePrioridade<>();
        fila.inserir(5);
        fila.inserir(3);
        fila.inserir(8);
        System.out.println(fila.topo()); // 3
        System.out.println(fila.removerTopo()); // 3
        System.out.println(fila.topo()); // 5
    }
}