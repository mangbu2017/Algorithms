package chapter04.section03;

import chapter01.section03.alg1_4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 加权无向图
 */
public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;  /** 邻接表 */

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for(int v = 0; v < V; v ++) {
            adj[v] = new Bag<Edge>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();

        for(int i = 0; i < E; i ++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E ++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<Edge>();
        for(int v = 0; v < V; v ++) {
            for(Edge e: adj[v]) {
                /**
                 * 去除重复边
                 */
                if(e.other(v) > v)
                    b.add(e);
            }
        }

        return b;
    }
}
