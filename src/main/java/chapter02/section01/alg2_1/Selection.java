package chapter02.section01.alg2_1;

import org.junit.Test;

import java.util.Arrays;

public class Selection {
    // 弟弟
    public static void $sort(int arr[]) {
        for(int i = 0; i < arr.length; i ++) {
            for(int j = i + 1; j < arr.length; j ++) {
                if(arr[i] > arr[j]) { // 这里的每次都交换是可以避免的
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
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

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort(Comparable arr[]) {
        for(int i = 0; i < arr.length; i ++) {
            int min = i;
            for(int j = i + 1; j < arr.length; j ++) {
                if(less(arr[j], arr[min])) { // 这里的每次都交换是可以避免的
                    min = j;
                }
            }
            exch(arr, i, min);
        }
    }

    @Test
    public void demo1() {
        Integer[] arr = { 1, 3, 4, -1, -2, -10, 6, 7, -3, 0, 0, 1, -2, -10, 0, -6, -11, 134, -112, -1, 1};

        sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
