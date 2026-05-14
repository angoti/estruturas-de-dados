package estruturasnaolineares;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implementação de uma árvore AVL genérica usando Comparator<T>.
 * Compatível com Java JDK 21+.
 */
public class AVLTree<T> {

  private static class Node<T> {
    T value;
    Node<T> left;
    Node<T> right;
    int height;

    Node(T value) {
      this.value = value;
      this.height = 1;
    }
  }

  private Node<T> root;
  private final Comparator<T> comparator;

  public AVLTree(Comparator<T> comparator) {
    this.comparator = comparator;
  }

  // =========================
  // INSERÇÃO
  // =========================

  public void insert(T value) {
    root = insert(root, value);
  }

  private Node<T> insert(Node<T> node, T value) {

    if (node == null) {
      return new Node<>(value);
    }

    int cmp = comparator.compare(value, node.value);

    if (cmp < 0) {
      node.left = insert(node.left, value);
    } else if (cmp > 0) {
      node.right = insert(node.right, value);
    } else {
      // Ignora duplicados
      return node;
    }

    updateHeight(node);

    return balance(node);
  }

  // =========================
  // REMOÇÃO
  // =========================

  public void remove(T value) {
    root = remove(root, value);
  }

  private Node<T> remove(Node<T> node, T value) {

    if (node == null) {
      return null;
    }

    int cmp = comparator.compare(value, node.value);

    if (cmp < 0) {
      node.left = remove(node.left, value);
    } else if (cmp > 0) {
      node.right = remove(node.right, value);
    } else {

      // Nó com 0 ou 1 filho
      if (node.left == null || node.right == null) {

        Node<T> temp = (node.left != null)
            ? node.left
            : node.right;

        if (temp == null) {
          node = null;
        } else {
          node = temp;
        }

      } else {

        // Nó com 2 filhos
        Node<T> successor = minValueNode(node.right);

        node.value = successor.value;

        node.right = remove(node.right, successor.value);
      }
    }

    if (node == null) {
      return null;
    }

    updateHeight(node);

    return balance(node);
  }

  private Node<T> minValueNode(Node<T> node) {

    Node<T> current = node;

    while (current.left != null) {
      current = current.left;
    }

    return current;
  }

  // =========================
  // BUSCA
  // =========================

  public boolean contains(T value) {
    return contains(root, value);
  }

  private boolean contains(Node<T> node, T value) {

    if (node == null) {
      return false;
    }

    int cmp = comparator.compare(value, node.value);

    if (cmp < 0) {
      return contains(node.left, value);
    }

    if (cmp > 0) {
      return contains(node.right, value);
    }

    return true;
  }

  // =========================
  // BALANCEAMENTO AVL
  // =========================

  private Node<T> balance(Node<T> node) {

    int balanceFactor = getBalance(node);

    // LEFT LEFT
    if (balanceFactor > 1 &&
        getBalance(node.left) >= 0) {

      return rotateRight(node);
    }

    // LEFT RIGHT
    if (balanceFactor > 1 &&
        getBalance(node.left) < 0) {

      node.left = rotateLeft(node.left);

      return rotateRight(node);
    }

    // RIGHT RIGHT
    if (balanceFactor < -1 &&
        getBalance(node.right) <= 0) {

      return rotateLeft(node);
    }

    // RIGHT LEFT
    if (balanceFactor < -1 &&
        getBalance(node.right) > 0) {

      node.right = rotateRight(node.right);

      return rotateLeft(node);
    }

    return node;
  }

  private Node<T> rotateRight(Node<T> y) {

    Node<T> x = y.left;
    Node<T> t2 = x.right;

    x.right = y;
    y.left = t2;

    updateHeight(y);
    updateHeight(x);

    return x;
  }

  private Node<T> rotateLeft(Node<T> x) {

    Node<T> y = x.right;
    Node<T> t2 = y.left;

    y.left = x;
    x.right = t2;

    updateHeight(x);
    updateHeight(y);

    return y;
  }

  // =========================
  // ALTURA / FATOR BALANCEAMENTO
  // =========================

  private void updateHeight(Node<T> node) {

    node.height = 1 + Math.max(
        height(node.left),
        height(node.right));
  }

  private int height(Node<T> node) {
    return node == null ? 0 : node.height;
  }

  private int getBalance(Node<T> node) {

    if (node == null) {
      return 0;
    }

    return height(node.left) - height(node.right);
  }

  // =========================
  // PERCURSOS
  // =========================

  public List<T> inOrder() {

    List<T> list = new ArrayList<>();

    inOrder(root, list);

    return list;
  }

  private void inOrder(Node<T> node, List<T> list) {

    if (node != null) {

      inOrder(node.left, list);

      list.add(node.value);

      inOrder(node.right, list);
    }
  }

  // =========================
  // IMPRESSÃO
  // =========================

  public void printInOrder() {

    for (T value : inOrder()) {
      System.out.println(value);
    }
  }
}