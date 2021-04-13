package 大厂面试.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore  信号量
 * 主要用于两个目的
 *
 * 一个是用于共享资源的互斥使用
 * 另一个用于并发线程数的控制
 *
 * 初始化一个信号量为3，默认是false 非公平锁， 模拟3个停车位
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3,false);//6辆车 抢3个车位

        for (int i = 1; i <=6; i++) {
            final int num = i;
            new Thread(()->{
                try {
                    semaphore.acquire();//抢占
                    System.out.println("第"+Thread.currentThread().getName()+"辆车抢到了车位");
                    try {//停3秒
                        TimeUnit.SECONDS.sleep(3);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("第"+Thread.currentThread().getName()+",停了三秒钟,输出停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(num)).start();
        }
    }
}
