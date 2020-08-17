package chapter02.section03.alg2_5;

public class Quick3way {
    private static void sort(Comparable[] arr, int start, int end) {
        if(end <= start)
            return;

        int i = start, j = end, k = start + 1;
        Comparable v = arr[start];

        while(k <= j) {
            int cmp = arr[k].compareTo(v); /** 从k开始 */
            if(cmp < 0) // 小于切分元素: 交换i k, i+1,
                exch(arr, i ++, k ++);
            else if(cmp > 0)
                exch(arr, k, j --); // 大于切分元素: 交换k j(k已经可以放在最后了), j --
            else
                k ++; // i ~ k-1 都是==v的
        }

        sort(arr, start, i - 1);
        sort(arr, j + 1, end);

    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
