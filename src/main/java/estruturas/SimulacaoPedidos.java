package estruturas;

// Record Java 16+: classe imutável de dados em uma linha
record Pedido(int id, String produto, double valor) {
}

public class SimulacaoPedidos {
  public static void main(String[] args) {
    Fila<Pedido> fila = new FilaLista<>();

    // Produtor: recebe pedidos via HTTP
    fila.enqueue(new Pedido(1, "Notebook", 3500.00));
    fila.enqueue(new Pedido(2, "Mouse", 150.00));
    fila.enqueue(new Pedido(3, "Teclado", 280.00));

    System.out.println("Pedidos na fila: " + fila.size());
    System.out.println("Próximo: " + fila.peek().produto());

    // Consumidor: processa em ordem FIFO
    while (!fila.isEmpty()) {
      Pedido p = fila.dequeue();
      System.out.printf("Processado #%d: %s — R$ %.2f%n",
          p.id(), p.produto(), p.valor());
    }
  }
}
// Saída:
// Pedidos na fila: 3
// Próximo: Notebook
// Processado #1: Notebook — R$ 3500,00
// Processado #2: Mouse — R$ 150,00
// Processado #3: Teclado — R$ 280,00
