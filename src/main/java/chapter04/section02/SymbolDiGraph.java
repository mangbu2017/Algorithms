package chapter04.section02;

import chapter03.section03.alg3_4.RedBlackBST;
import chapter04.section01.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public class SymbolDiGraph {
    private RedBlackBST<String, Integer> st;
    private String[] keys;
    private DiGraph G;

    public SymbolDiGraph(String filename, String sp) {
        st = new RedBlackBST<String, Integer>();
        In in = new In(filename);
        while(in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
//            System.out.println(Arrays.toString(a));
            for(int i = 0; i < a.length; i ++) {
//                System.out.println("before " + a[i] + ":" + st.size());
                if(!st.contains(a[i])) {
                    st.put(a[i], st.size());
//                    System.out.println("after: " + a[i] + ":" + st.size());
                }
            }
        }

        keys = new String[st.size()];

        for(String name: st.keys()) {
            keys[st.get(name)] = name;
        }

        G = new DiGraph(st.size());
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

    DiGraph G() {
        return G;
    }
}

