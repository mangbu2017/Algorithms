package chapter03.section01;

import chapter03.section01.alg3_2.BinarySearchST;
import chapter03.section01.alg3_3.BST;
import org.junit.Test;

public class Test0301 {
    @Test
    public void test1() {
        System.out.println(1 / 2);
    }

    @Test
    public void test32() {
//        BinarySearchST st = new BinarySearchST<String, Number>(100);
        BST st = new BST<String, Number>();

        st.put('S', 0);
        st.put('E', 1);
        st.put('A', 2);
        st.put('R', 3);
        st.put('C', 4);
        st.put('H', 5);
        st.put('E', 6);
        st.put('X', 7);
        st.put('A', 8);
        st.put('M', 9);
        st.put('P', 10);
        st.put('L', 11);
        st.put('E', 12);

        System.out.println(st);
        st.delete('X');
//        System.out.println(st);
//        st.delete('P');
//        System.out.println(st);
//        st.delete('A');
//        System.out.println(st);
//        System.out.println(st.ceiling('A'));
//        System.out.println(st.ceiling('F'));
//        System.out.println(st.ceiling('X'));
//
//        System.out.println(st.floor('H'));
//        System.out.println(st.floor('I'));
//        System.out.println(st.floor('B'));
//        st.delete('A');
////        System.out.println(st);
//        System.out.println(st.floor('B'));
//        System.out.println(st.floor('Z'));

//        String res = "";
//        for(Object key: st.keys('A', 'X')) {
//            res += key + ":" + st.get((Comparable) key) + ", ";
//        }
//        res += "size: " + st.size();
//
//        System.out.println(res);
    }
}
