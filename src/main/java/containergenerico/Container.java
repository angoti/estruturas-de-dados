package containergenerico;

public interface Container<T> {

  void adicionar(T item);

  T remover();

  int tamanho();

  boolean estaVazio();

}
