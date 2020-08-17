package chapter02.section04;

import edu.princeton.cs.algs4.Heap;
import org.junit.Test;

import java.util.Arrays;

public class HeapSort {
    /**
     * 从1开始是必须的
     * @param arr
     */
    public static void sort(Comparable[] arr) {
        int len = arr.length;
        Comparable a[] = new Comparable[len + 1];
//        System.out.println("len: " + len);

        for(int i = 1; i  < a.length; i ++) {
            a[i] = arr[i - 1];
        }

        for(int k = len / 2; k  >= 1; k --) {
            sink(a, k, len);
        }

        while(len > 1) {
            exch(a, 1, len --);
            sink(a, 1, len);
        }

        System.out.println(Arrays.toString(a));
    }

    static private void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static private boolean less(Comparable[] arr, int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    static private void swim(Comparable[] arr, int k) {
        while(k > 1 && less(arr, k / 2, k)) {
            exch(arr, k , k / 2);
            k = k / 2;
        }
    }

    static private void sink(Comparable[] arr, int k, int N) {
        while(k * 2 <= N) { // 左孩子
            int l = 2 * k;
            // 有j+1 && j+1 还比j大
//            System.out.println(l + ":" + N);
            if(l < N && less(arr, l, l + 1))
                l ++; // 设置为j+1

            // l >= k 大于等于孩子中大的 就结束
            if(!less(arr, k, l))
                break;

            exch(arr, k, l);

            k = l;
        }
    }

    @Test
    public void demo1() {
        Integer[] arr = { 1, 3, 4, -1, -2, -10, 6, 7, -3, 0, 0, 1, -2, -10, 0, -6, -11, 134, -112, -1, 1};

        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
