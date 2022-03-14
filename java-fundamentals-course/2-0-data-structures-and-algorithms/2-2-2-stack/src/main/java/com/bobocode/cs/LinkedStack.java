package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;
import com.bobocode.util.ExerciseNotCompletedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link LinkedStack} is a stack implementation that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
public class LinkedStack<T> implements Stack<T> {

    private Node<T> head;
    private int size;

    public LinkedStack() {
        size = 0;
    }

    public Node<T> getHead() {
        return head;
    }

    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        LinkedStack<T> linkedStack = new LinkedStack<>();
        for (T e : elements) {
            linkedStack.push(e);
        }
        return linkedStack;
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        if (element == null)
            throw new NullPointerException();
        Node<T> newNode = new Node<>(element);
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        if (isEmpty())
            throw new EmptyStackException();
        Node<T> head = getHead();
        this.head = head.next;
        size--;
        return head.element;
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        return this.size;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method;
        return this.size == 0;
    }

}
