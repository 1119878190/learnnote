package 大厂面试.ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lx
 * @date 2021/1/19 11:03
 *
 * 创建线程的方式
 * 1.继承Thread类
 * 2.实现Runnable接口
 * 3.实现Callable接口  可以创建线程,与Runnable不同的是,Callable有返回值
 * 4.线程池
 */

class MyThrea implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("come in callable");
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThrea());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThrea());

        new Thread(futureTask,"AAA").start();
        new Thread(futureTask,"BBB").start();//如果多个线程共用一个FutureTas,那么只执行一次
        new Thread(futureTask2,"AAA").start();//多个线程使用不同的FutureTask,多次执行


        int result1 = 10;
        System.out.println(Thread.currentThread().getName());

        while (!futureTask.isDone()){
            //为了不让main线程阻塞,可以在这里循环判断,或者将futureTask.get();写在后面
        }

        int rusult2 = futureTask.get();
        System.out.println(result1+rusult2);

    }
}
