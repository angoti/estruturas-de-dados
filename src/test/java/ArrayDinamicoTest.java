import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import arraysdinamicos.ArrayDinamico;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class ArrayDinamicoTest {

  private ArrayDinamico<String> lista;

  @BeforeEach
  void setUp() {
    lista = new ArrayDinamico<>();
  }

  @Test
  @DisplayName("Lista recém-criada deve estar vazia")
  void deveEstarVaziaAoCriar() {
    assertTrue(lista.estaVazia());
    assertEquals(0, lista.tamanho());
  }

  @Test
  @DisplayName("Deve adicionar elementos ao final")
  void deveAdicionarElementos() {
    lista.adicionar("A");
    lista.adicionar("B");
    assertEquals(2, lista.tamanho());
    assertEquals("A", lista.obter(0));
    assertEquals("B", lista.obter(1));
  }

  @Test
  @DisplayName("Deve inserir em posição específica")
  void deveInserirNaPosicao() {
    lista.adicionar("A");
    lista.adicionar("C");
    lista.inserir(1, "B");
    assertEquals("B", lista.obter(1));
    assertEquals("C", lista.obter(2));
  }

  @Test
  @DisplayName("Deve remover elemento e retorná-lo")
  void deveRemoverElemento() {
    lista.adicionar("A");
    lista.adicionar("B");
    lista.adicionar("C");
    String removido = lista.remover(1);
    assertEquals("B", removido);
    assertEquals(2, lista.tamanho());
    assertEquals("C", lista.obter(1));
  }

  @Test
  @DisplayName("Deve redimensionar automaticamente")
  void deveRedimensionar() {
    ArrayDinamico<Integer> numeros = new ArrayDinamico<>(2);
    for (int i = 0; i < 100; i++) {
      numeros.adicionar(i);
    }
    assertEquals(100, numeros.tamanho());
    assertEquals(50, numeros.obter(50));
  }

  @Test
  @DisplayName("Deve lançar exceção para índice inválido")
  void deveLancarExcecaoIndiceInvalido() {
    lista.adicionar("A");
    assertThrows(IndexOutOfBoundsException.class,
        () -> lista.obter(5));
    assertThrows(IndexOutOfBoundsException.class,
        () -> lista.obter(-1));
  }

  @Test
  @DisplayName("contem() deve localizar elementos")
  void deveVerificarContem() {
    lista.adicionar("Java");
    lista.adicionar("Spring");
    assertTrue(lista.contem("Java"));
    assertFalse(lista.contem("Python"));
  }

  @Test
  @DisplayName("limpar() deve remover todos os elementos")
  void deveLimpar() {
    lista.adicionar("A");
    lista.adicionar("B");
    lista.limpar();
    assertTrue(lista.estaVazia());
    assertEquals(0, lista.tamanho());
  }
}
