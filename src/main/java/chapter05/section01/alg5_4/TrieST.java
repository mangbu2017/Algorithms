package chapter05.section01.alg5_4;

import chapter01.section03.alg1_3.Queue;

/**
 * 基于单词查找树的符号表
 */
public class TrieST<Value> {
    private static int R = 256;
    private Node root;
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if(x == null)
            return null;
        return (Value) x.val;
    }

    /**
     * 返回以x为根节点的子单词查找树中val=key的节点
     * @param x
     * @param key
     * @param d 当前应该比较第d个字符
     * @return
     */
    private Node get(Node x, String key, int d) {
        if(x == null)
            return null;

        if(d == key.length()) {
            return x;
        }

        char c = key.charAt(d);

        return get(x.next[c], key, d + 1);
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if(x == null)
            x = new Node();

        /** 后边没有了 正对上x为叶子节点 */
        if(d == key.length()) {
            x.val = val;
            return x;
        }

        /**
         * 找到第d个字符对应的 子单词查找树
         */
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    /**
     * 收集子树x中val=pre的值 放入q队列
     */
    private void collect(Node x, String pre, Queue<String> q) {
        if(x == null)
            return;

        if(x.val != null)
            q.enqueue(pre);
        /**
         * 遍历字符表(必是从0开始)
         */
        for(char c = 0; c < R; c ++) {
            collect(x.next[c], pre + c, q);
        }
    }

    /**
     * 增加一个模式参数
     */
    private void collect(Node x, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if(x == null)
            return;

        /**
         * ST 符号表 val = null就没有意义了
         */
        if(d == pat.length() && x.val != null) {
            q.enqueue(pre);
        }

        /**
         * 长度打到模式长度 还没有val 就可以结束了
         */
        if(d == pat.length())
            return;

        char next = pat.charAt(d);
        for(char c = 0; c < R; c ++) {
            if(next == '.' || next == c) {
                collect(x.next[c], pre + c, pat, q);
            }
        }
    }

    /**
     * 找到所有以pre开头的键
     */
    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new Queue<String>();
        /**
         * 先找到pre对应的节点
         */
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }


    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new Queue<String>();
        collect(root, "", pat, q);
        return q;
    }

    /**
     * 返回是s的前缀的字符串中 最长的键
     */
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    /**
     * d表示 当前前缀长度 or 下一字符下标
     */
    private int search(Node x, String s, int d, int length) {
        /**
         * 找到头了 没有再长的了
         */
        if(x == null)
            return length;

        /**
         * 有值 更新最长键
         */
        if(x.val != null) {
            length = d;
        }

        /**
         * 找到s本身了, 最长前缀
         */
        if(d == s.length()) {
            return length;
        }

        /**
         * 只可能有一条最长路径
         */
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length); // d+1 => 长度+1
    }

    static public void main(String args[]) {
        TrieST<Number> trie = new TrieST<Number>();
        trie.put("she", 0);
        trie.put("sells", 1);
        trie.put("seashellssssss", 2);
        trie.put("seashells", 3);
        trie.put("by", 4);
        trie.put("the", 5);
        trie.put("seashore", 6);
        trie.put("the", 7);
        trie.put("shells", 8);
        trie.put("she", 9);
        trie.put("are", 10);
        trie.put("surely", 11);
        trie.put("seashells", 12);

//        System.out.println(trie.keysWithPrefix("sh"));
        /**
         * 测试正确
         */
        for(String i:trie.keysWithPrefix("sh")) {
            System.out.println(i);
        }
        System.out.println(trie.keysWithPrefix("sh"));
        System.out.println(trie.get("she"));
        /**
         * 测试正确
         */
//        System.out.println(trie.keysThatMatch("s.a"));
        System.out.println(trie.keysThatMatch(".he"));
        /**
         * 测试正确
         */
        System.out.println(trie.longestPrefixOf("shell"));
        System.out.println(trie.longestPrefixOf("shellsort"));
    }
}
