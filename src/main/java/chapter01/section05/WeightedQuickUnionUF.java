package chapter01.section05;

public class WeightedQuickUnionUF {
    private int[] id; // 父链接数组
    private int[] sz; // 各个根节点所对应的分量的大小
    private int count; // 联通分量的数量

    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i ++) {
            id[i] = i; // 父节点初始化为自身
        }
        sz = new int[N];
        for(int i = 0; i < N; i ++) {
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 找到p所在的联通分量的根节点
     */
    public int find(int p) {
        while(p != id[p])
            p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if(i == j)
            return;

        if(sz[i] < sz[j]) {
            id[i] = j;
            sz[j] = sz[j] += sz[i];
        }else {
            id[j] = i;
            sz[i] = sz[i] += sz[j];
        }

        count --;
    }
}
