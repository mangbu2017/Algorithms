package chapter01.section03.alg1_2;

import chapter01.section03.alg1_1.ResizingArrayStack;
import org.junit.Test;

public class test {
    @Test
    public void demo1() {
        Stack<Integer> stack1 = new Stack<Integer>();
        stack1.pop();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.push(4);
        stack1.pop();
        System.out.println("finish");
    }
}
