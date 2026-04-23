package caixagenerica;

public class Caixa<T> implements Armazenavel<T> {
  private T item;

  @Override
  public void guardar(T item) {
    this.item = item;
  }

  @Override
  public T recuperar() {
    return this.item;
  }

  public static void main(String[] args) {
    Caixa<String> caixaDeString = new Caixa<>();
    caixaDeString.guardar("Olá, Mundo!");
    System.out.println(caixaDeString.recuperar());

    Caixa<Integer> caixaDeInteger = new Caixa<>();
    caixaDeInteger.guardar(42);
    System.out.println(caixaDeInteger.recuperar());

    Caixa<Double> caixaDeDouble = new Caixa<>();
    caixaDeDouble.guardar(3.14);
    System.out.println(caixaDeDouble.recuperar());

    Integer numeroInteger = 100;
    caixaDeString.guardar(numeroInteger);// Erro de compilação: incompatible types: Integer cannot be converted to String
    /*
     * Mensagem de erro do compilador:
     * error: method guardar in class Caixa<T> cannot be applied to given types;
     * caixaDeString.guardar(numeroInteger);
     * ^
     * required: String
     * found: Integer
     * reason: argument mismatch; Integer cannot be converted to String
     * where T is a type-variable:
     * T extends Object declared in class Caixa
     * 1 error
     */
  }
}