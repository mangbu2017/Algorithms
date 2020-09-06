package chapter04.section04;

import chapter01.section03.alg1_2.Stack;

public class SP {
    /**
     * 父链接数组(存储的值为发现该顶点的 顶点)
     */
    DirectedEdge edgeTo[];
    /**
     * 从s(起点)到达[v]的最短路径的长度
     */
    double disTo[];

    // todo
    public SP(EdgeWeightedDiGraph G) {
        disTo = new double[G.V()];
        disTo[0] = 0;
        /**
         * 起点的disTo为0 不可达顶点的disTo始终为infinity
         */
        for(int i = 0; i < G.V(); i ++) {
            disTo[i] = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * 边的放松
     */
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        /**
         * 通过v可以进行一次 放松
         */
        if(disTo[w] > disTo[v] + e.weight()) {
            /** 更新数据 */
            disTo[w] = disTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    /**
     * 顶点的放松(顶点所有边都放松一次)
     */
    private void relax(EdgeWeightedDiGraph G, int v) {
        for(DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if(disTo[w] > disTo[v] + e.weight()) {
                disTo[w] = disTo[v] + e.weight();
                edgeTo[w] = e;
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

    /************** 以上为最短路径实现的基本代码 ****************/
}
