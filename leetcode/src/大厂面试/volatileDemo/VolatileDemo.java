package 大厂面试.volatileDemo;


class MyData{
     volatile int number = 0;

    public void addTo60(){
        this.number = 60;
    }
}
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"come in");
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"over update" + myData.number);
        },"aaa").start();

        while(myData.number == 0){
            //如果number==0 main线程会一直循环,不执行下面操作
        }

        System.out.println(Thread.currentThread().getName()+"number已更新");
    }
}
