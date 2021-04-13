import java.util.ArrayList;
import java.util.List;

public class HeapDemo {


    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        String s = "hahah+";
        while (true){
            list.add(s);
            s = s + s ;
            i ++;
        }
    }
}
