package estruturasnaolineares.exercícios_arvores_binarias;

import estruturasnaolineares.AVL;
import estruturasnaolineares.BST;

public class TracePercursoAVL {

  public static void tracePercursoAVL() {
    AVL<Integer> arvore = new AVL<>();

    arvore.inserir(50);
    arvore.inserir(30);

    arvore.inserir(70);
    arvore.inserir(20);
    arvore.inserir(40);

    arvore.inserir(15);

    // arvore.inserir(3);
    // arvore.inserir(7);
    // arvore.inserir(12);
    // arvore.inserir(20);

    // ávore montada
    // 10
    // / \
    // 5 15
    // / \ / \
    // 3 7 12 20

    System.out.println("Rotações realizadas: " + arvore.contarRotacoes());
    System.out.println(arvore.inOrderIterativo());
    System.out.println(arvore.inOrder());
    System.out.println(arvore.preOrder());
    System.out.println(arvore.postOrder());
    System.out.println(arvore.levelOrder());
    System.out.println("altura: " + arvore.altura());
    System.out.println("tamanho: " + arvore.tamanho());
    System.out.println("completa: " + arvore.ehArvoreCompleta());
    System.out.println("folhas: " + arvore.contarFolhas());
  }

  public static void comparaAlturaBST_AVL() {
    AVL<Integer> avl = new AVL<>();
    BST<Integer> bst = new BST<>();

    for (int i = 1; i <= 10; i++) {
      int valor = (int) (Math.random() * 10000);
      avl.inserir(valor);
      bst.inserir(valor);
    }

    System.out.println("Altura AVL: " + avl.altura());
    System.out.println("Altura BST: " + bst.altura());

  }

  public static void main(String[] args) {
    comparaAlturaBST_AVL();
  }

}
