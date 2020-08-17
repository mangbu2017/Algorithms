package chapter03.section01.alg3_3;

/**
 * 二叉查找树 实现 符号表
 */
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

    public Value get(Key key) {
        return get(key);
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
        return min(root).key;
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



}
