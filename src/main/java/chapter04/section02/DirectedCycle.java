package chapter04.section02;

import chapter01.section03.alg1_2.Stack;

/**
 * 寻找有向环
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    /**
     * 有向环上的所有顶点
     */
    private Stack<Integer> cycle;
    /**
     * 递归调用栈上的所有顶点 todo(无向环的判断为什么相较简单???)
     */
    private boolean[] onStack;

    public DirectedCycle(DiGraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++) {
            if(!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(DiGraph G, int v) {
        /**
         * 标记入栈
         */
        onStack[v] = true;
        /**
         * 标记访问
         */
        marked[v] = true;
        for(int w: G.adj(v)) {
            if(this.hasCycle()) // 已经发现环就不在遍历了
                return;
            else if(!marked[w]) { // 没有遍历的进行遍历
                /**
                 * w是v访问的
                 */
                edgeTo[w] = v;
                dfs(G, w);
            }else if(onStack[w]) { // 已经遍历过的 如果在栈中
                /**
                 * w早先已经访问过了
                 */
                cycle = new Stack<Integer>();
                for(int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                /** v开头 v结尾 */
                cycle.push(v);
            }
        }
        /**
         * 内部dfs结束 就表示出栈了
         */
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
