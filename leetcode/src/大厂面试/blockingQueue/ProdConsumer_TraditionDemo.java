package 大厂面试.blockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 题目:一个初始值为零的变量,两个线程对其交替操作,一个加一,一个减一,来回五轮
 *
 * 生产者,消费者2.0
 *
 * 循环判断必须使用while,如果使用if的话,会出现虚假唤醒
 */

class ShareData{
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try {
            //1.判断
            while (num !=0){
                //等待,不能生产
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //2.干活
            num++;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            //3.通知唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void decrement(){
        lock.lock();
        try {
            while(num == 0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }

}

public class ProdConsumer_TraditionDemo {



    public static void main(String[] args) {

        ShareData shareData = new ShareData();


        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                shareData.increment();
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                shareData.decrement();
            }
        },"BB").start();
    }
}
