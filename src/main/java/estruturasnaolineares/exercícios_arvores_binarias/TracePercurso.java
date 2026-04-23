package estruturasnaolineares.exercícios_arvores_binarias;

import estruturasnaolineares.ArvoreBinaria;
import estruturasnaolineares.No;

public class TracePercurso {  
  public static void main(String[] args) {
    ArvoreBinaria<Integer> arvore = new ArvoreBinaria<>();

    No<Integer> raiz = new No<>(10);

    No<Integer> n5 = new No<>(5);
    No<Integer> n15 = new No<>(15);
    No<Integer> n3 = new No<>(3);
    No<Integer> n7 = new No<>(7);
    No<Integer> n12 = new No<>(12);
    No<Integer> n20 = new No<>(20);

    // liga filhos de 5
    n5.esquerdo = n3;
    n5.direito = n7;

    // liga filhos de 15
    n15.esquerdo = n12;
    n15.direito = n20;

    // liga filhos da raiz
    raiz.esquerdo = n5;
    raiz.direito = n15;

    // injeta na árvore
    arvore.raiz = raiz;

    // verifica
    System.out.println(arvore.inOrder()); // [3, 5, 7, 10, 12, 15, 20]
    System.out.println(arvore.preOrder()); // [10, 5, 3, 7, 15, 12, 20]
    System.out.println(arvore.postOrder()); // [3, 7, 5, 12, 20, 15, 10]
    System.out.println(arvore.levelOrder()); // [10, 5, 15, 3, 7, 12, 20]
    System.out.println("altura: " + arvore.altura()); // 2
    System.out.println("tamanho: " + arvore.tamanho()); // 7
    System.out.println("completa: " + arvore.ehArvoreCompleta()); // true
    System.out.println("folhas: " + arvore.contarFolhas()); // 4
  }

}
