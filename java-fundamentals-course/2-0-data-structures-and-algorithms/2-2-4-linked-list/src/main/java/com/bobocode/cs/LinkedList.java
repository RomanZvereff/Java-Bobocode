package com.bobocode.cs;


import com.bobocode.util.ExerciseNotCompletedException;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedList<T> implements List<T> {

    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        LinkedList<T> linkedList = new LinkedList<>();
        for (T e : elements) {
            linkedList.add(e);
        }
        return linkedList;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        Objects.requireNonNull(element);
        Node<T> newNode = new Node<>(element);
        if (size() == 0) {
            first = last = newNode;
        }else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        Objects.requireNonNull(element);
        Objects.checkIndex(index, size + 1);
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            if (isEmpty()) {
                add(element);
            }else {
                newNode.next = first;
                first = newNode;
                size++;
            }
        }else if (index == size()) {
            add(element);
        }else {
            Node<T> currentNode = first;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            size++;
        }
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        Objects.checkIndex(index, size);
        Node<T> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        currentNode.element = element;
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        Objects.checkIndex(index, size());
        Node<T> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.element;
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        if (first != null) {
            return first.element;
        }
        throw new NoSuchElementException();
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        if (last != null) {
            return last.element;
        }
        throw new NoSuchElementException();
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        Objects.checkIndex(index, size);
        T element;
        Node<T> currentNode = first;
        if (index == 0) {
            element = first.element;
            first = currentNode.next;
        }else {
            if (index == (size() - 1)) {
                currentNode = getNodeByIndex(index - 1);
                element = currentNode.next.element;
                currentNode.next = null;
            } else {
                currentNode = getNodeByIndex(index - 1);
                element = currentNode.next.element;
                currentNode.next = currentNode.next.next;
            }
        }
        size--;
        return element;
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        Node<T> currentNode = first;
        for (int i = 0; i < size; i++) {
            if (element.equals(currentNode.element)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        return size() == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        first = last = null;
        size = 0;
    }

    private Node<T> getNodeByIndex(int index) {
        Node<T> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

}
