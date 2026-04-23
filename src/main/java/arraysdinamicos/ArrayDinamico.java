package arraysdinamicos;

import java.util.Arrays;

public class ArrayDinamico<T> implements Lista<T> {

  private static final int CAPACIDADE_INICIAL = 10;
  private Object[] elementos; // array interno
  private int quantidade; // número de elementos armazenados

  public static void main(String[] args) {

    ArrayDinamico<Integer> array = new ArrayDinamico<>();
    array.adicionar(1);
    array.adicionar(2);
    array.adicionar(3);
    array.adicionar(4);
    array.adicionar(5);
    System.out.println("Antes de inverter: " + array);
    array.inverter();
    System.out.println("Depois de inverter: " + array);
  }

  public ArrayDinamico() {
    this(CAPACIDADE_INICIAL);
  }

  public ArrayDinamico(int capacidadeInicial) {
    if (capacidadeInicial < 0) {
      throw new IllegalArgumentException(
          "Capacidade não pode ser negativa: " + capacidadeInicial);
    }
    this.elementos = new Object[capacidadeInicial];
    this.quantidade = 0;
  }

  public void inverter() {
    // Implemente aqui
    // Dica: troque elementos simétricos (primeiro com último,
    // segundo com penúltimo, etc.)
    for (int i = 0; i < quantidade / 2; i++) {
      @SuppressWarnings("unchecked")
      T temp = (T) elementos[i];
      elementos[i] = elementos[quantidade - 1 - i];
      elementos[quantidade - 1 - i] = temp;
    }
    int esq = 0;
    int dir = quantidade - 1;
    while (esq < dir) {
      @SuppressWarnings("unchecked")
      T temp = (T) elementos[dir];
      elementos[dir] = elementos[esq];
      elementos[esq] = temp;
      esq++;
      dir--;
    }
  }

  // ---- Operações de adição ----

  @Override // O(1) amortizado
  public void adicionar(T elemento) {
    garantirCapacidade(quantidade + 1);
    elementos[quantidade] = elemento;
    quantidade++;
  }

  @Override // O(n) - precisa deslocar elementos
  public void inserir(int indice, T elemento) {
    verificarIndiceParaInsercao(indice);
    garantirCapacidade(quantidade + 1);
    // Desloca elementos para a direita
    // System.arraycopy() é um método nativo otimizado que pode usar instruções SIMD
    // (Single Instruction, Multiple Data) do processador para copiar múltiplos
    // elementos simultaneamente. Ele é significativamente mais rápido que um loop
    // em Java, especialmente para arrays grandes.
    System.arraycopy(elementos, indice,
        elementos, indice + 1,
        quantidade - indice);
    elementos[indice] = elemento;
    quantidade++;
  }

  // ---- Operações de remoção ----

  @Override // O(n) - precisa deslocar elementos
  public T remover(int indice) {
    verificarIndice(indice);
    @SuppressWarnings("unchecked")
    T removido = (T) elementos[indice];
    int elementosParaMover = quantidade - indice - 1;
    if (elementosParaMover > 0) {
      System.arraycopy(elementos, indice + 1,
          elementos, indice,
          elementosParaMover);
    }
    elementos[--quantidade] = null; // permite GC
    return removido;
  }

  // ---- Operações de acesso ----

  @Override // O(1)
  public T obter(int indice) {
    verificarIndice(indice);
    @SuppressWarnings("unchecked")
    T elemento = (T) elementos[indice];
    return elemento;
  }

  @Override // O(n)
  public boolean contem(T elemento) {
    return indiceDe(elemento) >= 0;
  }

  @Override // O(n)
  public int indiceDe(T elemento) {
    if (elemento == null) {
      for (int i = 0; i < quantidade; i++) {
        if (elementos[i] == null)
          return i;
      }
    } else {
      for (int i = 0; i < quantidade; i++) {
        if (elemento.equals(elementos[i]))
          return i;
      }
    }
    return -1;
  }

  // ---- Informações e utilitários ----

  @Override
  public int tamanho() {
    return quantidade;
  }

  @Override
  public boolean estaVazia() {
    return quantidade == 0;
  }

  @Override
  public void limpar() {
    for (int i = 0; i < quantidade; i++) {
      elementos[i] = null; // permite GC
    }
    quantidade = 0;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < quantidade; i++) {
      if (i > 0)
        sb.append(", ");
      sb.append(elementos[i]);
    }
    return sb.append("]").toString();
  }

  // ---- Métodos internos ----

  private void garantirCapacidade(int capacidadeMinima) {
    if (capacidadeMinima > elementos.length) {
      int novaCapacidade = elementos.length +
          (elementos.length >> 1); // cresce 50%. O operador >> é o Bit Shift. Deslocar 1 bit à direita faz uma
                                   // divisão por 2, ou seja, metade do tamanho atual. Usado para calcular o
                                   // aumento de capacidade de forma eficiente.
      if (novaCapacidade < capacidadeMinima) {
        novaCapacidade = capacidadeMinima;
      }
      elementos = Arrays.copyOf(elementos, novaCapacidade);
    }
  }

  private void verificarIndice(int indice) {
    if (indice < 0 || indice >= quantidade) {
      throw new IndexOutOfBoundsException(
          "Índice: " + indice + ", Tamanho: " + quantidade);
    }
  }

  private void verificarIndiceParaInsercao(int indice) {
    if (indice < 0 || indice > quantidade) {
      throw new IndexOutOfBoundsException(
          "Índice: " + indice + ", Tamanho: " + quantidade);
    }
  }
}
