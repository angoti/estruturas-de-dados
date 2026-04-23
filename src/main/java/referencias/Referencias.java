package referencias;

public class Referencias {
  public static void main(String[] args) {
    StringBuilder sb1 = new StringBuilder("Ola");
    StringBuilder sb2 = sb1;
    sb2.append(" Mundo"); // modifica o mesmo objeto StringBuilder
    System.out.println(sb1);
    String s1 = "Ola";
    String s2 = s1;
    s2 = s2 + " Mundo"; // criação de um novo objeto String
    System.out.println(s1); // ???

    /*
    A classe String é imutável, ou seja, uma vez criado um objeto String, ele não pode ser modificado.
    Stringbuilder é mutável, ou seja, pode ser modificado após a criação. Quando fazemos sb2.append(" Mundo"), estamos modificando o mesmo objeto StringBuilder que sb1 referencia, por isso a saída é "Ola Mundo". Já quando fazemos s2 = s2 + " Mundo", estamos criando um novo objeto String e atribuindo a s2, enquanto s1 continua referenciando o objeto original "Ola". Por isso a saída é "Ola".
    */
  }
  
}
