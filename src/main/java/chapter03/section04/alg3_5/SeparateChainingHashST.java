package chapter03.section04.alg3_5;

import chapter03.section01.alg3_1.SequentialSearchST;
import chapter03.section04.alg3_6.LinearProbingHashST;

/**
 * 单独 链接 哈希 符号表
 * 拉链法
 */
public class SeparateChainingHashST<Key, Value> {
    private int N;
    private int M;
    /**
     * 存放链表对象的数组
     */
    private SequentialSearchST<Key, Value>[] st;
    public SeparateChainingHashST() {
        this(997);
    }
    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for(int i = 0; i < M; i ++) {
            st[i] = new SequentialSearchST();
        }
    }

    // todo
//    private void resize(int cap) {
//        SeparateChainingHashST<Key, Value> t;
//        t = new SeparateChainingHashST<Key, Value>(cap);
//        for(int i = 0; i < M; i ++) {
//            if(SeparateChainingHashST[i])
//                t.put(keys[i], vals[i]);
//        }
//        keys = t.keys;
//        vals = t.vals;
//        M = t.M;
//    }

    private int hash(Key key) {
        // 2^(4*8 = 32)  相当于 31位1 第32位为0
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }

    public void delete(Key key) {
        st[hash(key)].delete(key);
    }

//    public Iterable<Key> keys()
}
