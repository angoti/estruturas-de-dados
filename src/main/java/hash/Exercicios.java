package hash;

import java.util.HashMap;
import java.util.Map;

public class Exercicios {

  public static Map<String, Integer> frequencia(String[] palavras) {
    Map<String, Integer> freq = new HashMap<>();
    for (String p : palavras) {
      // getOrDefault evita null check manual
      freq.put(p, freq.getOrDefault(p, 0) + 1);
    }
    return freq;
  }

  // Alternativa com merge (Java 8+):
  public static Map<String, Integer> frequenciaMerge(String[] palavras) {
    Map<String, Integer> freq = new HashMap<>();
    for (String p : palavras) {
      freq.merge(p, 1, Integer::sum);
    }
    return freq;
  }

  public static int[] twoSum(int[] nums, int target) {
    // Mapa: valor já visto → índice
    Map<Integer, Integer> visto = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      int complemento = target - nums[i];
      if (visto.containsKey(complemento)) {
        return new int[] { visto.get(complemento), i };
      }
      visto.put(nums[i], i);
    }
    throw new IllegalArgumentException("Nenhum par encontrado");
  }

  public static void main(String[] args) {
    String[] palavras = { "maçã", "banana", "maçã", "laranja", "banana", "maçã" };
    Map<String, Integer> freq = frequencia(palavras);
    System.out.println(freq); // {maçã=3, banana=2, laranja=1}

    int[] nums = { 2, 7, 11, 15 };
    int target = 9;
    int[] indices = twoSum(nums, target);
    System.out.println("Índices: " + indices[0] + ", " + indices[1]); // Índices: 0, 1
  }

}
