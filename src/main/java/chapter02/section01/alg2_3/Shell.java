package chapter02.section01.alg2_3;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Shell {

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static boolean less(Comparable a, Comparable b) {
        if(a.compareTo(b) < 0)
            return true;
        else
            return false;
    }

    public void $sort(int arr[]) {
        int len = arr.length;
        int k = 1;
        while( k * 3 < len ) {
            k = k * 3 + 1;
        }

        while(k >= 1) {
            System.out.println("k: " + k);
            /**
             * i ++ 每个都需要比到 每个元素都属于一个 有序子数组
             */
            for(int i = k; i < len; i ++) {
                for(int j = i; j >= k && (arr[j] < arr[j - 1]); j -= k) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }

            k = k / 3;
        }
    }

    static public void sort(Comparable arr[]) {
        int len = arr.length;
        int k = 1;
        while( k * 3 < len ) {
            k = k * 3 + 1;
        }

        while(k >= 1) {
            for(int i = k; i < len; i ++) {
                for(int j = i; j >= k && less(arr[j], arr[j - 1]); j -= k) {
                    exch(arr, j, j - 1);
                }
            }

            k = k / 3;
        }
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
