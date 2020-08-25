package chapter03.section01.alg3_3;

/**
 * 二叉查找树 实现 符号表
 */

import chapter01.section03.alg1_3.Queue;

/** Key有一个泛型约束 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        /**
         * 已该节点为根的子树中的节点数量
         */
        private int N;
        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if(x == null) {
            return 0;
        }

        return x.N;
    }

    public boolean isEmpty() {
        if(root != null)
            return root.N == 0;
        return true;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if(x == null)
            return null;

        int cmp = key.compareTo(x.key);

        if(cmp < 0) {
            return get(x.left, key);
        }else if(cmp > 0) {
            return get(x.right, key);
        }else {
            return x.val;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    /**
     * 如果key存在则修改 不存在则插入
     * @param x
     * @param key
     * @param val
     * @return
     */
    private Node put(Node x, Key key, Value val) {
        if(x == null) {
            return new Node(key, val, 1);
        }

        int cmp = key.compareTo(x.key);

        /**
         * 不会卡在中间 一定会走到叶子节点
         */
        if(cmp < 0) {
            x.left = put(x.left, key, val);
        }else if(cmp > 0) {
            x.right = put(x.right, key, val);
        }else {
            x.val = val;
        }

        x.N = size(x.left) + size(x.right) + 1;

        /**
         * 无论找到与否x都会被返回
         */
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if(x.left == null)
            return x;
        return min(x.left);
    }

    private Node max(Node x) {
        if(x.right == null)
            return x;
        return min(x.right);
    }

    public Key max() {
        return max(root).key;
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if(x == null)
            return null;
        return x.key;
    }

    public Node floor(Node x, Key key) {
        if(x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);

        if(cmp == 0) {
            return x;
        }

        if(cmp < 0) {
            // 在左子树中找
            return floor(x.left, key);
        }

        // 在右子树中找
        Node t = floor(x.right, key);

        /**
         * 右子树中有就是他 没有就是根节点
         */
        if(t != null)
            return t;
        else
            return x;
    }


    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if(x == null)
            return null;
        return x.key;
    }

    public Node ceiling(Node x, Key key) {
        if(x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if(cmp == 0) {
            return x;
        }

        if(cmp > 0) {
            return floor(x.right, key);
        }

        Node t = floor(x.left, key);

        if(t != null)
            return t;
        else
            return x;
    }

    /**
     * 查找排名为k的元素并返回
     * @param k
     * @return
     */
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if(x == null)
            return null;

        /** 左子树中节点的数量(根节点的排名) */
        int t = size(x.left);

        /**
         * 如果k的排名在t之前 就在左子树中找
         */
        if(k < t) {
            return select(x.left, k);
        }else if(k == t) {
            return x;
        }else {
            return select(x.right, k - t - 1);
        }
    }

    /**
     * 查找对应key对应的排名并返回
     * @param key
     * @return
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    /** 返回以x为根节点的子树中 小于key的节点的数量 */
    private int rank(Node x, Key key) {
        if(x == null)
            return 0;

        int cmp = key.compareTo(x.key);

        if(cmp < 0) {
            return rank(x.left, key);
        }else if(cmp > 0) {
            return size(x.left) + 1 + rank(x.right, key);
        }else {
            return size(x.left);
        }
    }

    /** %%%BST中最难实现的就是delete操作%%% */

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if(x.left == null)
            return x.right;

        x.left = deleteMin(x.left);
        /** 从最低层开始往上更新每个节点的N */
        x.N = size(x.left) + 1 + size(x.right);
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if(x == null)
            return null;

        int cmp = key.compareTo(x.key);

        if(cmp < 0)
            x.left = delete(x.left, key);
        else if(cmp > 0)
            x.right = delete(x.right, key);
        else {
            if(x.right == null) {
                return x.left;
            }
            if(x.left == null) {
                return x.right;
            }
            /** 左右子树都有 */
            Node t = x;
            x = min(t.right); // 右子树中最小值
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        /**
         * 这种看似麻烦的方法的好处:
         * 1. 不用判断是否找到了(对比父级依次-1)
         */
        x.N = size(x.left) + 1 + size(x.right);
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key start, Key end) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, start, end);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key start, Key end) {
        if(x == null)
            return;

        int cmpStart = start.compareTo(x.key);
        int cmpEnd = end.compareTo(x.key);

        /**
         * 保证次序采用 中序遍历 左 => 根 => 右
         */
        if(cmpStart < 0) // 在左边界右侧 => 左子树要一直遍历完
            keys(x.left, queue, start, end);
        if(cmpStart <= 0 && cmpEnd >= 0) // 在开始遍历根
            queue.enqueue(x.key);
        if(cmpEnd > 0) // 最后遍历右子树
            keys(x.right, queue, start, end);
        /**
         * 相较于正常的中序遍历 只是增加了限制条件
         */
    }

    @Override
    public String toString() {
        String res = "";
        for(Key key: keys()) {
            res += key + ":" + get(key) + ",";
        }

        return res;
    }
}
