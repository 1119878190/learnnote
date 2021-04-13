package LinkedList.demo;

import java.text.SimpleDateFormat;
import java.time.temporal.Temporal;

/**
 * @author lx
 * @date 2021/2/4 10:35
 *
 * 1.获取单链表的节点个数(如果是带头节点的链表,需求不统计头节点)------getLength()方法
 * 2.查找单链表中的倒数第K个节点
 * 3.反转链表    https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
 *4. 从尾到头打印链表  https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 * 5. 合并两个有序链表  https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 */
public class Demo1 {


    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        SingleLinked singleLinked = new SingleLinked();
        singleLinked.add(hero1);
        singleLinked.add(hero2);
        singleLinked.add(hero3);
        singleLinked.add(hero4);

        singleLinked.list();
        System.out.println("链表的节点个数为:"+singleLinked.getLength(singleLinked.getHead()));

        System.out.println(singleLinked.findLastIndexNode(singleLinked.getHead(), 4));



    }




}


class SingleLinked{

    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加
     * @param heroNode
     */
    public void add(HeroNode heroNode){
        HeroNode tmp = head;
        while (true){
            if (tmp.next == null){
                break;
            }
            tmp = tmp.next;
        }
        tmp.next = heroNode;
    }

    /**
     * 1.获取单链表的节点个数(如果是带头节点的链表,需求不统计头节点)
     * @param head
     * @return
     */
    public  int getLength(HeroNode head){

        if(head.next == null){
            return 0;
        }
        int length = 0;
        //辅助节点,不包括头节点
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            //继续指向下一个node遍历
            cur = cur.next;
        }
        return length;

    }

    /**
     * 2.查找单链表中的倒数第K个节点
     */
    public HeroNode findLastIndexNode(HeroNode heroNode,int index){
        if (heroNode.next == null){
            return null;
        }
        //第一次遍历得到链表的长度
        int size = getLength(heroNode);
        //第二次遍历 size - index
        if (index <=0 || index > size){
            return null;
        }
        HeroNode cur = heroNode.next;
        for (int i = 0; i < size-index;i++){
            cur = cur.next;
        }
        return cur;
    }


    /**
     * 便利
     */
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        //辅助节点不包括头节点
        HeroNode tmp = head.next;
        while (true){
            if (tmp == null){
                break;
            }
            System.out.println(tmp);
            tmp = tmp.next;
        }

    }

}

/**
 * 定义一个HeroNode，每一个HeroNode对象就是一个节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    /**
     *  构造器
     * @param no
     * @param hName
     * @param hNickName
     */
    public HeroNode(int no, String hName, String hNickName) {
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