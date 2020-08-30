package chapter04.section02;

import chapter01.section03.alg1_4.Bag;
import edu.princeton.cs.algs4.In;

public class DiGraph {
    /**
     * final也支持在构造函数中初始化
     */
    private final int V; // 顶点数
    private int E; // 边数
    private Bag<Integer>[] adj; // 邻接表

    public DiGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V]; // 创建邻接表
        for(int i = 0; i < V; i ++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public DiGraph(In in) {
        this(in.readInt());
        int E = in.readInt();

        for(int i = 0; i < E; i ++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }
    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E ++;
    }

    public DiGraph reverse() {
        DiGraph R = new DiGraph(V);
        for(int v = 0; v < V; v ++) {
            for(int w: adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

    @Override
    public String toString() {
        String s = V + " vertices, " + E + "edges\n";

        for(int v = 0; v < V; v ++) {
            s += v + ": ";
            for(int w: this.adj(v)) {
                s += w + " ";
            }
            s += "\n";
        }

        return s;
    }

    public Bag<Integer> adj(int v) {
        return adj[v];
    }

    public static int degree(DiGraph G, int v) {
        return G.adj(v).size();
    }

}
