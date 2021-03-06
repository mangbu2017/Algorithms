package chapter03.section03.alg3_4;

import chapter01.section03.alg1_3.Queue;
import chapter03.section01.alg3_3.BST;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private static final boolean RED = true;
    private static final boolean BlACK = false;
    private class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color;

        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if(x == null)
            return false;
        return x.color == RED;
    }

    /**
     * 誊抄时容易出现bug
     */
    Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    void flipColors(Node h) {
        h.color = RED;
        h.left.color = BlACK;
        h.right.color = BlACK;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        /**
         * todo ???
         */
        root.color = BlACK;
    }

    private Node put(Node x, Key key, Value val) {
        if(x == null) {
            /** 新插入的都是red */
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(x.key);

        if(cmp < 0) {
            x.left = put(x.left, key, val);
        }else if(cmp > 0) {
            x.right = put(x.right, key, val);
        }else {
            x.val = val;
        }

        /** add start */
        /** 右边红 左旋转 */
        if(isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        /** 连续两个左红 上面那个右旋转 */
        if(isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        /** 左右都是红 改变颜色 */
        if(isRed(x.left) && isRed(x.right))
            flipColors(x);
        /** add end */

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * todo 验证
     */
    public boolean contains(Key key) {
        return get(key) != null;
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

    /** 删除最小键 start todo 没理解??? */
    private void flipColors2(Node h) {
        h.color = BlACK;
        h.left.color = RED;
        h.right.color = RED;
    }

    private Node moveRedLeft(Node h) {
        flipColors2(h);

        /** todo 如果不是 就啥也不干 ??? */
        if(isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    private Node balance(Node x) {
        if(isRed(x.right))
            x = rotateLeft(x);

        if(isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if(isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if(isRed(x.left) && isRed(x.right))
            flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node deleteMin(Node h) {
        if(h.left == null) {
            return null;
        }

        /** 左链接不是红色(左孩子不是3-node) */
        if(!isRed(h.left) && !isRed(h.left.left)) {
            /** 从右面借一个过来 */
            h = moveRedLeft(h);
        }

        h.left = deleteMin(h.left);
        return balance(h);
    }

    public void deleteMin() {
        if(!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = deleteMin(root);

        if(!isEmpty()) {
            root.color = BlACK;
        }
    }
    /** 删除最小键 end */

    public Key min() {
        return min(root).key;
    }
    public Key max() {
        return max(root).key;
    }

    private Node min(Node x) {
        if(x.left == null)
            return x;
        return min(x.left);
    }

    private Node max(Node x) {
        if(x.right == null)
            return x;
        return max(x.right);
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

}
