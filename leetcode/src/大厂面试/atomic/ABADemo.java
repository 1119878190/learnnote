package 大厂面试.atomic;

import jdk.management.resource.internal.inst.InitInstrumentation;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {  //CAS   ABA问题的解决  AtomicStampedReference

    public static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);


    public static void main(String[] args) {

        new Thread(()->{
            int stap = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t拿到的版本号是"+stap);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,stap,stap+1);
            System.out.println(Thread.currentThread().getName()+"\t第二次版本号是"+atomicStampedReference.getStamp()+"\t"+atomicStampedReference.getReference());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t第三次版本号是"+atomicStampedReference.getStamp()+"\t"+atomicStampedReference.getReference());


        },"t1").start();

        new Thread(() ->{
            int stap = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t拿到的版本号是"+stap);

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicStampedReference.compareAndSet(100,2021,stap,stap+1);
            System.out.println(Thread.currentThread().getName()+"\t当前版本号是"+atomicStampedReference.getStamp()+"修改是否成功"+result+"\t"+atomicStampedReference.getReference());


            HashMap<Integer,String> m = new HashMap<>();

        },"t2").start();
    }
}
