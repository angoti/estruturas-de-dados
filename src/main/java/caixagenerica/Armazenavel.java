package caixagenerica;

public interface Armazenavel<T> {

  void guardar(T item);
  T recuperar();
  
}
