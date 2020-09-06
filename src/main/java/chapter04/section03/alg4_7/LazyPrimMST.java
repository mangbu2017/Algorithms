package chapter04.section03.alg4_7;

import chapter01.section03.alg1_3.Queue;
import chapter02.section04.alg2_6.MinPQ;
import chapter04.section03.Edge;
import chapter04.section03.EdgeWeightedGraph;

/**
 * Prim算法(延时实现)
 */
public class LazyPrimMST {
    /**
     * 最小生成树的顶点
     */
    private boolean[] marked;
    /**
     * 最小生成树的边
     */
    private Queue<Edge> mst;
    /**
     * 横切边优先队列(包含失效边)
     */
    private MinPQ<Edge> pq;
    public LazyPrimMST(EdgeWeightedGraph G) {
        /**
         * todo MinPQ实现大小动态调整
         * ??? 大小不会超过G的边数
         */
        pq = new MinPQ<Edge>(G.E());
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();

        visit(G, 0); /** 默认0为根节点 */
        while(!pq.isEmpty())  {
            Edge e = pq.delMin();
            int v = e.either(),
                w = e.other(v);

            if(marked[v] && marked[w]) /** 跳过失效的边 */
                continue;
            mst.enqueue(e); /** 将边加入树中 */

            /** 这里是分不清是 v已经在树中还是w已经在树中的 */
            if(!marked[v])
                visit(G, v);
            if(!marked[w])
                visit(G, w);
        }
    }

    /**
     * 1. 标记顶点v
     * 2. 将所有 连接v 与 未被标记顶点 的边 加入pq
     */
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        /** 还是深度优先遍历 */
        for(Edge e: G.adj(v)) {
            if(!marked[e.other(v)])
                pq.insert(e);
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
