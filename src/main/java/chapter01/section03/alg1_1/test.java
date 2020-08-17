package chapter01.section03.alg1_1;

import org.junit.Test;

public class test {
    @Test
    public void demo1() {
        ResizingArrayStack<Integer> resizingArrayStack1 = new ResizingArrayStack<Integer>();
        resizingArrayStack1.push(1);
        resizingArrayStack1.push(2);
        resizingArrayStack1.push(3);
        resizingArrayStack1.push(4);
        resizingArrayStack1.pop();
        System.out.println("finish");
    }
}
