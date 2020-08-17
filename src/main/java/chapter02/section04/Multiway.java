package chapter02.section04;

import chapter02.section04.alg2_6.IndexMinPQ;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Arrays;

public class Multiway {
    public static void merge(In[] streams) {
        int len = streams.length;
        System.out.println("len: " + len);
        IndexMinPQ<String> pq = new IndexMinPQ<String>(len);

        // 这里只读了第一个
        for(int i = 0; i < len; i ++) {
            if(!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }

        while(!pq.isEmpty()) {
            System.out.println(pq.min());
            int i = pq.delMin();
            System.out.println("i: " + i);

            if(!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }
    }

    @Test
    public void demo1() {
//        int N = args.length;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        int N = args.length;
        In[] streams = new In[N];
        for(int i = 0; i < N; i ++) {
            streams[i] = new In(args[i]);
        }
        merge(streams);
    }
}
