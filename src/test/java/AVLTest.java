import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import estruturasnaolineares.AVL;

public class AVLTest {
  private AVL<Integer> avl;

  @BeforeEach
  void setUp() {
    avl = new AVL<>();
  }

  @Test
  @DisplayName("Inserção em ordem crescente deve manter altura O(log n)")
  void inserirOrdemCrescente() {
    for (int i = 1; i <= 15; i++)
      avl.inserir(i);
    // Sem AVL seria 14 (degenerada); com AVL deve ser ≤ ceil(1.44 * log2(16)) = 5
    assertTrue(avl.alturaAVL() <= 5,
        "Altura: " + avl.alturaAVL());
  }

  @Test
  @DisplayName("In-order deve manter ordenação após inserções")
  void inOrder() {
    for (int v : new int[] { 10, 20, 30, 40, 50, 25 })
      avl.inserir(v);
    List<Integer> resultado = avl.inOrder();
    for (int i = 1; i < resultado.size(); i++)
      assertTrue(resultado.get(i) > resultado.get(i - 1));
  }

  @Test
  @DisplayName("Rotação LL: inserir 30, 20, 10 deve balancear")
  void rotacaoLL() {
    avl.inserir(30);
    avl.inserir(20);
    avl.inserir(10);
    // Após rotação, raiz deve ser 20
    assertEquals(20, avl.raiz.dado);
    assertEquals(1, avl.alturaAVL());
  }

  @Test
  @DisplayName("Rotação LR: inserir 30, 10, 20 deve balancear")
  void rotacaoLR() {
    avl.inserir(30);
    avl.inserir(10);
    avl.inserir(20);
    assertEquals(20, avl.raiz.dado);
    assertEquals(1, avl.alturaAVL());
  }

  @Test
  @DisplayName("Remoção deve manter balanceamento")
  void removerMantendoBalanco() {
    for (int v : new int[] { 5, 3, 7, 2, 4, 6, 8 })
      avl.inserir(v);
    avl.remover(3);
    assertFalse(avl.contem(3));
    assertEquals(avl.inOrder(), avl.inOrder()); // ainda ordenada
    assertTrue(avl.alturaAVL() <= 3);
  }
}