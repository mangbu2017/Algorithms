package chapter04.section01;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];

    }

    private void dfs(Graph G, int v) {
        /**
         * 标记起点
         */
        marked[v] = true;
        count ++;
        for(int w: G.adj(v))
            if(!marked[w])
                dfs(G, w);
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
