package chapter01.section03.alg1_3;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
    }
    private Node first;
    private Node last;
    private int N;
    public boolean isEmpty() {
        return first == null;
//        return N == 0;
    }
    public int size() {
        return N;
    }
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if(isEmpty())
            first = last;
        else
            oldLast.next = last;

        N++;
    }
    public Item dequeue() {
        Item res = first.item;

        first = first.next;

        // first = last = oneNode{next = null}
        if(isEmpty()) {// 这种判断只有在 通过 first == null 判断空队列时才有效
            last = null;
        }

        N--;

        return res;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    @Override
    public String toString() {
        String s = "****** queue N: " + N + "******";
        for(Iterator<Item> it = iterator(); it.hasNext(); ) {
            s += "\n";
            System.out.println("xjfekjsf");
            Item i = it.next();
            System.out.println(i);
            s += i;
        }
        s += "\n******";
        return s;
    }

    private class QueueIterator implements Iterator<Item> {
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
