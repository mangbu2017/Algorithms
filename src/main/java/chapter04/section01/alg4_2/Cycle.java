package chapter04.section01.alg4_2;

import chapter04.section01.Graph;

/**
 * 是否存在环(存在自环或平行边的简单图)
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++) {
            if(!marked[v])
                dfs(G, v, v);
        }
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        /**
         * 有环则说明
         */
        for(int w: G.adj(v)) {
            if(!marked[w])
                dfs(G, w, v);
            else if(w != u)
                hasCycle = true;
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
