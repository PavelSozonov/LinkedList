package com.company;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by pavel on 07.06.17.
 */
public class LinkedListTest {

    private LinkedList<Integer> testList = new LinkedList<>();

    @Test
    public void sizeTest1() {
        testList.add(10);
        assertEquals(1, testList.size());
    }

    @Test
    public void sizeTest2() {
        testList.add(10);
        testList.add(100);
        assertEquals(2, testList.size());
    }

    @Test
    public void sizeTest3() {
        testList.add(10);
        testList.add(100);
        testList.add(1000);
        assertEquals(3, testList.size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void argumentTest1() {
        testList.add(1, -1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void argumentTest2() {
        testList.add(1, 1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void argumentTest3() {
        testList.remove(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void argumentTest4() {
        testList.remove(new Integer(5));
    }

    @Test
    public void iteratorTest() {
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        Iterator<Integer> iterator = testList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals((Integer)1, (Integer)iterator.next());
        assertEquals((Integer)2, (Integer)iterator.next());
        assertEquals((Integer)3, (Integer)iterator.next());
        assertEquals((Integer)4, (Integer)iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void removeFirstTest() {
        testList.add(1);
        testList.add(2);
        testList.remove(0);
        assertEquals(1, testList.size());
        assertTrue(testList.contains(2));
        assertFalse(testList.contains(1));
    }

    @Test
    public void removeLastTest() {
        testList.add(1);
        testList.add(2);
        testList.remove(1);
        assertEquals(1, testList.size());
        assertTrue(testList.contains(1));
        assertFalse(testList.contains(2));
    }

    @Test
    public void removeFromCenterTest() {
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.remove(1);
        assertEquals(2, testList.size());
        assertTrue(testList.contains(1));
        assertFalse(testList.contains(2));
        assertTrue(testList.contains(3));
    }
}