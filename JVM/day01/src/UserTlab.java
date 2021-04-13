import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserTlab {

    public static void main(String[] args) {
        System.out.println("我是打酱油的");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束了===================");

        Lock lock = new ReentrantLock();
     
        ThreadLocal local = new ThreadLocal();
        local.get();

        List<String> list = new ArrayList<>();


    }
}
