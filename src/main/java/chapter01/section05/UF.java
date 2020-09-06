package chapter01.section05;

import edu.princeton.cs.algs4.In;

public class UF {
    private int[] id;
    private int count;

    public UF(int N) {
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i ++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /** quick-find start */
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if(pID == qID) {
            return;
        }

        for(int i = 0; i < id.length; i ++) {
            if(id[i] == pID)
                id[i] = qID;
        }
        count --;
    }
    public int find(int p) {
        return id[p];
    }
    /** quick-find end */

    public static void main(String[] args) {
        In in = new In("files/tinyUF");
        int N = in.readInt();
        UF uf = new UF(N);

        while(!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if(uf.connected(p, q))
                continue;

            uf.union(p, q);
            System.out.println(p + " " + q);
        }

        System.out.println(uf.count() + " components");
    }
}
