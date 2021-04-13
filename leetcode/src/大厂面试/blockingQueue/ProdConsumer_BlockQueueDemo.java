package 大厂面试.blockingQueue;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lx
 * @date 2021/1/19 9:59
 * 生产者消费者 3.0
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/原子访问
 */


class MyResource{

    private volatile boolean FLAG = true;//默认开启,进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    //生产
    public void myPro() throws Exception{
        String data = null;
        boolean retValue;
        // 多线程环境的判断，一定要使用while进行，防止出现虚假唤醒
        // 当FLAG为true的时候，开始生产
        while (FLAG){
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+"\t插入队列成功"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t插入队列失败"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t老板叫停 FLAG=FALSE");
    }

    //消费
    public void myConsumer() throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"超过2秒没有取到蛋糕,消费推出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName()+"消费者取到蛋糕"+result+"成功");
        }
    }

    public void stop(){
        this.FLAG = false;
    }

}
public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(3));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            System.out.println();
            System.out.println();
            try {
                myResource.myPro();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();


        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        //暂停一会
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("5秒钟时间到,大佬停止mian");
        myResource.stop();
    }
}
