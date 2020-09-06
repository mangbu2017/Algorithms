package chapter04.section03.alg4_7;

import chapter01.section03.alg1_3.Queue;
import chapter02.section04.alg2_6.IndexMinPQ;
import chapter04.section03.Edge;
import chapter04.section03.EdgeWeightedGraph;

/**
 * Prim算法(即时实现)
 * 尝试从优先队列中删除 失效边
 * 优先队列中只含有 树顶点和非树顶点 之间的 横切边
 */
public class PrimMST {
    /**
     * 距离树最近的边
     */
    private Edge[] edgeTo;
    /**
     * 最近边的权重
     * distTo[w] = edge[w].weight()
     */
    private double[] distTo;
    /**
     * 标识顶点是否在树中
     */
    private boolean[] marked;
    /**
     * 有效的横切边(索引优先队列支持删除指定下标元素)
     * 索引存储顶点 值存储权重
     */
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];

        /** 初始化所有权重为无穷大 */
        for(int v = 0; v < G.V(); v ++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        pq = new IndexMinPQ<Double>(G.V());

        /** 起始顶点0本身就在树中 距离树的距离就是0 */
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while(!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;

        for(Edge e: G.adj(v)) {
            int w = e.other(v);

            if(marked[w]) /** v-w失效 */
                continue;

            if(e.weight() < distTo[w]) {/** w连接到树的权重有更小的 */
                /** 更新更优数据 */
                edgeTo[w] = e;
                distTo[w] = e.weight();

                if(pq.contains(w))
                    pq.change(w, distTo[w]);
                else
                    pq.insert(w, distTo[w]);
            }
        }
    }

    // todo
    public Iterable<Edge> edges() {
        return new Queue<>();
    }

    // todo
    public double weight() {
        return 0;
    }
}
