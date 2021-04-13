package 大厂面试.countDownLatch;

import 大厂面试.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch   计数器,直到减为0时,其它线程才可以执行
 */

public class CountDownLatchDemo {


    public static void main(String[] args) {
        CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
        try {
            countDownLatchDemo.closeDoor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDoor() throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            final int num = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t被灭了");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getReMessage()).start();//枚举
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"6位同学都走了,班长锁门");
    }

}
