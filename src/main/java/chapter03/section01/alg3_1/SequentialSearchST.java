package chapter03.section01.alg3_1;

import org.junit.Test;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * 无序数组实现 符号表
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key, Value> implements Iterable<Key> {
    private Node first;

    @Override
    public Iterator<Key> iterator() {
        return new SequentialSearchSTIterator();
    }

//    @Override
//    public void forEach(Consumer<? super Key> action) {
//
//    }


    private class Node {
        Key key;
        Value value;
        Node next;
        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for(Node p = first; p != null; p = p.next) {
            if(key.equals(p.key))
                return p.value;
        }
        return null;
    }

    public void put(Key key, Value value) {
        for(Node p = first; p != null; p = p.next) {
            if(key.equals(p.key)) {
                p.value = value;
                return;
            }
        }

        first = new Node(key, value, first);
    }

    public int size() {
        int s = 0;
        for(Node p = first; p != null; p = p.next) {
            s ++;
        }
        return s;
    }

    public void delete(Key key) {
        if(first == null)
            return;

        if(key.equals(first.key)) {
            first = first.next;
            return;
        }

        Node q = null;
        for(Node p = first; p != null; p = p.next) {
            if(key.equals(p.key)) {
                q.next = p.next;
                break;
            }
            q = p;
        }
    }

    private class SequentialSearchSTIterator implements Iterator<Key> {
        private Node p = first;

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public Key next() {
            Key key = p.key;
            p = p.next;
            return key;
        }
    }

    // todo keys返回键的集合没写明白+不知道咋用
    public Iterator<Key> keys() {
        return new SequentialSearchSTIterator();
    }

    @Override
    public String toString() {
        String str = "";

        for(Node p = first; p != null; p = p.next) {
            if(p == first) {
                str += p.key + ":" + p.value;
            }else {
                str += " => " + p.key + ":" + p.value;
            }
        }

        return str;
    }

    @Test
    public void demo1() {
        SequentialSearchST st = new SequentialSearchST<String, Integer>();

        st.put("aaa", 111);
        st.put("bbb", 222);
        st.put("ccc", 333);
        System.out.println(st);
        st.delete("bbb");
        System.out.println(st);
        st.put("aaa", 110);
        System.out.println(st);
        st.put("ddd", 555);
        System.out.println(st);
        System.out.println("XXXXX");
        for(Object p: st) {
            System.out.println(p);
        }

        Iterator it = st.keys();
        String key = "";
        System.out.println(it);
//        for(; keys.hasNext(); keys = )
    }

    @Test
    public void test1() {
        System.out.println(5 / 2);
    }
}
