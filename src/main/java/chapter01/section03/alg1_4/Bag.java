package chapter01.section03.alg1_4;

import chapter01.section03.alg1_2.Stack;
import chapter01.section03.alg1_3.Queue;

import java.util.Iterator;
import java.util.function.Consumer;

public class Bag<T> implements Iterable<T> {
    private class Node {
        T item;
        Node next;
    }
    private Node first;
    private int N;

    @Override
    public Iterator<T> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<T> {
        private Node p = first;
        public boolean hasNext() {
            return p != null;
        }
        public T next() {
            Node res = p;
            p = p.next;
            return res.item;
        }
        public void remove() {}
    }

    /**
     * Bag没有 删除数据的方法
     * @param item
     */
    public void add(T item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N ++;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }
}
