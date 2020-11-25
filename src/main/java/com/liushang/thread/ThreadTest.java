package com.liushang.thread;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          ThreadLocal<DateFormat> sdf2 = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        for (int i = 0; i < 20; i++) {
            Thread t1 = new Thread(() -> {
                try {
                    System.out.println(sdf2.get().parse("2020-11-25 10:16:00"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
            t1.start();
        }
    }
}
