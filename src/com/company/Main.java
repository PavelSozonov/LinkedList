package com.company;

public class Main {

    public static void main(String[] args) {
        LinkedList<Long> list = new LinkedList<>();
        list.add(5L);
        list.add(10L);
        list.add(1L, 1);
        list.add(9L);
        list.add(100L, 0);
        list.remove(0);
        list.remove(3);
        list.remove(2);
        System.out.println(String.format("Contains: %d, %s", 5, list.contains(5L)));
        System.out.println(String.format("Contains: %d, %s", 10, list.contains(10L)));
        System.out.println(String.format("Contains: %d, %s", 9, list.contains(9L)));
        System.out.println(String.format("Size: %s", list.size()));
        System.out.println("Values:");
        for (Long value : list) System.out.println(value);
    }
}
