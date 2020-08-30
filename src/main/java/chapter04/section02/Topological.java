package chapter04.section02;

/**
 * 有向无环图的拓扑排序
 */
public class Topological {
    private Iterable<Integer> order; // 顶点的拓扑排序

    public Topological(DiGraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if(!cycleFinder.hasCycle()) {
            DeepFirstOrder dfs = new DeepFirstOrder(G);
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
