package chapter02.section01.alg2_2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Insertion {
    // 弟弟
    public void $sort(int[] arr) {
        int len = arr.length;
        for(int i = 1; i < len; i ++) {
            for(int j = i; j > 0; j --) {
                if(arr[j] < arr[j - 1]) { // 进行插入
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        if(a.compareTo(b) < 0)
            return true;
        else
            return false;
    }

    private static boolean less(String a, String b, int i) {
        return a.substring(i).compareTo(b.substring(i)) < 0;
    }

    @Test
    public void demo2() {
        /**
         * 会报String下标越界错误
         */
        System.out.println("aaa".substring(5));
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        for(int i = 1; i < len; i ++) {
            for(int j = i; j > 0 && less(arr[j], arr[j - 1]); j --) {
                exch(arr, j, j - 1);
            }
        }
    }

    /**
     * 插入排序就是不断地将后一个插入到前面的有序数组中
     */
    public static void sort(String[] arr, int start, int end, int d) {
        for(int i = start; i <= end; i ++) {
            for(int j = i; j > start && less(arr[j], arr[j - 1], d); j --) {
                exch(arr, j, j - 1);
            }
        }
    }

    @Test
    public void demo1() {
        int[] arr = { 1, 3, 4, -1, -2, -10, 6, 7, -3, 0, 0, 1, -2, -10, 0, -6, -11, 134, -112, -1, 1};
        Integer[] arr1 = IntStream.of(arr).boxed().collect(Collectors.toList()).toArray(new Integer[0]);

        $sort(arr);
        sort(arr1);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
    }
}
