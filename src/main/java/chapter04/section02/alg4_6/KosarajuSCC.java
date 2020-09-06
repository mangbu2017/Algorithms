package chapter04.section02.alg4_6;

import chapter01.section03.alg1_4.Bag;
import chapter04.section01.Graph;
import chapter04.section01.alg4_3.CC;
import chapter04.section02.DepthFirstOrder;
import chapter04.section02.DiGraph;
import edu.princeton.cs.algs4.In;

/**
 * 找出图中所有的强联通分量 todo 不是很明白呀??? 有点难理解
 */
public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(DiGraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for(int s: order.reversePost()) {
            /**
             * 这里说明之前的遍历并没有 mark到该顶点
             */
            if(!marked[s]) {
                dfs(G, s);
                count ++;
            }
        }
    }

    private void dfs(DiGraph G, int v) {
        marked[v] = true;
        /**
         * 顶点所属的联通子图为count
         */
        id[v] = count;
        for(int w: G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * 是否属于统一强连通子图
     */
    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    /**
     * 返回v所在的联通子图(标识)
     */
    public int id(int v) {
        return id[v];
    }

    /**
     * 返回G的联通子图数量
     */
    public int count() {
        return count;
    }

    /**
     * 不太理解 但是程序正确
     */
    public static void main(String[] args) {
        DiGraph G = new DiGraph(new In("files/tinyDG"));
        KosarajuSCC cc = new KosarajuSCC(G);
        int M = cc.count();
        System.out.println(M + " components");

        Bag<Integer>[] components;
        components = (Bag<Integer>[]) new Bag[M];
        for(int i = 0; i < M; i ++) {
            components[i] = new Bag<Integer>();
        }

        for(int v = 0; v < G.V(); v ++) {
            components[cc.id(v)].add(v);
        }

        for(int i = 0; i < M; i ++) {
            for(int v: components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
