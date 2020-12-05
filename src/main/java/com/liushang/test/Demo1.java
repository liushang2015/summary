package com.liushang.test;

public class Demo1 {
    public static void main(String[] args) {
       Thread thread =new Thread(){
           @Override
           public void run(){
               p();
           }
       };
        thread.start();
        System.out.println("ping");

    }
    static void p(){
        System.out.println("pong");
    }
}
