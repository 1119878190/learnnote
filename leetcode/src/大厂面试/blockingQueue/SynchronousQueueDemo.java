package 大厂面试.blockingQueue;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.*;

/**
 * SychronousQueue是一个不存储元素的BlockingQueue
 *  每一个put操作必须要等待一个take操作,否则不能继续添加元素,反之亦然
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put("1");

                System.out.println(Thread.currentThread().getName()+"\t put 2");
                blockingQueue.put("2");

                System.out.println(Thread.currentThread().getName()+"\t put 3");
                blockingQueue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"aaa").start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t get"+blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t get"+blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"\t get"+blockingQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"bbb").start();



    }
}
