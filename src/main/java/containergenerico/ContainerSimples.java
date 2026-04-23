package containergenerico;

public class ContainerSimples<T> implements Container<T> {
  private Object[] itens;
  private int tamanho;

  public ContainerSimples(int capacidade) {
    this.itens = new Object[capacidade];
    this.tamanho = 0;
  }

  @Override
  public void adicionar(T item) {
    if (tamanho < itens.length) {
      itens[tamanho] = item;
      tamanho++;
    } else {
      // throw new RuntimeException("Container cheio");
      this.itens = java.util.Arrays.copyOf(itens, itens.length + 10);
      this.itens[tamanho] = item;
      tamanho++;
    }
  }

  @Override
  public T remover() {
    if (tamanho > 0) {
      T item = (T) itens[tamanho - 1];
      itens[tamanho - 1] = null; // ajuda o GC
      tamanho--;
      return item;
    } else {
      throw new RuntimeException("Container vazio");
    }
  }

  @Override
  public int tamanho() {
    return tamanho;
  }

  @Override
  public boolean estaVazio() {
    return tamanho == 0;
  }

  public static void main(String[] args) {
    ContainerSimples<String> container = new ContainerSimples<>(5);
    container.adicionar("Olá");
    container.adicionar("Mundo");
    System.out.println("Tamanho: " + container.tamanho());
    System.out.println("Removido: " + container.remover());
    System.out.println("Tamanho: " + container.tamanho());

    ContainerSimples<Integer> containerInt = new ContainerSimples<>(5);
    containerInt.adicionar(10);
    containerInt.adicionar(20);
    System.out.println("Tamanho: " + containerInt.tamanho());
    System.out.println("Removido: " + containerInt.remover());
    System.out.println("Tamanho: " + containerInt.tamanho());

    ContainerSimples<String> container2 = new ContainerSimples<>(5);
    container2.adicionar("Olá");
    for (int i = 0; i < 500000; i++) {
      container2.adicionar("Mundo" + (i + 1));
      System.out.println("Tamanho: " + container2.tamanho());
    }

  }

}
