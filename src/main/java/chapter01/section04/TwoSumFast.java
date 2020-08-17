package chapter01.section04;
import chapter01.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.util.Arrays;

// TwoSum N^2
// TwoSum nlog2N 线性对数级别
public class TwoSumFast {
    public static int count(int[] a) {
        // 快排 log2N
        Arrays.sort(a);

        int len = a.length;
        int count = 0;
        System.out.println(Arrays.toString(a));

        for(int i = 0; i < len; i ++) {

            // 二分查找算法 如果包含重复项的话 返回的不一定是哪个
            int idx = BinarySearch.rank(-a[i], a);

//            System.out.println(a[i] + " " + idx);
            if(idx > i) {// 排序后 负数的负值肯定更在它的右边
//                System.out.println(a[i] + " " + -a[i]);
                count ++;
            }
        }

        return count;
    }

    public static int repeatCount(int[] a) {
        // 归并 log2N
        Arrays.sort(a);

        int len = a.length;
        int count = 0;
        System.out.println(Arrays.toString(a));

        for(int i = 0; i < len; i ++) {

            int subCount = findCountInRight(a, i, -a[i]);

            count += subCount;
//            System.out.println("repeatCount: " + a[i] + " " + subCount);
        }

        return count;
    }

    public static int findCountInRight(int[] arr, int idx, int key) {
        int nLen = arr.length - (idx + 1);
        int[] nArr = new int[nLen];
//        System.out.println("nLen: " + nLen);
        System.arraycopy(arr, idx + 1, nArr, 0, nLen);
//        System.out.println("原数组: " + Arrays.toString(arr));
//        System.out.println("目标数组: " + Arrays.toString(nArr));

        int count = BinarySearch.rankRepeatCount(key, nArr); // >= 0
        System.out.println(key + Arrays.toString(nArr) + count);
        return count;
    }

    public static void main(String[] args) {
        int[] a = In.readInts("files/TwoSumFast");
        StdOut.println(count(a));
    }

    // 有重复数据的情况 类似组排
    @Test
    public void demo1() {
        int[] arr = { -1 , -2, 0, 0, 0, 1, 5, -5, 2, -6, 7, 6, -7 , -7, 8};
        // => 3 1 1 1 1 => 7
        int count = repeatCount(arr);
        System.out.println("count: " + count);
    }
}
