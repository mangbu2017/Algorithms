package chapter03.section01.alg3_2;

import chapter01.section03.alg1_3.Queue;
import org.junit.Test;

import java.util.Arrays;

/**
 * 有序索引数组+二分查找 实现 符号表
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int i) {
        return keys[i];
    }

    public boolean contains(Key key) {
        int i = rank(key);
        /**
         * start的初始值为0 且永远不会变小
         */
        if(i < N && keys[i].compareTo(key) == 0)
            return true;
        return false;
    }

    /**
     * @param key
     * @return 大于等于key的最小键
     */
    public Key ceiling(Key key) {
        // 应该插入的位置
        int i = rank(key);
        return keys[i];
    }

    /**
     * @param key
     * @return 小于等于key的最大键
     */
    public Key floor(Key key) {
        int i = rank(key);

        /**
         * 有大于等于它的键
         * %%% 因为还可能出现 rank返回 i == N，即新增最大元素的情况 %%%
         */
        if(i < N) {
            if(key.compareTo(keys[i]) == 0)
                return key;

            if(i - 1 >= 0) {
                return keys[i - 1];
            }
        }else {
            return keys[size() - 1];
        }

        return null;
    }

    public Value get(Key key) {
        if(isEmpty())
            return null;

        int i = rank(key);
        // 无论是否命中 都会返回一个位置下标 所以需要额外判断
        if(i < N && keys[i].compareTo(key) == 0)
            return values[i];

        return null;
    }

    public void put(Key key, Value val) {
        /**
         * 应该插入的位置
         */
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) {
            values[i] = val;
            return;
        }

        for(int j = N; j > i; j --) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }

        keys[i] = key;
        values[i] = val;

        N ++;
    }

    public void delete(Key key) {
        int i = rank(key);

        if(i < N && key.compareTo(keys[i]) == 0) {
            for(int j = i; j < N - 1; j ++) {
                keys[j] = keys[j + 1];
                values[j] = values[j + 1];
            }

            keys[N - 1] = null;
            values[N - 1] = null;

            N --;
        }
    }

    /**
     *
     * @param start [start,
     * @param end    end]
     * @return 返回一个可遍历的队列
     */
    public Iterable<Key> keys(Key start, Key end) {
        Queue<Key> q = new Queue<Key>();

        int endIdx = rank(end);

        // fixme 没有对start end是否存在进行校验
        for(int i = rank(start); i < endIdx; i ++) {
            q.enqueue(keys[i]);
        }

        /**
         * for循环没有包含 end下标
         */
        if(contains(end)) {
            q.enqueue(keys[endIdx]);
        }

        return q;
    }

    /**
     * rank的非递归版本
     * @param key
     * @return 找到第一个大于等于key的元素下标
     */
    private int rank(Key key) {
        int start = 0, end = N - 1;

        while(start <= end) {
            int mid = (end + start) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0)
                end = mid - 1;
            else if(cmp > 0)
                start = mid + 1;
            else
                return mid;
        }

        return start;
    }

    @Override
    public String toString() {
//        return "BinarySearchST{" +
//                "keys=" + Arrays.toString(keys) +
//                ", values=" + Arrays.toString(values) +
//                ", N=" + N +
//                '}';

        String res = "";
        for(Key key: keys(min(), max())) {
            res += key + ":" + get( key) + ", ";
        }
        res += "size: " + size();
        return res;
    }
}
