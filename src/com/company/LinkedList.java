package com.company;

import java.util.Iterator;

/**
 * Created by pavel on 07.06.17.
 */
public class LinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public int size() {
        return size;
    }

    // Add in the end
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    // Add by index
    // (if index == 0 it's new head, if index == size it's new tail)
    public void add(T value, int index) {
        if ((index > size) || index < 0) throw new IllegalArgumentException("The index out of boundaries");

        if (index == size) {
            add(value);
        } else {
            Node<T> newNode = new Node<>(value);

            Node<T> insertBeforeThisNode = find(index);
            newNode.setNext(insertBeforeThisNode);

            Node<T> prev = insertBeforeThisNode.getPrev();

            if (prev == null) {
                head = newNode;
            } else {
                prev.setNext(newNode);
                newNode.setPrev(prev);
            }
            insertBeforeThisNode.setPrev(newNode);
            size++;
        }
    }

    // Find node by index
    private Node<T> find(int index) {
        if ((index > size - 1) || (index < 0)) throw new IllegalArgumentException("Index out of boundaries");
        int currentIndex;
        Node<T> currentNode;
        if (index < (size - 1) / 2) { // Search from head
            currentIndex = 0;
            currentNode = head;
            while (currentIndex < index) {
                currentNode = currentNode.getNext();
                currentIndex++;
            }
        } else { // Search from tail
            currentIndex = size - 1;
            currentNode = tail;
            while (currentIndex > index) {
                currentNode = currentNode.getPrev();
                currentIndex--;
            }
        }
        return currentNode;
    }

    // Find node by value
    private Node<T> find(T value) {
        Node<T> currentNode = head;
        while (currentNode != null && currentNode.getValue() != value) {
            currentNode = currentNode.getNext();
        }
        if (currentNode == null || currentNode.getValue() != value) return null;
        return currentNode;
    }

    // Remove node, it must exist, and it must be the node of this list
    private void remove(Node<T> removeNode) {
        if (removeNode == null) throw new IllegalArgumentException("The argument equals null");
        Node<T> prev = removeNode.getPrev();
        Node<T> next = removeNode.getNext();
        if (prev != null) prev.setNext(next);
        if (next != null) next.setPrev(prev);
        if (removeNode.equals(head)) {
            head = removeNode.getNext();
        }
        if (removeNode.equals(tail)) {
            tail = removeNode.getPrev();
        }
        size--;
    }

    // Remove by value
    public void remove(T value) {
        Node<T> removeNode = find(value);
        if (removeNode == null) throw new IllegalArgumentException("The value does not exist");
        remove(removeNode);
    }

    // Remove by index
    public void remove(int index) {
        Node<T> removeNode = find(index);
        remove(removeNode);
    }

    // Check if list contains value
    public boolean contains(T value) {
        Node<T> searchedNode = find(value);
        if (searchedNode != null) return true;
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return find(index++).getValue();
            }
        };

        return iterator;
    }
}
