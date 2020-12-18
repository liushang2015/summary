package com.liushang.thread;



import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * DateFormat的parse没有做安全处理，不能保证原子性，所以在多线程操作存在线程安全问题，
 * 就是因为有多个线程同时去操作SimpleDateFormat
 * 多个线程共享同一个线程
 */
public class ThreadLocalTest {
//    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ThreadLocal<DateFormat> sdf2 = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(1,10,0L, TimeUnit.MICROSECONDS,new LinkedBlockingDeque<Runnable>(10),threadFactory);

        for (int i = 0; i < 15; i++) {
            threadPoolExecutor.execute(()->
                    {
                        try {
//                            System.out.println(sdf.parse("2020-11-25 09:49:00"));
                            System.out.println(Thread.currentThread().getId()+":"+sdf2.get().parse("2020-11-25 09:49:00"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        threadPoolExecutor.shutdown();
    }
}
