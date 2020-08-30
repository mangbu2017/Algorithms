package chapter04.section01.alg4_3;

import chapter01.section03.alg1_4.Bag;
import chapter04.section01.Graph;
import edu.princeton.cs.algs4.In;

public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int s = 0; s < G.V(); s ++) {
            /**
             * 这里说明之前的遍历并没有 mark到该顶点
             */
            if(!marked[s]) {
                dfs(G, s);
                count ++;
            }
        }
    }

    private void dfs(Graph G, int v) {
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
     * 我们只需要判断 两个顶点是否 属于同一个联通子图就ok了
     */
    public boolean connected(int v, int w) {
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

    // CC可太牛逼了
    public static void main(String[] args) {
//        In in = new In("files/CCMain");
        Graph G = new Graph(new In("files/tinyG.txt"));
        CC cc = new CC(G);
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
