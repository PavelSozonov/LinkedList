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

    public void add(T value, int index) {
        if ((index > size) || index < 0) throw new IllegalArgumentException();

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

    private Node<T> find(int index) {
        if ((index > size - 1) || (index < 0)) throw new IllegalArgumentException();
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

    private Node<T> find(T value) {
        Node<T> currentNode = head;
        while (currentNode != null && currentNode.getValue() != value) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    private void remove(Node<T> removeNode) {
        if (removeNode == null) throw new IllegalArgumentException();
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

    public void remove(T value) {
        Node<T> removeNode = find(value);
        remove(removeNode);
    }

    public void remove(int index) {
        Node<T> removeNode = find(index);
        remove(removeNode);
    }

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
