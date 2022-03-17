package com.bobocode.cs;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 *
 * @author Serhii Hryhus
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elements;
    private int size;

    /**
     * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
     *
     * @param initCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public ArrayList(int initCapacity) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        elements = new Object[initCapacity];
    }

    /**
     * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
     * A default size of inner array is 5;
     */
    public ArrayList() {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates and returns an instance of {@link ArrayList} with provided elements
     *
     * @param elements to add
     * @return new instance
     */
    public static <T> List<T> of(T... elements) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        List<T> list = new ArrayList<>();
        for (T e : elements) {
            list.add(e);
        }
        return list;
    }

    /**
     * Adds an element to the array.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        resizeIfNeeded();
        elements[size()] = element;
        size++;
    }

    /**
     * Adds an element to the specific position in the array where
     *
     * @param index   index of position
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        Objects.checkIndex(index, size() + 1);
        resizeIfNeeded();
        if (index == 0) {
            if (isEmpty()) {
                elements[index] = element;
            } else {
                System.arraycopy(elements, 0, elements, 1, size() - 1);
                elements[0] = element;
            }
        } else if (index == size()) {
            elements[size()] = element;
        } else {
            System.arraycopy(elements, index, elements, index + 1, size() - index);
            elements[index] = element;
        }
        size++;
    }

    /**
     * Retrieves an element by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index index of element
     * @return en element
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        Objects.checkIndex(index, size());
        return (T) elements[index];
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(0);
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
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(size() - 1);
    }

    /**
     * Changes the value of array at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   position of value
     * @param element a new value
     */
    @Override
    public void set(int index, T element) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        Objects.checkIndex(index, size());
        elements[index] = element;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        Objects.checkIndex(index, size());
        T removedElement = (T) elements[index];
        if (index == size() - 1) {
            elements[index] = null;
        }else {
            System.arraycopy(elements, index + 1, elements, index, size() - index - 1);
        }
        size--;
        return removedElement;
    }

    /**
     * Checks for existing of a specific element in the list.
     *
     * @param element is element
     * @return If element exists method returns true, otherwise it returns false
     */
    @Override
    public boolean contains(T element) {
//        throw new ExerciseNotCompletedException(); // todo: implement this method
        for (int i = 0; i < size(); i++) {
            if (element.equals(elements[i])) {
                return true;
            }
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
     * @return amount of saved elements
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
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void resizeIfNeeded() {
        if (size() == elements.length) {
            int newSize = (int) (size() * 1.5) + 1;
            Object[] newElements = new Object[newSize];
            System.arraycopy(elements, 0, newElements, 0, size());
            elements = newElements;
        }
    }

}
