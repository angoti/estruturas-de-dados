package arraysdinamicos;

import java.util.ArrayList;
import java.util.List;

public class Teste {
  
  public static void main(String[] args) {
    List<Integer> lista = new ArrayList<>();
    Lista<Integer> listaDinamica = new ArrayDinamico<>();

    long inicio, fim;

    // Medição com ArrayDinamico
    inicio = System.nanoTime();
    for (int i = 0; i < 100_000; i++) {
      listaDinamica.inserir(0, i);
    }
    fim = System.nanoTime();
    System.out.println("Tempo ArrayDinamico: " + (fim - inicio) / 1_000_000 + " ms");

    // Medição com ArrayList
    // inicio = System.nanoTime();
    // for (int i = 0; i < 100_000; i++) {
    //   lista.add(0,i);
    // }
    // fim = System.nanoTime();
    // System.out.println("Tempo ArrayList: " + (fim - inicio) / 1_000_000 + " ms");
  }
}
