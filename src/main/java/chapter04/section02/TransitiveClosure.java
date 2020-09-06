package chapter04.section02;

import chapter04.section02.alg4_4.DepthFirstDirectedPaths;

/**
 * 顶点对 可达性
 */
public class TransitiveClosure {
    private DepthFirstDirectedPaths[] all;

    TransitiveClosure(DiGraph G) {
        all = new DepthFirstDirectedPaths[G.V()];

        for(int v = 0; v < G.V(); v ++) {
            all[v] = new DepthFirstDirectedPaths(G, v);
        }

    }

    /**
     * v是否可达w
     */
    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}
