package com.bobocode.cs;

import java.util.Objects;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 *
 * @param <T> a generic parameter
 * @author Taras Boychuk
 * @author Ivan Virchenko
 */
public class LinkedQueue<T> implements Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }

    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(T element) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        Objects.requireNonNull(element);
        Node<T> newNode = new Node<>(element);
        if (head == null && tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    public T poll() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        if (isEmpty())
            return null;

        Node<T> headNode = this.head;
        if (size() == 1) {
            head = null;
            tail = null;
        } else {
            head = headNode.next;
        }
        size--;
        return headNode.element;
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        return size() == 0;
    }

}
