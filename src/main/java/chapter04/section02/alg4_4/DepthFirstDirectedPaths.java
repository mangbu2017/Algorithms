package chapter04.section02.alg4_4;

import chapter01.section03.alg1_4.Bag;
import chapter04.section02.DiGraph;
import edu.princeton.cs.algs4.In;

/**
 * 有向图路径 (是否可达)
 */
public class DepthFirstDirectedPaths {
    private boolean[] marked;

    /**
     * 根据一个起点 深度遍历 初始化
     */
    public DepthFirstDirectedPaths(DiGraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    /**
     * 根据多个起点 深度遍历 初始化
     */
    public DepthFirstDirectedPaths(DiGraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for(int s: sources) {
            if(!marked[s])
                dfs(G, s);
        }
    }

    private void dfs(DiGraph G, int v) {
        marked[v] = true;
        for(int w: G.adj(v)) {
            if(!marked[w])
                dfs(G, w);
        }
    }

    /**
     * 是否可达
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * 正确
     */
    public static void main(String[] args) {
        DiGraph G = new DiGraph(new In("files/tinyDG"));

        Bag<Integer> sources = new Bag<Integer>();
//        for(int i = 1; i < args.length; i ++) {
//
//        }
        sources.add(1);
        sources.add(2);
        sources.add(6);

        DepthFirstDirectedPaths reachable = new DepthFirstDirectedPaths(G, sources);

        for(int v = 0; v < G.V(); v ++) {
            if(reachable.marked(v))
                System.out.print(v + " ");
        }
        System.out.println();
    }
}
