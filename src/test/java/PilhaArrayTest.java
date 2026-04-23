

import org.junit.jupiter.api.*;

import estruturas.PilhaArray;

import java.util.EmptyStackException;
import static org.junit.jupiter.api.Assertions.*;

class PilhaArrayTest {

  private PilhaArray<String> pilha;

  @BeforeEach
  void setUp() {
    pilha = new PilhaArray<>();
  }

  // ── Comportamento básico ──────────────────────────────────────

  @Test
  @DisplayName("Pilha recém-criada deve estar vazia")
  void deveEstarVaziaAoCriar() {
    assertTrue(pilha.isEmpty());
    assertEquals(0, pilha.size());
  }

  @Test
  @DisplayName("push e pop devem seguir LIFO")
  void pushEPopDevemSeguirLifo() {
    pilha.push("A");
    pilha.push("B");
    pilha.push("C");
    assertEquals("C", pilha.pop()); // último entrou, primeiro sai
    assertEquals("B", pilha.pop());
    assertEquals("A", pilha.pop());
    assertTrue(pilha.isEmpty());
  }

  @Test
  @DisplayName("peek não deve remover o elemento")
  void peekNaoDeveRemover() {
    pilha.push("X");
    assertEquals("X", pilha.peek());
    assertEquals(1, pilha.size()); // ainda está lá
    assertEquals("X", pilha.pop()); // agora remove
    assertTrue(pilha.isEmpty());
  }

  // ── Casos de borda ────────────────────────────────────────────

  @Test
  @DisplayName("pop em pilha vazia deve lançar EmptyStackException")
  void popEmVaziaDeveriaLancarExcecao() {
    assertThrows(EmptyStackException.class, () -> pilha.pop());
  }

  @Test
  @DisplayName("peek em pilha vazia deve lançar EmptyStackException")
  void peekEmVaziaDeveriaLancarExcecao() {
    assertThrows(EmptyStackException.class, () -> pilha.peek());
  }

  @Test
  @DisplayName("Pilha com um único elemento deve funcionar corretamente")
  void umUnicoElemento() {
    pilha.push("único");
    assertFalse(pilha.isEmpty());
    assertEquals("único", pilha.peek());
    assertEquals("único", pilha.pop());
    assertTrue(pilha.isEmpty());
  }

  // ── Redimensionamento ─────────────────────────────────────────

  @Test
  @DisplayName("Deve redimensionar além da capacidade inicial")
  void deveRedimensionarAutomaticamente() {
    // PilhaArray começa com capacidade 16; inserir 200 elementos
    for (int i = 0; i < 200; i++)
      pilha.push("item" + i);
    assertEquals(200, pilha.size());
    assertEquals("item199", pilha.peek()); // topo correto após redimensionamentos
  }

  @Test
  @DisplayName("Pop após redimensionamento deve manter LIFO")
  void popAposRedimensionamentoDelesManterlIFO() {
    for (int i = 0; i < 50; i++)
      pilha.push(String.valueOf(i));
    for (int i = 49; i >= 0; i--) {
      assertEquals(String.valueOf(i), pilha.pop());
    }
  }
}
