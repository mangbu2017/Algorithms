package chapter02.section04.alg2_6;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq; // 完全二叉树
    private int N = 0;

    public MinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++ N] = v;
        swim(N);
    }

    public Key delMin() {
        Key max = pq[1];
        exch(1, N --);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     *  %%%最大优先和最小优先只有这里不一样%%%
     */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }
    private void swim(int k) {
        while(k > 1 && less(k / 2, k)) {
            exch(k , k / 2);
            k = k / 2;
        }
    }
    private void sink(int k) {
        while(k * 2 <= N) { // 左孩子
            int l = 2 * k;
            // 有j+1 && j+1 还比j大
            if(l < N && less(l, l + 1))
                l ++; // 设置为j+1

            // l >= k 大于等于孩子中大的 就结束
            if(!less(k, l))
                break;

            exch(k, l);

            k = l;
        }
    }
}