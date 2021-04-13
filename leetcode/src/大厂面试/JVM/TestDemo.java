package 大厂面试.JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lx
 * @date 2021/1/25 11:13
 */
public class TestDemo {
    public static void main(String[] args) throws Exception {
        int i = 129;
        Integer j = 129;
        Integer k = 127;
        Integer l = new Integer(127);
        String s = null;

        System.out.println(i == j);
        System.out.println(j == k);
        System.out.println(i == l);
        System.out.println(j == l);

    }
}
