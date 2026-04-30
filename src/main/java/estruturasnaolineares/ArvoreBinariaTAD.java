package estruturasnaolineares;

import java.util.List;

public interface ArvoreBinariaTAD<T> {

  List<T> inOrder();

  List<T> preOrder();

  List<T> postOrder();

  List<T> levelOrder();

  int altura();

  int tamanho();

  boolean estaVazia();

  boolean ehArvoreCompleta();

  int contarFolhas();

}
