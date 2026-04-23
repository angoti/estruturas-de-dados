package bigO;

public class solucoes {
  // Busca binária em array ordenado - O(log n)
  public int buscaBinaria(int[] arr, int alvo) {
    int esq = 0, dir = arr.length - 1;
    while (esq <= dir) {
      int meio = esq + (dir - esq) / 2;
      if (arr[meio] == alvo)
        return meio;
      if (arr[meio] < alvo)
        esq = meio + 1;
      else
        dir = meio - 1;
    }
    return -1;
  }

  public int[] inverterArray(int[] arr) {
    int n = arr.length;
    int[] invertido = new int[n];
    for (int i = 0; i < n; i++) {
      invertido[i] = arr[n - 1 - i];
    }
    return invertido;
  }

  public int[] inverterArrayInplace(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n / 2; i++) {
      int temp = arr[i];
      arr[i] = arr[n - 1 - i];
      arr[n - 1 - i] = temp;
    }
    return arr;
  }

  public static void main(String[] args) {
    solucoes b = new solucoes();
    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    // int alvo = 10;
    // int resultado = b.buscaBinaria(arr, alvo);
    // if (resultado != -1) {
    //   System.out.println("Elemento encontrado no índice: " + resultado);
    // } else {
    //   System.out.println("Elemento não encontrado.");
    // }
    int[] invertido = b.inverterArray(arr);
    System.out.print("Array invertido: ");
    for (int num : invertido) {
      System.out.print(num + " ");
    }
  }
  
}
