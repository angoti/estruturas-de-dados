package estruturas;

import java.util.Scanner;

public class HistoricoNavegacao {

  // Usamos Deque<String> do JCF — demonstra integração com a seção 6
  private final java.util.Deque<String> historico = new java.util.ArrayDeque<>();
  private final java.util.Deque<String> futuro = new java.util.ArrayDeque<>();
  private String paginaAtual;

  public void visitar(String url) {
    if (paginaAtual != null)
      historico.push(paginaAtual);
    paginaAtual = url;
    futuro.clear(); // nova navegação descarta o histórico de "avançar"
  }

  public String voltar() {
    if (historico.isEmpty())
      throw new IllegalStateException("Sem histórico anterior");
    futuro.push(paginaAtual);
    return paginaAtual = historico.pop();
  }

  public String avancar() {
    if (futuro.isEmpty())
      throw new IllegalStateException("Sem páginas futuras");
    historico.push(paginaAtual);
    return paginaAtual = futuro.pop();
  }

  public String paginaAtual() {
    return paginaAtual;
  }

  public boolean podeVoltar() {
    return !historico.isEmpty();
  }

  public boolean podeAvancar() {
    return !futuro.isEmpty();
  }

  public static void main(String[] args) {
    HistoricoNavegacao navegador = new HistoricoNavegacao();
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== Navegador Web Simples ===");
    System.out.println("Demonstração de pilhas em voltar e avançar.\n");

    boolean executando = true;
    while (executando) {
      exibirMenu();
      String opcao = scanner.nextLine().trim();

      switch (opcao) {
        case "1":
          System.out.print("Digite a URL: ");
          String url = scanner.nextLine().trim();
          if (url.isEmpty()) {
            System.out.println("URL inválida.");
            break;
          }
          navegador.visitar(url);
          System.out.println("Visitando: " + navegador.paginaAtual());
          break;
        case "2":
          if (!navegador.podeVoltar()) {
            System.out.println("Não há página anterior.");
            break;
          }
          System.out.println("Voltou para: " + navegador.voltar());
          break;
        case "3":
          if (!navegador.podeAvancar()) {
            System.out.println("Não há página para avançar.");
            break;
          }
          System.out.println("Avançou para: " + navegador.avancar());
          break;
        case "4":
          if (navegador.paginaAtual() == null) {
            System.out.println("Nenhuma página aberta.");
          } else {
            System.out.println("Página atual: " + navegador.paginaAtual());
          }
          System.out.println("Pode voltar? " + (navegador.podeVoltar() ? "Sim" : "Não"));
          System.out.println("Pode avançar? " + (navegador.podeAvancar() ? "Sim" : "Não"));
          break;
        case "0":
          executando = false;
          System.out.println("Encerrando navegador...");
          break;
        default:
          System.out.println("Opção inválida. Escolha uma opção do menu.");
      }

      System.out.println();
    }

    scanner.close();
  }

  private static void exibirMenu() {
    System.out.println("Menu:");
    System.out.println("1 - Visitar URL");
    System.out.println("2 - Voltar");
    System.out.println("3 - Avançar");
    System.out.println("4 - Mostrar status");
    System.out.println("0 - Sair");
    System.out.print("Escolha uma opção: ");
  }
}
