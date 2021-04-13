package 大厂面试.ThreadLocal;

/**
 * @author lx
 * @date 2021/1/29 18:41
 *
 * ThreadLocal :为每一个线程创建副本,每个线程的数据都是独享的,线程之间不互相影响.
 *应用场景 Spring@transactional 控制connection
 * 将connection放到threadloccalmap里面,让在一个service方法里里调用多个dao方法的调用必须是同一个链接,保证事务
 *
 *
 *  public void set(T value) {
 *         Thread t = Thread.currentThread();
 *         ThreadLocalMap map = getMap(t);
 *         if (map != null)
 *             map.set(this, value);     set的方法的key是ThreadLocal
 *         else
 *             createMap(t, value);
 *     }
 *
 */
public class ThreadLocalDemo {

    static ThreadLocal<Person> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            threadLocal.set(new Person("zhangsan"));
        }).start();

        new Thread(()->{
            System.out.println(threadLocal.get());
        }).start();


    }
}


class Person{
    String name;
    public Person(String name){
        this.name = name;
    }

}
