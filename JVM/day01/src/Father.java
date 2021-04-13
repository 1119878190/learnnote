import org.junit.Test;
import sun.font.FontRunIterator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;

public class Father implements Cloneable{

    int a = 3;
     String str = "fatherString";

    static {
        int b = 2;
    }



    public void eat(){
        System.out.println("father吃饭");
    }

    public void sleep(){
        System.out.println("father睡觉");
    }

    public static void main(String[] args)  {
    /*  String s1 = "java";
      String s2 = "ok";
      String s3 = s1+s2;
        System.out.println(s3 == "javaok");*/
        String s1 = "javaEE";
        String s2 = "hadoop";
        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;



        System.out.println(s3 == s4); // true
        System.out.println(s3 == s5); // false
        System.out.println(s3 == s6); // false
        System.out.println(s3 == s7); // false
        System.out.println(s5 == s6); // false
        System.out.println(s5 == s7); // false
        System.out.println(s6 == s7); // false



    }



    @Test
    public void testone(){
        int i = 1;
        int j = 0;
        j = i++ + ++i + i++ + ++i ;
    //局部变量表 1,2    3     3,4   5
    //操作数栈   1      3     3     5
        System.out.println(j);


    }


}


