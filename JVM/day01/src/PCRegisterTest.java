import java.util.Date;

public class PCRegisterTest {

    public static void main(String[] args) {
        int i = 10;
        int j = 20;
        int k = i +j;
        String n = "111";
        char[] c = new char[]{3};
        String s = "abc";
        System.out.println(i);
    }

    public static void testStatic(){
        PCRegisterTest pcRegisterTest = new PCRegisterTest();
        Date date = new Date();
        int count = 10;
        System.out.println(count);
    }

    public void add(){
        int i = 1;
        int j = i++ + ++i;
        System.out.println(j);



    }


}