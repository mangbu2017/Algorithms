package chapter04.section01.alg4_2;

import chapter03.section03.alg3_4.RedBlackBST;
import chapter04.section01.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

public class SymbolGraph {
    private RedBlackBST<String, Integer> st;
    private String[] keys;
    private Graph G;

    public SymbolGraph(String filename, String sp) {
        st = new RedBlackBST<String, Integer>();
        In in = new In(filename);
        while(in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            for(int i = 0; i < a.length; i ++) {
                if(!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }

        keys = new String[st.size()];

        for(String name: st.keys()) {
            keys[st.get(name)] = name;
        }

        G = new Graph(st.size());
        in = new In(filename);
        while(in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            /**
             * 将每一行的第一顶点 与 其他顶点相连
             */
            int v = st.get(a[0]);
            for(int i = 1; i < a.length; i ++) {
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String key) {
        return st.contains(key);
    }

    public int index(String key) {
        return st.get(key);
    }

    public String name(int i) {
        return keys[i];
    }

    Graph G() {
        return G;
    }

    /**
     * 正确 牛逼
     */
    public static void main(String[] args) {
        String filename = "files/routes";
        String delim = " ";
        SymbolGraph sg = new SymbolGraph(filename, delim);

        Graph G = sg.G();

        while(StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            for(int w: G.adj(sg.index(source))) {
                System.out.println(" " + sg.name(w));
            }
        }
    }
}
