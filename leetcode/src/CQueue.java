import jdk.nashorn.internal.ir.IdentNode;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Stack;

public class CQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public CQueue(){
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value){
        stack1.push(value);
    }

    public int deleteHead(){
        if (stack1.empty())
            return -1;
        while (stack1.size() != 1){
            stack2.add(stack1.pop());
        }
        int temp = stack1.pop();
        while (!stack2.empty()){
            stack1.add(stack2.pop());
        }
        return temp;
        
    }
}
