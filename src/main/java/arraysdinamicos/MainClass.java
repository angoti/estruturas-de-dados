package arraysdinamicos;

import java.util.Arrays;

public class MainClass {
  public static void main(String[] args) {
    Integer[] array = { 10, 20, 30, 40, 50, null, null, null };
    int quantidade = 5;
    int indice = 2;

    System.out.println("Antes: " + Arrays.toString(array));

    // Simula abertura de espaço para inserção
    System.arraycopy(array, indice,
        array, indice + 1,
        quantidade - indice);

    System.out.println("Depois do arraycopy: " + Arrays.toString(array));

    // Agora insere o elemento
    array[indice] = 25;

    System.out.println("Depois da inserção: " + Arrays.toString(array));

  }
}
