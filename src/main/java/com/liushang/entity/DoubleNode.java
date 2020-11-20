package com.liushang.entity;

public class DoubleNode {
    public Object e;
    public DoubleNode next;
    public DoubleNode pre;
    public DoubleNode(){

    }
    public DoubleNode(Object e){
        this.e = e;
        next = null;
        pre = null;
    }
}
