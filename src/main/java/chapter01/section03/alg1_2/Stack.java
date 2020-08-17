package chapter01.section03.alg1_2;

import java.util.Iterator;

// 链表实现
public class Stack<Item> implements Iterable<Item> {
    private Node first; // 成员变量默认初始化为 null
    private int N; // 成员变量默认初始化为 0
    private class Node {
        Item item;
        Node next;
    }
    public int size() {
        return N;
    }
    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N ++;
    }

    public Item pop() {
        Node res = first;
        first = first.next;
        N --;
        return res.item;
    }

    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {
        private Node p = first;
        public boolean hasNext() {
            return p != null;
        }
        public Item next() {
            Node res = p;
            p = p.next;
            return res.item;
        }
        public void remove() {}
    }
}
