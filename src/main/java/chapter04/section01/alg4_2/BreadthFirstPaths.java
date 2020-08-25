package chapter04.section01.alg4_2;

import chapter01.section03.alg1_2.Stack;
import chapter01.section03.alg1_3.Queue;
import chapter04.section01.Graph;

public class BreadthFirstPaths {
    /**
     * 节点是否被访问过
     */
    private boolean[] marked;
    /**
     * edgeTo 是一颗用 父链接表示的以s为根且含有所有与s连通的顶点的树
     */
    private int[] edgeTo;
    private final int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        this.bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<Integer>();
        /**
         * 先标记起点
         */
        marked[s] = true;
        queue.enqueue(s);

        /**
         * 队列的使用使其遍历出现层次
         * 从而方便查找最短路径
         */
        while(!queue.isEmpty()) {
            int v = queue.dequeue();
            for(int w: G.adj(v)) {
                if(!marked[w]) {
                    /**
                     * 赋值邻接表中的顶点的上一节点为v
                     */
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
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
