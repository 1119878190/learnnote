package 大厂面试.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁: 循环不停的尝试获取锁,避免了阻塞,单消耗cpu
 */
public class SpinLockDemo {

    //原子线程引用
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"come in");
        while(!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){

        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"come out");
    }


    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"---a---").start();

        //暂停一会
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"---b---").start();
    }
}
