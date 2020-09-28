package chapter05.section01;

import java.util.Arrays;

/**
 * 键索引计数法排序
 */
public class KeyCount {
    class Student {
        String name;
        int key; // 组号

        public Student(String name, int key) {
            this.name = name;
            this.key = key;
        }

        public int key() {
            return key;
        }
    }

    static public void main(String[] args) {
        KeyCount kc = new KeyCount();
        int[] count = new int[10];
        Student[] s = kc.init();

        for(int i = 0; i < s.length; i ++) {
            count[s[i].key() + 1] ++;
        }

        System.out.println(Arrays.toString(count));

        for(int r = 0; r < count.length - 1; r ++) {
            count[r + 1] += count[r];
        }

        System.out.println(Arrays.toString(count));

        String [] aux = new String[s.length];

        for(int i = 0; i < s.length; i ++) {
            /**
             * 1. 第几组 s[i].key()
             * 2. 下一组的起始位置 count[s[i].key()]
             * 3. 赋值之后再+1 count[s[i].key()] ++
             */
            aux[count[s[i].key()] ++] = s[i].name;
        }

        System.out.println(Arrays.toString(aux));

    }

    public Student[] init() {
        Student[] students = new Student[22];
        students[0] = new Student("aaa", 2);
        students[1] = new Student("bbb", 2);
        students[2] = new Student("ccc", 1);

        students[3] = new Student("ddd", 2);
        students[4] = new Student("eee", 2);
        students[5] = new Student("fff", 2);
        students[6] = new Student("ggg", 1);
        students[7] = new Student("hhh", 1);

        students[8] = new Student("iii", 3);
        students[9]= new Student("jjj", 3);
        students[10] = new Student("kkk", 3);
        students[11] = new Student("lll", 3);
        students[12] = new Student("mmm", 4);
        students[13] = new Student("nnn", 4);


        students[14] = new Student("ooo", 4);
        students[15] = new Student("ppp", 4);
        students[16] = new Student("qqq", 3);
        students[17] = new Student("rrr", 3);
        students[18] = new Student("sss", 4);
        students[19] = new Student("ttt", 4);

        students[20] = new Student("uuu", 0);
        students[21] = new Student("vvv", 0);


        return students;
    }
}
