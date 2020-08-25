package chapter03.section04.alg3_6;

/**
 * 线性探测法
 */
public class LinearProbingHashST<Key, Value> {
    private int N;
    private int M = 16; // 线性探测表的大小
    private Key[] keys;
    private Value[] vals;
    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }
    public LinearProbingHashST(int cap) {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
        this.M = cap;
    }

    private int hash(Key key) {
        // 2^(4*8 = 32)  相当于 31位1 第32位为0
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Key key, Value val) {

        // todo 没有null校验

        if(N >= M / 2)
            resize(2 * M);

        int i;
        for(i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if(keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N ++;
    }

    public Value get(Key key) {
        for(int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if(keys[i].equals(key))
                return vals[i];
        }
        return null;
    }

    public boolean contains(Key key) {
        for(int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if(keys[i].equals(key))
                return true;
        }
        return false;
    }

    public void delete(Key key) {
        if(!contains(key))
            return;

        int i = hash(key);

        while(!key.equals(keys[i]))
            i = (i + 1) % M;

        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while(keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N --;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }

        N --;

        if(N > 0 && N == M / 8)
            resize(M / 2);
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);
        for(int i = 0; i < M; i ++) {
            if(keys[i] != null)
                t.put(keys[i], vals[i]);
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }



}
