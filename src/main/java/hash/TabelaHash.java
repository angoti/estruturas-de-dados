package hash;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Tabela hash genérica com encadeamento separado.
 * Redimensiona quando α > LOAD_FACTOR_THRESHOLD.
 *
 * @param <K> tipo da chave — deve implementar hashCode() e equals()
 * @param <V> tipo do valor
 */
public class TabelaHash<K, V> implements Map<K, V> {

  // ── Constantes ────────────────────────────────────────────────────────────
  private static final int CAPACIDADE_INICIAL = 16;
  private static final double LOAD_FACTOR_THRESHOLD = 0.75;
  private static final int FATOR_CRESCIMENTO = 2;

  // ── Estado interno ────────────────────────────────────────────────────────
  /** Array de buckets; cada bucket é uma lista de entradas. */
  @SuppressWarnings("unchecked")
  private LinkedList<Entry<K, V>>[] buckets;
  private int size; // número de pares armazenados
  private int capacidade; // número de buckets (sempre potência de 2)

  // ── Entry: par (chave, valor) ─────────────────────────────────────────────
  private static class Entry<K, V> {
    final K key;
    V value;

    Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  // ── Construtor ────────────────────────────────────────────────────────────
  @SuppressWarnings("unchecked")
  public TabelaHash() {
    capacidade = CAPACIDADE_INICIAL;
    buckets = new LinkedList[capacidade];
    size = 0;
  }

  // ── Função de indexação ───────────────────────────────────────────────────
  /**
   * Converte a chave em um índice de bucket.
   * Aplica um espalhamento de bits (Wang hash) antes do AND para
   * reduzir colisões quando a capacidade é potência de 2.
   */
  private int indexar(K key) {
    int h = key.hashCode();
    h = h ^ (h >>> 16); // mistura bits altos e baixos
    return h & (capacidade - 1); // AND funciona porque capacidade é 2^k
  }

  // ── put ───────────────────────────────────────────────────────────────────
  @Override
  public void put(K key, V value) {
    Objects.requireNonNull(key, "Chave não pode ser nula");

    // Redimensiona antes de inserir se necessário
    if ((double) (size + 1) / capacidade > LOAD_FACTOR_THRESHOLD) {
      redimensionar();
    }

    int idx = indexar(key);
    if (buckets[idx] == null) {
      buckets[idx] = new LinkedList<>();
    }

    // Atualiza valor se chave já existe
    for (Entry<K, V> e : buckets[idx]) {
      if (Objects.equals(e.key, key)) {
        e.value = value;
        return; // atualização: size não muda
      }
    }

    // Chave nova: insere no início da lista (O(1))
    buckets[idx].addFirst(new Entry<>(key, value));
    size++;
  }

  // ── get ───────────────────────────────────────────────────────────────────
  @Override
  public V get(K key) {
    Objects.requireNonNull(key, "Chave não pode ser nula");
    int idx = indexar(key);
    if (buckets[idx] == null)
      return null;

    for (Entry<K, V> e : buckets[idx]) {
      if (Objects.equals(e.key, key)) {
        return e.value;
      }
    }
    return null; // não encontrado
  }

  // ── remove ────────────────────────────────────────────────────────────────
  @Override
  public void remove(K key) {
    Objects.requireNonNull(key, "Chave não pode ser nula");
    int idx = indexar(key);
    if (buckets[idx] == null)
      return;

    buckets[idx].removeIf(e -> Objects.equals(e.key, key));
    size--;
  }

  // ── containsKey ───────────────────────────────────────────────────────────
  @Override
  public boolean containsKey(K key) {
    return get(key) != null;
  }

  // ── size / isEmpty ────────────────────────────────────────────────────────
  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  // ── Redimensionamento ─────────────────────────────────────────────────────
  @SuppressWarnings("unchecked")
  private void redimensionar() {
    int novaCapacidade = capacidade * FATOR_CRESCIMENTO;
    LinkedList<Entry<K, V>>[] novosBuckets = new LinkedList[novaCapacidade];

    // Reinsere todos os pares com nova função de indexação
    for (LinkedList<Entry<K, V>> bucket : buckets) {
      if (bucket == null)
        continue;
      for (Entry<K, V> e : bucket) {
        // Recalcula índice com nova capacidade
        int h = e.key.hashCode();
        h = h ^ (h >>> 16);
        int novoIdx = h & (novaCapacidade - 1);

        if (novosBuckets[novoIdx] == null) {
          novosBuckets[novoIdx] = new LinkedList<>();
        }
        novosBuckets[novoIdx].addFirst(e);
      }
    }

    buckets = novosBuckets;
    capacidade = novaCapacidade;
  }

  // ── fatorDeCarga (diagnóstico) ────────────────────────────────────────────
  public double fatorDeCarga() {
    return (double) size / capacidade;
  }
}
