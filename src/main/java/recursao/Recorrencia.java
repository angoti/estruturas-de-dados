public class Recorrencia {

  public static void main(String[] args) {
    System.out.println("Relações de Recorrencia");
    int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int alvo = 5;
    Recorrencia recorrencia = new Recorrencia();
    int resultado = recorrencia.buscaBinariaRecursiva(arr, alvo, 0, arr.length - 1);
    System.out.println("Elemento encontrado no índice: " + resultado);
  }

  public static void mergeSort(int[] arr, int esq, int dir) {
    if (esq >= dir) {
      return; // Caso base: array de 1 elemento já está ordenado
    }
    int meio = esq + (dir - esq) / 2;

    // Primeira chamada recursiva: ordena metade esquerda - entrada n/2
    mergeSort(arr, esq, meio);

    // Segunda chamada recursiva: ordena metade direita - entrada n/2
    mergeSort(arr, meio + 1, dir);

    // Mescla as duas metades ordenadas - operação O(n)
    merge(arr, esq, meio, dir);
  }

public static void merge(int[] arr, int esq, int meio, int dir) {
    int[] temp = new int[dir - esq + 1];
    int i = esq, j = meio + 1, k = 0;

    while (i <= meio && j <= dir) {
      if (arr[i] <= arr[j]) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }

    while (i <= meio) {
      temp[k++] = arr[i++];
    }

    while (j <= dir) {
      temp[k++] = arr[j++];
    }

    System.arraycopy(temp, 0, arr, esq, temp.length);
  }

  public static int buscaBinariaRecursiva(int[] arr, int alvo, int esq, int dir) {
    // Caso base: O(1)
    if (esq > dir) {
      return -1;
    }

    // Cálculo do meio: O(1)
    int meio = esq + (dir - esq) / 2;

    // Comparação: O(1)
    if (arr[meio] == alvo) {
      return meio;
    }

    // AQUI está a chave: chamada recursiva com entrada MENOR
    if (arr[meio] > alvo) {
      // Busca na metade esquerda - entrada reduzida pela metade
      return buscaBinariaRecursiva(arr, alvo, esq, meio - 1);
    } else {
      // Busca na metade direita - entrada reduzida pela metade
      return buscaBinariaRecursiva(arr, alvo, meio + 1, dir);
    }
  }

}