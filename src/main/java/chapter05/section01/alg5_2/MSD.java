package chapter05.section01.alg5_2;

import chapter02.section01.alg2_2.Insertion;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 高位优先的字符串排序
 * (可太闹腾了，死脑细胞)
 */
public class MSD {
    private static int R = 256; // 基数
    private static final int M = 15; /** 小数组的切换阈值 */
    private static String[] aux; // 数据分类的辅助数组

    private static int charAt(String s, int d) {
        if(d < s.length())
            return s.charAt(d);
        else
            return -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    /**
     * 以第d个字符为键将a[start]到a[end]排序
     * @param a
     * @param start
     * @param end
     * @param d 正数第几个字符
     */
    public static void sort(String [] a, int start, int end, int d) {

        if(start >= end)
            return;

        /**
         * 如果数组比较小那就直接使用插入排序
         * (todo ??? 应该是性能方面的考虑)
         * 这里有问题 不递归了
         */
//        if(end <= start + M) {
//            Insertion.sort(a, start, end, d);
//            return;
//        }

        /**
         * 频率计算
         * 每次递归调用的count都是一份全新的
         */
        int[] count = new int[R + 2];
        for(int i = start; i <= end; i ++) {
            /**
             * 从下标2开始，空出两个位置
             * -1 + 2 = 1 count1
             * 1 - 1 = 0 count2
             */
            count[charAt(a[i], d) + 2] ++;
        }

        /** 频率转化为索引 */
        for(int r = 0; r < R + 1; r ++) {
            count[r + 1] += count[r];
        }

        /**
         * are      she
         * by       sells
         * she      shore
         * sells
         * shore
         * the
         * the
         *
         * count [0, 0, 0(a),..., 1(e),..., 2(h),..., 0(z)]
         * count [0, 0(a.start), 0(a),...,0(e.start),1(e),...,1(h.start),3(h),..., 3(z)]
         * count1 index+2 表示频率
         * count2 index+1 就是起始位置
         *
         *
         * 字符串结尾
         * count1 [0, 2, 0(a),..., 1(e),..., 2(h),..., 0(z)]
         * count2 [0, 2(a.start), 0(a),..., 2(e.start),1(e),...,1(h.start),3(h),..., 3(z)]
         * 此时count2[1]有值
         * 即count2的第一个字符的起始位置就是2了
         * 无论后续如何排列结尾总是排在前面
         */


        /**
         * 数据分类
         * 只是在[start, end]区间 进行数据分类
         */
        for(int i = start; i <= end; i ++) {
            /**
             * count[charAt(a[i], d) + 1] 取的是 字符串第d个字符应该在的位置
             * 会把aux[start, end]都填上 只不过不是按照顺序来的
             * %%% aux肯定是[0, end-start+1]的数组 %%%
             */
            aux[count[charAt(a[i], d) + 1] ++] = a[i];
        }

        /** 回写 */
        for(int i = start; i <= end; i ++) { // 2 4
            /**
             * a[2] = aux[0] 这里说明肯定是要用到 aux[0]的 => 因为上面 ++ 是按照次序来的
             * a[3] = aux[1]
             * a[4] = aux[2]
             */
            a[i] = aux[i - start];
        }

        /**
         * 代码到这里已经将正数地d个字符进行了 建索引排序
         */

        /**
         * 递归地以每个字符为键进行排序
         * 1. 所有首字符为a的进行d为1的排序
         * 2. 所有首字符为b的进行d为1的排序
         * 3. 所有首字符为c的进行d为1的排序
         * ...
         */
        for(int r = 0; r < R; r ++) {
            /**
             * 1. 字符串数组
             * 2. a的起始位置到终止位置
             * 3.
             * 4.
             */
            sort(a,start + count[r], start + count[r + 1] - 1, d + 1);
        }
    }

    @Test
    public void demo2() {
        String[] s = In.readStrings("files/MSD");
        System.out.println(Arrays.toString(s));
        MSD.sort(s);
        System.out.println(Arrays.toString(s));
    }


    @Test
    public void demo1() {
        /**
         * 会报越界错误
         */
        System.out.println(new String("11111").charAt(10));
    }
}
