import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public class TestSon {



    public static void main(String[] args) {
        Father father = new Son();
        father.eat();
        father.sleep();
        System.out.println(father.str);
    }

    public void ss()  {


        try {
            FileInputStream fileInputStream = new FileInputStream("F://");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("sdfsd");
        }
    }



}
