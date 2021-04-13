package 大厂面试第三季.LockSupport;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ClassTest {
    String str = new String("hello");
    char[] ch = {'a', 'b', 'c'};

    public void fun(String str, char ch[]) {
        str = "world";
        ch[0] = 'd';
    }

    public static void main(String[] args) {
        ClassTest test1 = new ClassTest();
        test1.fun(test1.str, test1.ch);
        System.out.print(test1.str + " and ");
        System.out.print(test1.ch);


        File file = new File("d://e");
       // File[] files = file.listFiles((File f) ->f.getName().endsWith());

        Set set = new HashSet<>();

    }
}