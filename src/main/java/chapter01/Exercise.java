package chapter01;
import edu.princeton.cs.algs4.*;

public class Exercise {
    public static void main(String[] args) {
        Exercise e = new Exercise();
//        e.case1_1_1();
//        e.case1_1_2();
        e.case1_1_3();
    }

    private void case1_1_1() {
        System.out.println((0 + 15) / 2);
        System.out.println(2.0e-6 * 100000000.1);
        System.out.println( true && false || true && true );
    }

    private void case1_1_2() {
        System.out.println((1 + 2.236) / 2 );
        System.out.println(1 + 2 + 3 + 4.0);
        System.out.println( 4.1 >= 4);
        System.out.println( 1 + 2 + "3");
    }

    private boolean case1_1_3() {
        int[] arr = new int[3];
        StdOut.println(arr[0]);
        for(int i = 0; i < 3; i ++) {
            arr[i] = StdIn.readInt();
        }

        if(arr[0] == arr[1]) {
            if(arr[1] == arr[2]) {
                StdOut.println("equal");
                return true;
            }
        }
        StdOut.println("notequal");
        return false;
    }
}
