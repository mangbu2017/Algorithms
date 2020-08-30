package chapter04.section02;

import chapter01.section03.alg1_2.Stack;
import chapter01.section03.alg1_3.Queue;

public class DeepFirstOrder {
    private boolean[] marked;
    /**
     * 所有顶点的前序排列
     */
    private Queue<Integer> pre;
    /**
     * 后序排列
     */
    private Queue<Integer> post;
    /**
     * 逆后序排列
     */
    private Stack<Integer> reversePost;

    public DeepFirstOrder(DiGraph G) {
        pre = new Queue<Integer>();
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        marked = new boolean[G.V()];

        for(int v = 0; v < G.V(); v ++) {
            if(!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(DiGraph G, int v) {
        pre.enqueue(v);
        marked[v] = true;
        for(int w: G.adj(v)) {
            if(!marked[w])
                dfs(G, w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
