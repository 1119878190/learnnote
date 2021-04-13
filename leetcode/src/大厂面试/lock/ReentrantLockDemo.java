package 大厂面试.lock;

import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 可重入锁(递归锁) : 同一个线程,可以进入任意一个它已经拥有锁所同步的代码快
 *            也就是说同步方法李调用同步方法,可以自动获取锁
 *
 * ReentrantLock/Synchronzied 是非公平的可重入锁
 */
class Phone{
    public synchronized void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getId()+"invoke sendSmS");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getId()+"------invoke----- sendEmail");
    }


}
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();

       Thread t1 =  new Thread(){
            @Override
            public void run() {
                try {
                    phone.sendSMS();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
       t1.setName("t1");
       t1.start();

       new Thread(()->{
           try {
               phone.sendSMS();
           } catch (Exception e) {
               e.printStackTrace();
           }
       },"t2").start();



    }


}
