package queue;

import java.util.Scanner;

//环形队列  取模
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试
        //创建一个队列
        CircleArray arrayQueue = new CircleArray(4);//这里设置了4 ，其对列有效数据只有3，有一个空间作为约定
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//接受一个字符串；
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case  'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据时%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");

    }
}


class CircleArray{

    private int maxSize;//表示数组的最大容量
    private int front;//指向队列的第一个元素
    private int rear;//指向最后一个元素的后一个位置
    private int arr[];//数据


    public CircleArray(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //是最后一个元素得 后一个位置 这个位置是空的 约定的 不存储数据 所以 rear+1按照环形来理解就是回去了
    public boolean isFull(){
        return (rear +1) % maxSize == front;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据
    public void addQueue(int num){
        //判断队列是否满
        if (isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = num;
        //将rear后移，这里必须考虑取模
        rear = (rear +1) % maxSize;
    }

    //取数据
    public int getQueue(){
        //判断是否为空
        if (isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列为空，不能取数据");
        }
        //front指向第一个元素
        //1.先把front 对应的值保存到一个临时变量
        //2.将front后移
        //3.零时保存的变量返回
        int value = arr[front];
        front = (front +1) % maxSize;
        return value;
    }

    //显示队列所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列空的，没有数据");
            return;
        }
        //思路：从front开始遍历，便利多少个元素？？？？
        for(int i = front; i < front + size();i++){
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }

    }

    //求出当前队列有效数据的个数
    public int size(){
        return (rear - front + maxSize) % maxSize;
    }

    //显示队列的头部数据,注意不是取出数据
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空的，没有数据");
        }
        return arr[front];
    }

}