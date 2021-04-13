package 大厂面试第三季.LockSupport;

/**
 * @author lx
 * @date 2021/3/3 18:40
 */
public class Demo {
    public static void main(String[] args) {
        String s = new String("2")+new String("2");
        s.intern();
        String s2 = "22";
        System.out.println(s == s2); //
    }
}
