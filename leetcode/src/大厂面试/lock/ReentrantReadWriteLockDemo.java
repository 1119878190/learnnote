package 大厂面试.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁  ReentrantReadWriteLock 既能保证数据的一致性,也可以保证并发的写
 */
class MyCatch{

    private volatile Map<Integer,Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(Integer key,Object value){
        readWriteLock.writeLock().lock();
        try {
            readWriteLock.writeLock();
            System.out.println(key+"正在写入"+key);
            map.put(key,value);
            System.out.println(key+"写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(int key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(key+"正在读取");
            Object result = map.get(key);
            System.out.println(key+"读取完成"+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }

}

public class ReentrantReadWriteLockDemo {


    public static void main(String[] args) {
       MyCatch myCatch = new MyCatch();

        //写
        for (int i = 0; i < 5; i++) {
            final int key = i;
            new Thread(()->{
                myCatch.put(key,key);
            },String.valueOf(i)).start();
        }

        //读
        for (int i = 0; i < 5; i++) {
            final int key = i;
            new Thread(()->{
                myCatch.get(key);
            },String.valueOf(i)).start();
        }
    }
}
