package chapter04.section04;

import chapter04.section04.alg4_9.DijkstraSP;

/**
 * 迪杰斯特拉算法
 * 找出顶点对间的最短路径
 */
public class DijkstraAllPairsSP {
    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDiGraph G) {
        all = new DijkstraSP[G.V()];
        for(int v = 0; v < G.V(); v ++) {
            all[v] = new DijkstraSP(G, v);
        }
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    public double dist(int s, int t) {
        return all[s].disTo(t);
    }
}
