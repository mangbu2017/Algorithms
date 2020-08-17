package chapter02.section01;

import chapter02.section01.alg2_1.Selection;
import chapter02.section01.alg2_2.Insertion;
import chapter02.section01.alg2_3.Shell;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

public class SortCompare {
    public static double time(String alg, Double[] a) {
        Stopwatch timer = new Stopwatch();

        if(alg.equals("Insertion"))
            Insertion.sort(a);

        if(alg.equals("Selection"))
            Selection.sort(a);

        if(alg.equals("Shell"))
            Shell.sort(a);

        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        /**
         * 使用算法alg将T个长度为N的数组排序
         */
        double total = 0;

        Double[] a = new Double[N];

        for(int i = 0; i < T; i ++) {
            for(int j = 0; j < N; j ++) {
                a[j] = StdRandom.uniform();
            }
            total += time(alg, a);
        }

        return total;
    }

    @Test
    public void demo1() {
        double time = timeRandomInput("Selection", 1000000, 1000);
        double time1 = timeRandomInput("Insertion", 1000000, 1000);
        double time2 = timeRandomInput("Shell", 1000000, 1000);

        System.out.println(time + " ~ " + time1 + " ~ " + time2);
        // 78.2059999999997 ~ 87.2019999999996 ~ 113.46599999999975 10000&1000无法体现

    }
}
