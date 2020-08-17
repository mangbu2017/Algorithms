package chapter01;

import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.*;
import org.junit.Test;
import org.junit.jupiter.api.TestFactory;

// 非递归
public class BinarySearch {
    // 1 2 3 4 5 => 2
    // mid = 2
    // 0 1
    // 1 1
    public static int rank(int key, int[] arr) { // 直接针对的就是有序集合
        int lo = 0;
        int hi = arr.length - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2; // 向下取整
            if(key < arr[mid])
                hi = mid - 1;
            else if(key > arr[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    // 返回所有值为key的idx 个数
    public static int rankRepeatCount(int key, int[] arr) {
        int count = 0;
        int len = arr.length;
        int idx = BinarySearch.rank(key, arr);

        if(idx == -1)
            return count;

        count ++;

        int left = idx - 1,
            right = idx + 1;

        while (left > -1) {
            if(arr[left] == key) {
                count ++;
                left --;
            }else {
                break;
            }
        }

        while (right < len) {
            if(arr[right] == key) {
                count ++;
                right ++;
            }else {
                break;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] whiteList = In.readInts(args[0]);

        Arrays.sort(args);
        while (!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if(rank(key, whiteList) < 0) {
                StdOut.println(key);
            }
        }
    }

    @Test
    public void demo1() {
        System.out.println(1 / 2);
    }

    @Test
    public void demo2() {
        int[] arr = { 1, 2, 3, 4, 5, 5, 6, -5, 6, 6, 1, 3 };
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        int count3 = BinarySearch.rankRepeatCount(3, arr);
        int count5 = BinarySearch.rankRepeatCount(5, arr);
        int count_1 = BinarySearch.rankRepeatCount(-1, arr);
        int count6 = BinarySearch.rankRepeatCount(6, arr);

        System.out.println(count3);
        System.out.println(count5);
        System.out.println(count_1);
        System.out.println(count6);
    }
}
