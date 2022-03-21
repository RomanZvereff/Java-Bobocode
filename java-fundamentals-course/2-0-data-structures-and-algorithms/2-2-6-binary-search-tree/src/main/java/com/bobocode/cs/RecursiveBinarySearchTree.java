package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private static class Node<T> {
        T element;
        Node<T> leftNode;
        Node<T> rightNode;

        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> root;
    private int size;
    private int depth;


    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        RecursiveBinarySearchTree<T> recursiveBinarySearchTree = new RecursiveBinarySearchTree<>();
        for (T e : elements) {
            recursiveBinarySearchTree.insert(e);
        }
        return recursiveBinarySearchTree;
    }

    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        } else {
            return insert(root, element);
        }
    }

    private boolean insert(Node<T> node, T element) {
        Node<T> newNode = new Node<>(element);

        if (node.element.compareTo(element) > 0) {
            if (node.leftNode != null) {
                return insert(node.leftNode, element);
            } else {
                node.leftNode = newNode;
                size++;
                return true;
            }
        } else if (node.element.compareTo(element) < 0) {
            if (node.rightNode != null) {
                return insert(node.rightNode, element);
            } else {
                node.rightNode = newNode;
                size++;
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(T element) {
        Objects.requireNonNull(element);
        return contains(root, element);
    }

    private boolean contains(Node<T> node, T element) {
        boolean isContains = false;
        if (node == null)
            return false;
        if (node.element.compareTo(element) == 0) {
            isContains = true;
        }
        if (node.element.compareTo(element) > 0) {
            isContains = contains(node.leftNode, element);
        } else if (node.element.compareTo(element) < 0) {
            isContains = contains(node.rightNode, element);
        }
        return isContains;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return root != null ? depth(root) - 1 : 0;
    }

    private int depth(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(depth(node.leftNode), depth(node.rightNode));
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.leftNode, consumer);
            consumer.accept(node.element);
            inOrderTraversal(node.rightNode, consumer);
        }
    }

}
