package chapter02.section04.alg2_6;

/**
 * 允许访问已经插入优先队列的元素
 */
public class IndexMinPQ<Key extends Comparable<Key>> {
    private int N;
    /**
     * 索引数组(实际的二叉堆)
     * 新位置 => 老位置
     */
    private int[] pq;
    /**
     * 索引数组的逆序
     * 老位置 => 新位置
     */
    private int[] qp;
    private Key[] keys;

    public IndexMinPQ(int maxN) {
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1]; // 索引二叉树
        qp = new int[maxN + 1]; // 逆序
        /** 二叉堆还没有开始构造, 老位置在pq上还没有对应的值 */
        for(int i = 0; i <= maxN; i ++)
            qp[i] = -1;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    /**
     * 将第k个元素赋值为key
     * @param k
     * @param key
     */
    public void insert(int k, Key key) {
        N ++;
        qp[k] = N; // qp保存了第k个元素
        pq[N] = k;
        keys[k] = key; // 这里相当于只是一个Map
        swim(N);
    }

    public Key min() {
        return keys[pq[1]];
    }

    public int delMin() {
        int indexOfMin = pq[1];
        exch(1, N --);
        sink(1);
        // 元素组中有一位是null了 但是没啥影响
        // 因为其在qp中的值也是-1了
        keys[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;
        return indexOfMin;
    }

    public int minIndex() {
        return pq[1];
    }

    public void change(int k, Key key) {
        keys[k] = key;
        sink(qp[k]); // 通过qp获取老位置对应的新位置
        swim(qp[k]);
    }

    public void delete(int k) {
        int index = qp[k];
        exch(index, N --);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    /**
     * 这里需要交换的就是 index 了
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     *  %%% 最大优先和最小优先只有这里不一样 %%%
     *  %%% 获取索引在原数组对应的值进行比较 %%%
     */
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
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
