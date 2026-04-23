package arraysdinamicos;

public class TesteVazamentoMemoria {
  static class ObjetoGrande {
    private byte[] dados = new byte[1024 * 1024]; // 1 MB

    @Override
    protected void finalize() {
      System.out.println("ObjetoGrande sendo coletado!");
    }
  }

  // Versão SEM anular referências (vazamento)
  static class ListaSemNull<T> {
    private Object[] elementos;
    private int quantidade;

    public ListaSemNull(int capacidade) {
      this.elementos = new Object[capacidade];
      this.quantidade = 0;
    }

    public void adicionar(T elemento) {
      elementos[quantidade++] = elemento;
    }

    public T removerDoFinal() {
      if (quantidade == 0)
        return null;
      @SuppressWarnings("unchecked")
      T elemento = (T) elementos[quantidade - 1];
      quantidade--; // NÃO anula!
      return elemento;
    }
  }

  // Versão COM anulação de referências (correta)
  static class ListaComNull<T> {
    private Object[] elementos;
    private int quantidade;

    public ListaComNull(int capacidade) {
      this.elementos = new Object[capacidade];
      this.quantidade = 0;
    }

    public void adicionar(T elemento) {
      elementos[quantidade++] = elemento;
    }

    public T removerDoFinal() {
      if (quantidade == 0)
        return null;
      @SuppressWarnings("unchecked")
      T elemento = (T) elementos[quantidade - 1];
      elementos[--quantidade] = null; // Anula!
      return elemento;
    }
  }

  public static void main(String[] args) throws InterruptedException {
    System.out.println("=== Teste com lista SEM anulação ===");
    testarLista(new ListaSemNull<>(10000));

    System.out.println("\n=== Teste com lista COM anulação ===");
    testarLista(new ListaComNull<>(10000));
  }

  private static void testarLista(Object lista) throws InterruptedException {
    // Adiciona 10.000 objetos de 1MB cada
    for (int i = 0; i < 10000; i++) {
      if (lista instanceof ListaSemNull) {
        ((ListaSemNull<ObjetoGrande>) lista).adicionar(new ObjetoGrande());
      } else {
        ((ListaComNull<ObjetoGrande>) lista).adicionar(new ObjetoGrande());
      }
    }

    System.out.println("Adicionados 10.000 objetos (10 GB)");
    Thread.sleep(2000);

    // Remove todos
    for (int i = 0; i < 10000; i++) {
      if (lista instanceof ListaSemNull) {
        ((ListaSemNull<ObjetoGrande>) lista).removerDoFinal();
      } else {
        ((ListaComNull<ObjetoGrande>) lista).removerDoFinal();
      }
    }

    System.out.println("Removidos 10.000 objetos");
    System.out.println("Forçando GC...");
    System.gc();
    Thread.sleep(5000);
  }
}
