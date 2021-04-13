package LinkedList;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

//单链表  增删改查  直接添加到尾部，没有顺序
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero3);

        //显示
        singleLinkedList.list();

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2,"小鹿","我是一只路");
        singleLinkedList.update(newHeroNode);

        System.out.println("修改后的链表情况");

        //显示
        singleLinkedList.list();

        //删除节点
        singleLinkedList.delete(1);
        singleLinkedList.delete(2);
        singleLinkedList.delete(3);
        singleLinkedList.delete(4);
        System.out.println("删除后链表情况");
        singleLinkedList.list();

    }

}

//定义SingleLikedList 管理我们的英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动,不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");


    /**
     *   //添加节点到单项链表 【没有顺序】
     *     //思路，当不考虑编号的顺序时
     *     //1.找到当前链表的最后节点
     *     //2.将最后这个节点的next指向新的节点
     * @param heroNode
     */
    public void add(HeroNode heroNode){

        //应为head节点不能动，英雌我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true){
            if (temp.next == null){
                break;
            }
            //如果没有找到最后，将temp往后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        temp.next = heroNode;
    }

    /**
     *   //第二种添加，【有顺序】在添加英雄是，根据排名将英雄插入到指定位置
     *     //例如：  1  3  4  要在1和3之间出入2  将2的next指向3  然后将1的next指向2  temp = 1;
     *     //(如果有这个排名，则添加失败，并给出提示信息)
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加位置
        //因为单链表，我们找到的temp必须是要添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//标识添加的标号是否存在，默认为false
        while (true){
            if (temp.next == null){//说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no){//位置找到，就在temp的后面插入
                break;
            }else if(temp.next.no == heroNode.no){//说明添加的heronode的编号已经存在
                flag = true;//说明编号存在
                break;
            }

            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag的值
        if (flag){//不能添加，说明编号存在
            System.out.printf("准备插入英雄的编号%d已经存在，不能加入\n",heroNode.no);
        }else {
            //插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     *    //修改节点的信息，根据no编号来修改，即no编号不能改
     *     //说明
     *     //1.根据newHeroNode 的no 来修改即可
     * @param heroNode
     */
    public void update(HeroNode heroNode){
        //判断是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }

        //找到需要修改的节点,根据no编写
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true){
            if (temp == null){
                break;//链表已经遍历结束
            }
            if (temp.no == heroNode.no){
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag){
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else {
            //没有找到
            System.out.printf("没有找到编号 %d 的节点,不能修改\n",heroNode.no);
        }
    }


    /**
     *
     * 删除节点
     * 1.head不能动,因此我们需要一个temp辅助节点找到待删除节点的前一个节点
     * 2.说明我们在比较时,是temp.next.no  和 需要删除的节点的no比较
     */
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;//表示是否找到待删除节点
        while (true){
            if (temp.next == null){
                //已经到最后
                break;
            }
            if (temp.next.no == no){
                //找到了待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;//temp后移
        }

        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.printf("要删除的 %d这个节点不存在",no);
        }
    }

    /**
     * //显示链表【遍历】
     */
    public void list(){
        //判断链表是否为空
        if (head.next == null){
            System.out.println("链表尾空");
            return;
        }
        //应为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true){
            if (temp == null){
                break ;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp指向下一个next
            temp = temp.next;
        }

    }
}

//定义一个HeroNode，每一个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点
    //构造器
    public HeroNode(int no,String hName,String hNickName){
        this.no = no;
        this.name = hName;
        this.nickname = hNickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }


}
