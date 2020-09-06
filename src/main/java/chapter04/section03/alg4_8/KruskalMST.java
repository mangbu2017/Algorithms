package chapter04.section03.alg4_8;

import chapter01.section03.alg1_3.Queue;
import chapter01.section05.UF;
import chapter02.section04.alg2_6.MinPQ;
import chapter04.section03.Edge;
import chapter04.section03.EdgeWeightedGraph;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        /**
         * 最小生成树
         */
        mst = new Queue<Edge>();
        /**
         * 用于将边根据权重进行排序
         */
        MinPQ<Edge> pq = new MinPQ<Edge>(G.E());
        for(Edge e: G.edges()) {
            pq.insert(e);
        }

        /** 是否在同一联通分量中 */
        UF uf = new UF(G.V());

        while(!pq.isEmpty() && mst.size() < G.V() - 1) { // == G.V() - 1时树已经形成了
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if(uf.connected(v, w)) /** 忽略失效边 */
                continue;

            uf.union(v, w); /** 合并分量 */
            mst.enqueue(e); /** 添加至最小生成树 */
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    // todo
    public double weight() {
        return 0;
    }
}
