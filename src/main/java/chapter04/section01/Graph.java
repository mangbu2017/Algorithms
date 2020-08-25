package chapter04.section01;


import chapter01.section03.alg1_4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {
    /**
     * final也支持在构造函数中初始化
     */
    private final int V; // 顶点数
    private int E; // 边数
    private Bag<Integer>[] adj; // 邻接表

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V]; // 创建邻接表
        for(int i = 0; i < V; i ++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
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
        adj[w].add(v);
        E ++;
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

//    public Iterable<Integer> adj(int v) {
//        return adj[v];
//    }
    public Bag<Integer> adj(int v) {
        return adj[v];
    }

    public static int degree(Graph G, int v) {
//        int degree = 0;
//        for(int w: G.adj(v))
//            degree ++;
//        return degree;
        return G.adj(v).size();
    }

}
