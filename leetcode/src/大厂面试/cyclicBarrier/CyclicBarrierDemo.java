package 大厂面试.cyclicBarrier;

import java.util.Calendar;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier   和CountDownLatch相反，需要集齐七颗龙珠，召唤神龙。也就是做加法，开始是0，加到某个值的时候就执行
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        //CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{ System.out.println("********集齐七颗龙珠才能运行*********");});
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new Runnable() {
            @Override
            public void run() {
                System.out.println("召唤神龙");
            }
        });

        for (int i = 1; i <= 7; i++) {
            final int num = i;
            new Thread(()->{
                System.out.println("集齐了"+num+"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("000000000000");
            },String.valueOf(num)).start();
        }

    }
}
