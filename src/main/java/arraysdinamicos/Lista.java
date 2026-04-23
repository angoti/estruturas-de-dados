package arraysdinamicos;

public interface Lista<T> {

  void adicionar(T elemento);
  void inserir(int indice, T elemento);
  T remover(int indice);
  T obter(int indice);
  int tamanho();
  boolean estaVazia();
  boolean contem(T elemento);
  int indiceDe(T elemento);
  void limpar();
}

