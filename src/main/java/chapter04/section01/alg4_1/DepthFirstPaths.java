package chapter04.section01.alg4_1;

import chapter01.section03.alg1_2.Stack;
import chapter04.section01.Graph;

public class DepthFirstPaths {
    /**
     * 节点是否进行过递归
     */
    private boolean[] marked;
    /**
     * edgeTo 是一颗用 父链接表示的以s为根且含有所有与s连通的顶点的树
     */
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        this.dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for(int w: G.adj(v)) {
            /**
             * 是一个没到达过的顶点
             */
            if(!marked[w]) {
                /**
                 * 保存下到达该顶点的 前一顶点
                 */
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        /**
         * 是否被标记过
         */
        return marked[v];
    }

    public Iterable<Integer>pathTo(int v) {

        if(!hasPathTo(v))
            return null;

        Stack<Integer> path = new Stack<Integer>();
        // 反着回起点
        /**
         * 只能反着来 因为起点是没有 前一顶点 这一概念的
         */
        for(int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
