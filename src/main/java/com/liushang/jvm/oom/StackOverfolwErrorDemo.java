package com.liushang.jvm.oom;

/**
 * Exception in thread "main" java.lang.StackOverflowError
 * 原因：深度的方法调用会出现栈爆了
 */
public class StackOverfolwErrorDemo {
    static int i = 0;

    public static void main(String[] args) {

        stackOverfolwErrorDemo();
    }

    private static void stackOverfolwErrorDemo() {
            stackOverfolwErrorDemo();
    }
}
