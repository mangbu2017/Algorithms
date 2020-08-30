package chapter04.section01.alg4_2;

import chapter04.section01.Graph;

public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++) {
            if(!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for(int w: G.adj(v)) {
            if(!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            }else if(color[w] == color[v]) {
                /**
                 * 已经被遍历过的是不会再经颜色改变的
                 * 所以两个颜色相同 切不会再变色的 相连顶点 说明 图不是二分图
                 */
                isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }
}
