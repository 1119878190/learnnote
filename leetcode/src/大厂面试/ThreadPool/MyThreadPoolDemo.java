package 大厂面试.ThreadPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.concurrent.*;

/**
 * @author lx
 * @date 2021/1/19 15:42
 *
 * 第四种使用/获得java多线程的方式,线程池
 * newFixedThreadPool
 * newSingleThreadExecutor
 * newCacheThreadPool
 *
 * 以下所有拒绝策略都实现了RejectedExecutionHandler接口
 * AbortPolicy：默认，直接抛出RejectedExcutionException异常，阻止系统正常运行
 * CallerRunsPolicy：该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者
 * DiscardPolicy：直接丢弃任务，不予任何处理也不抛出异常，如果运行任务丢失，这是一种好方案
 * DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
 *
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        try {
            //模拟用户来办理业务,每个用户就是一个来自外部的请求线程
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    public static void threadPoolInit(){
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个线程
        //ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个线程

        Integer a = new Integer(3);
        Integer b = new Integer(4);
        System.out.println(a.equals(b));


    }
}
