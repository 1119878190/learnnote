package 大厂面试.ThreadPool;

import java.util.concurrent.*;

/**
 * 线程池异常处理方式
 * 1.创建自定义异常类实现Thread.UncaughtExceptionHandler
 * 2.创建自定义线程工厂设置其UncaughtExceptionHandler为自定义的
 * 3.在创建线程池的时候工厂为自定义工厂
 */
public class ThreadsExceptionDemo implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getName()+"出现了异常=================");
        e.printStackTrace();
    }
}


class ThreadDemo{
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                3,
                6,
                10L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                new HanlderThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()

        );

        executorService.execute(()->{
            throw new RuntimeException();
        });
    }
}

class HanlderThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this+"creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created "+t);
        t.setUncaughtExceptionHandler(new ThreadsExceptionDemo());//设定线程工厂的异常处理器
        System.out.println("eh="+t.getUncaughtExceptionHandler());
        return t;
    }
}