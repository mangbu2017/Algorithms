package chapter02.section03.alg2_5;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Arrays;

public class Quick {
    public static void sort(Comparable[] arr) {
        // 随机打乱arr
        StdRandom.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    private static boolean less(Comparable a, Comparable b) {
        if(a.compareTo(b) < 0)
            return true;
        else
            return false;
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void sort(Comparable[] arr, int start, int end) {
        // start end 中间找不到 j 了
        if(end <= start)
            return;

        int j = partition(arr, start, end);
        sort(arr, start, j - 1);
        sort(arr, j + 1 , end);

    }

    /**
     * 用下标为start的元素 切分数组 并返切分元素的最后的位置
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static int partition(Comparable[] arr, int start, int end) {
        int i = start, j = end + 1;
        Comparable v = arr[start];

        while(true) {
            // 从左数 第一个比 v小的
            while(less(arr[++ i], v)) {
                if(i == end)
                    break;
            }
            // 从右数 第一个比 v大的
            while(less(v, arr[-- j])) {
                if(j == start)
                    break;
            }
            if(i >= j)
                break;

            exch(arr, i, j);
        }

        exch(arr, start, j);

        return j;
    }

    @Test
    public void demo1() {
        Integer[] arr = { 1, 3, 4, -1, -2, -10, 6, 7, -3, 0, 0, 1, -2, -10, 0, -6, -11, 134, -112, -1, 1};

        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
