package chapter04.section03;

/**
 * todo 这里Edge的这个继承 不太理解 ???
 */
public class Edge implements Comparable<Edge>{
    private final int v; // 顶点1
    private final int w; // 顶点2
    private final double weight; // 边的权重

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if(vertex == v)
            return w;
        else if(vertex == w)
            return v;
        else throw new RuntimeException("inconsistent edge(前后矛盾的边)");
    }

    @Override
    public int compareTo(Edge that) {
        if(this.weight() < that.weight())
            return -1;
        else if(this.weight() > that.weight())
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
