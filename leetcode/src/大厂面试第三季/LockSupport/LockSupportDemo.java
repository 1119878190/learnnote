package 大厂面试第三季.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author lx
 * @date 2021/2/22 11:29
 */
public class LockSupportDemo {

    public static void main(String[] args) {
        Thread a = new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"come in");
            LockSupport.park();//被阻塞
            System.out.println(Thread.currentThread().getName()+"被唤醒");
        },"a");
        a.start();


        new Thread(()->{
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName()+"通知了");
        },"b").start();

    }
}
