package estruturasnaolineares;

public class No<T> {
  T dado;
  No<T> esquerdo;
  No<T> direito;

  public No(T dado) {
    this.dado = dado;
    this.esquerdo = null;
    this.direito = null;
  }
}
