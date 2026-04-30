package estruturasnaolineares;

public class No<T> {
  public T dado;
  public No<T> esquerdo;
  public No<T> direito;

  public No(T dado) {
    this.dado = dado;
    this.esquerdo = null;
    this.direito = null;
  }

  // hashcode
  @Override
  public int hashCode() {
    return dado.hashCode();
  }
}
