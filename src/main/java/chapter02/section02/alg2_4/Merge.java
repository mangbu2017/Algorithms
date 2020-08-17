package chapter02.section02.alg2_4;

import org.junit.Test;

import java.util.Arrays;

public class Merge {
    // 辅助数组
    private static Comparable[] aux;

    public static void sort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int start, int end) {
        if(start == end) // <=
            return;

        int mid = (start + end) / 2;

        sort(arr, start, mid);
        sort(arr, mid + 1, end);

        /**
         * 优化点2: 跳过merge操作
         */
        if(!less(arr[mid + 1], arr[mid]))
            return;

        merge(arr, start, mid, end);
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
