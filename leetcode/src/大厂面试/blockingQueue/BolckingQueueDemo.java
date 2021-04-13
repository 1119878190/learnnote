package 大厂面试.blockingQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 四组方法
 *
 * 1.抛出异常 add()  element()  remove()   当队列满的时候,或者空的时候,添加或者取出数据会报错
 *
 * 2.特殊值 offer()  peek()    poll()    当队列满的时候,或者空的时候,添加返回false,取出数据返回null
 *
 *3.阻塞  put()  take()     当队列满的时候,或者空的时候,添加或者取出数据会一直阻塞
 *
 * 4.超时  offer(e,time,unit) poll()   当阻塞队列满时,队列会阻塞生产者线程一点时间,超过后限时后生产者线程会退出
 *
 */
public class BolckingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);//有界队列,需要指定大小
        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(3);//有界队列,需要指定大小
        BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<>(3);//有界队列,需要指定大小
        BlockingQueue<String> blockingQueue4 = new ArrayBlockingQueue<>(3);//有界队列,需要指定大小


        System.out.println("==================第一组方法 异常:add(),element(),remove===========================");

        System.out.println(blockingQueue.add("a"));//返回boolean类型
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));


        //System.out.println(blockingQueue.add("x"));//不能再添加了,队列满的时候,往里添加会出现Queue full异常

        System.out.println(blockingQueue.element());//打印第一个元素

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        //System.out.println(blockingQueue.remove());//当队列空的时候,去数据出现NoSuchElementException

        System.out.println("====================第二组方法 特殊值:offer(),peek(),poll()=============================");
        System.out.println(blockingQueue2.offer("a"));
        System.out.println(blockingQueue2.offer("b"));
        System.out.println(blockingQueue2.offer("c"));

        System.out.println(blockingQueue2.offer("x"));//队列满了 ,再往里添加不能添加,也不报错,但会天健失败 false

        System.out.println(blockingQueue2.peek());//打印第一个元素

        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll());

        System.out.println(blockingQueue2.poll());//队列为空时,再取数据,返回null

        System.out.println("===================第三组方法 阻塞:put(),take()=============================");

        blockingQueue3.put("a");
        blockingQueue3.put("a");
        blockingQueue3.put("a");

       // blockingQueue3.put("x");//队满的时候,往里添加数据,会出现一直阻塞

        blockingQueue3.take();
        blockingQueue3.take();
        blockingQueue3.take();

        //blockingQueue3.take();//队列空时,再取数据,也会一直阻塞


        System.out.println("=========================第四组 超时:offer(e,time,unit),poll()============================");

        blockingQueue4.offer("a",2L, TimeUnit.SECONDS);
        blockingQueue4.offer("a",2L, TimeUnit.SECONDS);
        blockingQueue4.offer("a",2L, TimeUnit.SECONDS);
        blockingQueue4.offer("a",2L,TimeUnit.SECONDS);

    }
}
