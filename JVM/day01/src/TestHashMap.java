import org.omg.CORBA.IRObject;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class TestHashMap {

    public static void main(String[] args) {
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"张三");
        map.put(2,"李四");
        map.put(3,"王五");

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        for (Integer key : map.keySet()){
            System.out.println("key:"+key+","+"value:"+ map.get(key));
        }
        System.out.println("=============================");

        for (Map.Entry<Integer,String> key : map.entrySet()){
            System.out.println(key);
        }
        System.out.println("##################################");


        for (Integer integer:list){
            System.out.println(integer);
        }
        System.out.println("######################");

        Iterator iterator  = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        AtomicInteger atomicInteger = new AtomicInteger();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add(123);

    }
}
