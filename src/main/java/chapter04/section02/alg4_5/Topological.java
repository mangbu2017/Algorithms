package chapter04.section02.alg4_5;

import chapter04.section02.DepthFirstOrder;
import chapter04.section02.DiGraph;
import chapter04.section02.DirectedCycle;
import chapter04.section02.SymbolDiGraph;

/**
 * 有向无环图的拓扑排序
 */
public class Topological {
    private Iterable<Integer> order; // 顶点的拓扑排序

    public Topological(DiGraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if(!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    /**
     * 正确
     */
    public static void main(String[] args) {
        String filename = "files/jobs";
        String separator = "/";
        SymbolDiGraph sg = new SymbolDiGraph(filename, separator);

        Topological top = new Topological(sg.G());

        for(int v: top.order()) {
            System.out.println(sg.name(v));
        }
    }
}
