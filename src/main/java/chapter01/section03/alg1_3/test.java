package chapter01.section03.alg1_3;

import org.junit.Test;

public class test {
    @Test // 空队列删除报错
    public void demo1() {
        Queue<Integer> queue1 = new Queue<Integer>();
        queue1.dequeue();
    }

    @Test // 使用N == 0 dequque时判空出错, enqueue没问题
    public void demo2() {
        Queue<Integer> queue2 = new Queue<Integer>();
        queue2.enqueue(1);
        queue2.dequeue();
        System.out.println("finish");
    }
}
