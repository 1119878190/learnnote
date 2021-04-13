package 大厂面试.deadlock;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lx
 * @date 2021/1/20 11:49
 */

class MyThread implements Runnable{

    private String lockA;
    private String lockB;

    public MyThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"已持有"+lockA+"试图占有"+lockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println("已占有B");
            }
        }
    }
}
public class DeadLock {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        //new Thread(new MyThread(lockA,lockB),"AAA").start();
        //new Thread(new MyThread(lockB,lockA),"BBB").start();


        Map<String,Object> map = new HashMap<>();
        Map<String,Object> table = new Hashtable<>();
        table.put("aa",null);

    }
}
