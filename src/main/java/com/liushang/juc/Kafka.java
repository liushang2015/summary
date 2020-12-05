package com.liushang.juc;

import sun.security.krb5.internal.TGSRep;

import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * main线程往队列写数据
 * 3个工作线程读队列数据
 * 当主线程主动发起不写了，如何让让其他3个线程停止
 *
 * 输入false让其他3个线程阻塞
 * 输入true让其他3个线程启动
 */
public class Kafka {
    private static volatile Boolean run = true;
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();               //创建条件变量
    private static BlockingDeque blockingDeque = new LinkedBlockingDeque();

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (run == false) {
                    lock.lock();
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }

                try {
                    System.out.println(Thread.currentThread().getName() + " : " + blockingDeque.takeFirst());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            while (true) {
                if (run == false) {
                    lock.lock();
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }

                try {
                    System.out.println(Thread.currentThread().getName() + " : " + blockingDeque.takeFirst());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t2");
        Thread t3 = new Thread(() -> {
            while (true) {
                if (run == false) {
                    lock.lock();
                    try {
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }

                try {
                    System.out.println(Thread.currentThread().getName() + " : " + blockingDeque.takeFirst());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t3");
        t1.start();
        t2.start();
        t3.start();


        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print("请输入：");
            String str = input.next();
            if (str.equals("false")) {
                run = false;

            }else{
                blockingDeque.add(str);
            }

            if (run == false && str.equals("true")) {
                run = true;
                lock.lock();
                try {
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }

            }


        }
    }

}
