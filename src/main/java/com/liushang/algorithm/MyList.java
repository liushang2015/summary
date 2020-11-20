package com.liushang.algorithm;

import com.liushang.entity.DoubleNode;
import com.liushang.entity.Students;

import java.util.Random;

public class MyList {
    private DoubleNode head;
    private DoubleNode tail;
    private int size = 0;

    public MyList() {
        head = new DoubleNode();
        tail = new DoubleNode();
        head.next =null;
        tail.pre = null;
    }

    public boolean empty() {
        if (head.next == null)
            return true;
        return false;
    }
    //找到所找下标节点的前一个节点
    public DoubleNode findpre(int index){
        DoubleNode rnode = head;
        int dex = -1;
        while(rnode.next != null){
            //找到了插入节点的上一个节点
            if( dex== index - 1){
                return rnode;
            }
            rnode = rnode.next;
            dex++;
        }
        return null;
    }
    public DoubleNode findthis(int index){
        DoubleNode rnode = head;
        //把rnode想象为指针，dex为指向的下标，这个地方很容易错，因为当指向最后一个节点时没有判断IF就跳出循环了
        int dex = -1;
        while(rnode.next != null){
            if(dex == index)
                return rnode;
            rnode = rnode.next;
            dex++;
        }
        if(dex == size - 1){
            return rnode;
        }
//        Node test = new Node(new Students("haha",1,2));
        return null;
    }

    // 往链表末尾加入节点
    public void add(Object e) {
        DoubleNode node = new DoubleNode(e);
        DoubleNode rnode = head;
        //如果是空链表的话插入一个节点，这个节点的pre不能指向上一个节点，必须指空
        if (this.empty()) {
            rnode.next = node;
            rnode.next.pre = null;
            tail.pre = node;
            size++;
        } else {
            while (rnode.next != null)
                rnode = rnode.next;
            rnode.next = node;
            node.pre = rnode;
            tail.pre = node;
            size++;
        }
    }
    //往链表的某一个标插入一个节点
    public boolean add(int index,Object e){
        if(index <0||index>=size)
            return false;
        DoubleNode node = new DoubleNode(e);
        DoubleNode prenode = this.findpre(index);
        node.next = prenode.next;
        prenode.next.pre = node;
        prenode.next = node;
        node.pre = prenode;
        size++;
        return true;
    }
    public boolean add(int index,MyList myl){
        if(index <0 || index >= size)
            return false;
        DoubleNode prenode = this.findpre(index);
//        myl.tail.pre.next = prenode.next;
//        prenode.pre = myl.tail.pre;
//        tail.pre = null;
//        prenode.next = myl.head.next;
//        myl.head.next.pre = prenode;
//        head.next = null;
        myl.tail.pre.next = prenode.next;
        prenode.next.pre = myl.tail.pre.pre;
        myl.head.next.pre = prenode.pre;
        prenode.next = myl.head.next;
        myl.head = null;
        myl.tail = null;
        size+=myl.size;
        return true;
    }

    public Object remove(int index){
        Object ob= this.get(index);
        if(index <0 || index >= size)
            return null;
        //特殊情况，当移除节点是最后一个节点的时候
        //较为复杂通过画图来写代码
        if(index == size - 1){
            DoubleNode prenode = this.findpre(index);
            this.tail.pre = this.tail.pre.pre;
            this.tail.pre.next.pre = null;
            this.tail.pre.next =null;
            size--;
            return ob;
        }
        //比较复杂，通过画图解决
        else{
            DoubleNode prenode = this.findpre(index);
            prenode.next = prenode.next.next;
            prenode.next.pre.next = null;
            prenode.next.pre = prenode.next.pre.pre;
            size--;
            return ob;
        }
    }


    public Object get(int index){
        DoubleNode thisnode = this.findthis(index);
        return thisnode.e;
    }
    public int size(){
        return size;
    }

    public static void main(String[] args) {
        String name = "";
        int credit;
        int age;
        int size;
        MyList myl = new MyList();
        Random random = new Random();
        size = random.nextInt(5) + 1;
        for (int i = 0; i < size; i++) {
            credit = random.nextInt(5);
            age = random.nextInt(5) + 18;
            for (int j = 0; j < 4; j++) {
                name += (char) (random.nextInt(26) + 97);
            }
            Students stu = new Students(name, credit, age);
            myl.add(stu);
            name = "";
        }

        System.out.println("Size of myl1 is "+ myl.size());
        for(int i = 0; i < myl.size() ;i++){
            Students stu2 = (Students) myl.get(i);
            stu2.show();
        }
//        //测试能否在链表末尾加入节点（成功）
//        for(int i = 0; i < myl.size() ;i++){
//            Students stu2 = (Students) myl.get(i);
//            stu2.show();
//        }
//        //测试能否通过下标加入一个节点（成功）
//        Students stu3 = new Students("cyt",5,18);
//        myl.add(1, stu3);
//        System.out.println("Size is "+ myl.size());
//        for(int i = 0; i < myl.size() ;i++){
//            Students stu2 = (Students) myl.get(i);
//            stu2.show();
//        }

        MyList myl2 = new MyList();
        size = random.nextInt(5) + 1;
        for (int i = 0; i < size; i++) {
            credit = random.nextInt(5);
            age = random.nextInt(5) + 18;
            for (int j = 0; j < 4; j++) {
                name += (char) (random.nextInt(26) + 97);
            }
            Students stu2 = new Students(name, credit, age);
            myl2.add(stu2);
            name = "";
        }
        System.out.println("Size is of myl2 "+ myl2.size());
        for(int i = 0; i < myl2.size() ;i++){
            Students stu2 = (Students) myl2.get(i);
            stu2.show();
        }



        myl.add(1, myl2);
        System.out.println("Size is of myl1 "+ myl.size());
        for(int i = 0; i < myl.size() ;i++){
            Students stu2 = (Students) myl.get(i);
            stu2.show();
        }


    }
}
