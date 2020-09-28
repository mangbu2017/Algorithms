package chapter04.section04;

import chapter01.section03.alg1_4.Bag;
import chapter04.section03.Edge;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

/**
 * 加权有向图
 */
public class EdgeWeightedDiGraph {
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;  /** 邻接表 */

    public EdgeWeightedDiGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Bag[V];
        for(int v = 0; v < V; v ++) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    public EdgeWeightedDiGraph(In in) {
        this(in.readInt());
        int E = in.readInt();

        for(int i = 0; i < E; i ++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E ++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> b = new Bag<DirectedEdge>();
        for(int v = 0; v < V; v ++) {
            for(DirectedEdge e: adj[v]) {
                b.add(e);
            }
        }

        return b;
    }

    @Override
    public String toString() {
       String res = "";
       res += "v: " + V + "\n";
       res += "e: " + E + "\n";

       for(DirectedEdge e: edges()) {
           res += e.from() + " -> " + e.to() + "\n";
       }

       return res;
    }
}
