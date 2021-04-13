import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public  class ABS  {



    public static void main(String[] args) {
        HashMap map = new HashMap();

        map.put(1,"1");
        map.put(2,"2");
        System.out.println(map);

        int a = 4;
        Integer b = new Integer(4);
        System.out.println(a ==b);


        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    }


}
