package chapter02.section02.alg2_4;

import org.junit.Test;

import java.util.Arrays;

public class MergeBU {
    // 辅助数组
    private static Comparable[] aux;

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        aux = new Comparable[len];

        for(int sz = 1; sz < len; sz = sz * 2) {
            for(int start = 0; start < len - sz; start += sz * 2) {
                merge(arr, start, start + sz - 1, Math.min(start + sz * 2 - 1, len - 1));
            }
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        if(a.compareTo(b) < 0)
            return true;
        else
            return false;
    }

    private static void merge(Comparable[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;

        /**
         * todo 优化点3: 避过赋值操作
         */
        for(int k = start; k <= end; k ++)
            aux[k] = arr[k];

        System.out.println(Arrays.toString(Arrays.copyOf(aux, start)));

        for(int k = start; k <= end; k ++)
            if(i > mid) // 左边用尽
                arr[k] = aux[j ++];
            else if(j > end) // 右边用尽
                arr[k] = aux[i ++];
            else if(less(aux[j], aux[i])) // 右边小用右边
                arr[k] = aux[j ++];
            else // 左边小或相等 用左边
                arr[k] = aux[i ++];
    }

    @Test
    public void demo1() {
        Integer[] arr = { 1, 3, 4, -1, -2, -10, 6, 7, -3, 0, 0, 1, -2, -10, 0, -6, -11, 134, -112, -1, 1};

//        $sort(arr);
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
