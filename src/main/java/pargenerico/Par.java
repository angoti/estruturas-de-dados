package pargenerico;

public class Par<A, B> {
  private A primeiro;
  private B segundo;

  public Par(A primeiro, B segundo) {
    this.primeiro = primeiro;
    this.segundo = segundo;
  }

  public A getPrimeiro() {
    return this.primeiro;
  }

  public B getSegundo() {
    return this.segundo;
  }

  @Override
  public String toString() {
    return "Par{" +
        "primeiro=" + primeiro +
        ", segundo=" + segundo +
        '}';
  }

  public static void main(String[] args) {
    Par<String, Integer> teste1 = new Par<String, Integer>("Edson Angoti", 58);
    System.out.println(teste1);

    Par<String, Par<Integer, Double>> teste2 = new Par<String, Par<Integer, Double>>("Dados compostos",
        new Par<Integer, Double>(10, 3.14));
    System.out.println(teste2);
  }

}
