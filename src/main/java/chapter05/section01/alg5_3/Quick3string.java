package chapter05.section01.alg5_3;

import chapter05.section01.alg5_2.MSD;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Arrays;

/**
 * 三向字符串快速排序
 * 就是快排
 */
public class Quick3string {
    private static int charAt(String s, int d) {
        if(d < s.length())
            return s.charAt(d);
        else
            return -1;
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void sort(String[] a, int start, int end, int d) {
        if(end <= start)
            return;

        int i = start, j = end;
        /** 第一个元素的首字母 */
        int v = charAt(a[start], d);
        int k = start + 1;

        while(k <= j) {
            /** 当前元素首字母 */
            int t = charAt(a[k], d);

            if(t < v) // 左边
                exch(a, i ++, k ++);
            else if(t > v) // 右边
                exch(a, k, j --);
            else // 中间
                k ++;
        }

        sort(a, start, i - 1, d);

        /** 如果v是结尾则不用递归了 */
        if(v >= 0)
            sort(a, i, j, d + 1);

        sort(a, j + 1, end, d);
    }

    @Test
    public void demo2() {
        String[] s = In.readStrings("files/MSD");
        System.out.println(Arrays.toString(s));
        MSD.sort(s);
        System.out.println(Arrays.toString(s));
    }
}
