package chapter01.section03.alg1_1;

import java.util.Iterator;

// 固定长度的数组实现
public class ResizingArrayStack<T> implements Iterable<T> {
    private T[] a = (T[]) new Object[1];
    private int N = 0; // 当前栈的长度 也就是将要压入栈的元素的下标

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        T[] temp = (T[]) new Object[max];
        for(int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(T item) {
        if(N == a.length)
            this.resize(2 * a.length);

        a[N++] = item;
    }

    public T pop() {
        T item = a[--N];
        a[N] = null;
        if(N > 0 && N == a.length / 4)
            this.resize(a.length / 2);

        return item;
    }

    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int i = N;
        public boolean hasNext() {
            return i > 0;
        }
        public T next() {
            return a[--i];
        }
        public void remove() {}
    }
}
