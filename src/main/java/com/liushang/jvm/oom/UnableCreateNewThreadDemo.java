//package com.liushang.jvm.oom;
//
///**
// * @ProjectName: summary
// * @Package: com.liushang.jvm.oom
// * @ClassName: UnableCreateNewThreadDemo
// * @Author: LiuShang
// * @Description: 高并发请求服务器时,经常出现如下异常：Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
// *
// * 原因导致：
// * 1.  你的应用创建的太多线程了，一个应用进程创建多个线程，超过系统承载的极限
// * 2.  你的服务器并不允许你的应用程序创建这么多线程，linux系统默认允许单个进程可以创建的线程数是1024个，
// *      你的应用创建超过这个数量，就会报
// *
// * 解决办法：
// * 1.想办法降低你的应用程序创建线程的数量，分析应用是否真的需要创建这么多线程，如果不是，改代码线程数降到最低
// * 2.对于有的应用，确实需要创建很多线程，远超过Linux系统的默认个线程的限制，可以用过修改linux的配置，扩大linux默认限制
// *
// * 查看线程配置
// * vim /etc/security/limits.d/20-nproc.conf
// * *          soft    nproc     1024
// * root       soft    nproc     unlimited
// *
// * linux执行编译看效果
// * javac -d .UnableCreateNewThreadDemo.java
// * java com.liushang.jvm.oom.UnableCreateNewThreadDemo
// * @Date: 2020/11/19 17:09
// * @Version: 1.0
// */
//public class UnableCreateNewThreadDemo {
//    public static void main(String[] args) {
//        for (int i = 0;  ; i++) {
//            System.out.println("********** i ="+i);
//            new Thread(()->{
//                try {
//                    Thread.sleep(Integer.MAX_VALUE);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//    }
//
//}
