package 数据结构与算法.排序.Comparable;

import 数据结构与算法.排序.Comparable.Person;

/**
 * @author lx
 * @date 2021/2/3 17:41
 */
public class ComparableDemo {


    public static void main(String[] args) {
        Person p1 = new Person("zhangsan",10);
        Person p2 = new Person("lisi",20);
        Comparable max = getMax(p1, p2);
        System.out.println(max);

    }

    public static Comparable getMax(Comparable x,Comparable y){
        int result = x.compareTo(y);
        if (result>0){
            return x;
        }else {
            return y;
        }
    }
}
