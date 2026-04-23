import org.junit.jupiter.api.*;

import estruturasnaolineares.ArvoreBinaria;
import estruturasnaolineares.No;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ArvoreBinariaTest {
  private ArvoreBinaria<Integer> arvore;

  /**
   * 4
   * / \
   * 2 6
   * / \ / \
   * 1 3 5 7
   */
  @BeforeEach
  void setUp() {
    arvore = new ArvoreBinaria<>();
    No<Integer> raiz = new No<>(4);
    raiz.esquerdo = new No<>(2);
    raiz.direito = new No<>(6);
    raiz.esquerdo.esquerdo = new No<>(1);
    raiz.esquerdo.direito = new No<>(3);
    raiz.direito.esquerdo = new No<>(5);
    raiz.direito.direito = new No<>(7);
    arvore.raiz = raiz;
  }

  @Test
  @DisplayName("In-Order deve retornar [1,2,3,4,5,6,7]")
  void inOrder() {
    assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), arvore.inOrder());
  }

  @Test
  @DisplayName("Pre-Order deve retornar [4,2,1,3,6,5,7]")
  void preOrder() {
    assertEquals(List.of(4, 2, 1, 3, 6, 5, 7), arvore.preOrder());
  }

  @Test
  @DisplayName("Post-Order deve retornar [1,3,2,5,7,6,4]")
  void postOrder() {
    assertEquals(List.of(1, 3, 2, 5, 7, 6, 4), arvore.postOrder());
  }

  @Test
  @DisplayName("Level-Order deve retornar [4,2,6,1,3,5,7]")
  void levelOrder() {
    assertEquals(List.of(4, 2, 6, 1, 3, 5, 7), arvore.levelOrder());
  }

  @Test
  @DisplayName("Altura da árvore perfeita de 7 nós deve ser 2")
  void altura() {
    assertEquals(2, arvore.altura());
  }

  @Test
  @DisplayName("Árvore vazia deve ter altura -1")
  void alturaVazia() {
    assertEquals(-1, new ArvoreBinaria<>().altura());
  }
}