package chapter04.section04.alg4_9;

import chapter01.section03.alg1_2.Stack;
import chapter02.section04.alg2_6.IndexMinPQ;
import chapter04.section04.DijkstraAllPairsSP;
import chapter04.section04.DirectedEdge;
import chapter04.section04.EdgeWeightedDiGraph;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

public class DijkstraSP {
    /**
     * 父链接数组(存储的值为发现该顶点的 顶点)
     */
    DirectedEdge edgeTo[];
    /**
     * 从s(起点)到达[v]的最短路径的长度
     */
    double disTo[];
    /**
     * 顶点: 权重(优先级)
     */
    private IndexMinPQ<Double> pq;
//    edu.princeton.cs.algs4.IndexMinPQ

    public DijkstraSP(EdgeWeightedDiGraph G, int s) {
        pq = new IndexMinPQ<Double>(G.V());
        edgeTo = new DirectedEdge[G.V()];
        disTo = new double[G.V()];
        /**
         * 起点的disTo为0 不可达顶点的disTo始终为infinity
         */
        for(int i = 0; i < G.V(); i ++) {
            disTo[i] = Double.POSITIVE_INFINITY;
        }
        disTo[s] = 0.0;

        pq.insert(s, 0.0);
        while(!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    /**
     * 顶点的放松(顶点所有边都放松一次)
     * 深度优先
     */
    private void relax(EdgeWeightedDiGraph G, int v) {
        for(DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if(disTo[w] > disTo[v] + e.weight()) {
                /** 更新树 */
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;

                /** 更新队列 */
                if(pq.contains(w))
                    pq.change(w, disTo[w]);
                else
                    pq.insert(w, disTo[w]);
            }
        }
    }

    public double disTo(int v) {
        return disTo[v];
    }

    public boolean hasPathTo(int v) {
        /**
         * 使用infinity 代替之前的mark[i]=false
         */
        return disTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if(!hasPathTo(v))
            return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    /**
     * 正确
     */
    static public void main(String[] args) {
//        EdgeWeightedDiGraph g = new EdgeWeightedDiGraph(new In("files/mediumEWD.txt"));
        EdgeWeightedDiGraph g = new EdgeWeightedDiGraph(new In("files/tinyEWD"));
        System.out.println(g);

        DijkstraSP sp = new DijkstraSP(g, 0);

        for(int t = 0; t < g.V(); t ++) {
            System.out.print(0 + " to " + t);
            System.out.printf(" (%4.2f): ", sp.disTo(t));
            if(sp.hasPathTo(t))
                for(DirectedEdge e: sp.pathTo(t))
                    System.out.print(e + "  ");
            System.out.println();
        }

//        DijkstraAllPairsSP allSp = new DijkstraAllPairsSP(g);
//        System.out.println(allSp.path(0, 1));
    }
}
