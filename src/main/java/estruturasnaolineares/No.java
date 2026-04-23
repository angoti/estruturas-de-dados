package estruturasnaolineares;

public class No<T> {
  T dado;
  public No<T> esquerdo;
  public No<T> direito;

  public No(T dado) {
    this.dado = dado;
    this.esquerdo = null;
    this.direito = null;
  }
}
