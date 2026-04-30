import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import estruturasnaolineares.FilaDePrioridade;

public class FilaDePrioridadeTest {

    @Test
    @DisplayName("buildHeap([4,10,3,5,1,8,2]) deve ter 1 no topo")
    void buildHeapTopoMinimo() {
        Integer[] valores = {4, 10, 3, 5, 1, 8, 2};
        FilaDePrioridade<Integer> heap = FilaDePrioridade.buildHeap(valores, Integer::compareTo);
        assertEquals(1, heap.topo());
    }

    @Test
    @DisplayName("buildHeap([4,10,3,5,1,8,2]) deve remover em ordem crescente")
    void buildHeapOrdemCrescente() {
        Integer[] valores = {4, 10, 3, 5, 1, 8, 2};
        FilaDePrioridade<Integer> heap = FilaDePrioridade.buildHeap(valores, Integer::compareTo);
        int[] esperado = {1, 2, 3, 4, 5, 8, 10};
        for (int v : esperado) {
            assertEquals(v, heap.removerTopo());
        }
    }

    @Test
    @DisplayName("inserir([4,10,3,5,1,8,2]) deve ter 1 no topo e tamanho 7")
    void inserirUmAUm() {
        FilaDePrioridade<Integer> heap = new FilaDePrioridade<>();
        for (int v : new int[]{4, 10, 3, 5, 1, 8, 2}) {
            heap.inserir(v);
        }
        assertEquals(7, heap.tamanho());
        assertEquals(1, heap.topo());
    }
}
