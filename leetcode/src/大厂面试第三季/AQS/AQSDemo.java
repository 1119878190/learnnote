package 大厂面试第三季.AQS;

import 大厂面试.lock.ReentrantLockDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lx
 * @date 2021/2/22 16:26
 */
public class AQSDemo {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();

        reentrantLock.unlock();
    }
}
