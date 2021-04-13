package 大厂面试.lock;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock与Sychronized的区别
 * 1.Sychronized是关键字,而Lock是接口    Sychronized是JVM层面的   monitorenter   monitorexit
 * 2.Sychronized不需要手动释放锁,而Lock要在finally中手动释放
 * 3.Sychronized如果没有获取锁会一直获取,而Lock的trylock()方法,在一段时间内没有获取,会停止获取
 *  * 4.Sychronized是非公平锁,Lock默认是非公平锁,如果实例化的时候传入true为公平锁
 *  * 5.Lock可以精确唤醒线程Condition,而Sychronized要么随机获取一个,要么全部获取
 *  *
 */


/**
 * Lock精确唤醒    A--->B--->C--->A--->B--->C
 * 1.标志位   2.同一把锁的Condition
 *
 */
class ShareResource{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();//c1 c2 c3 同一把锁
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print5(){
        lock.lock();
        try {
            while (number != 1){
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t");
            }
            number = 2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while (number != 2){
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t");
            }
            number = 3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while (number != 3){
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t");
            }
            number = 1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        },"C").start();



    }
}
