package com.liushang.jvm.oom;

import java.nio.ByteBuffer;

/**
 * 配置参数：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 故障现象：Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 *
 * 原因导致：
 * 写NIO程序经常使用ByteBuffer来读取或者写入数据,这是一种基于通道（Channel）与缓冲区（Buffer）的I/O方式
 * 它可以使用Native函数库直接分配堆外内存，然后通过一个存储在java堆里面的DirectBuffer对象这块内存的引用操作
 * 这样能在一些场景中显著提高性能，因为避免了在java堆和Native堆中来回复制数据
 *
 * ByteBuffer.allocate(capability)第一种方式是分片JVM堆内存，属于GC管辖范围，由于需要拷贝所以速度相对较慢
 *
 * ByteBuffer.allocteDirect(capability)第一种方式是分配OS本地内存，不属于GC管辖范围，由于不需要内存拷贝所以速度相对较快
 *
 * 但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC，DirectByteBuffer 对象们就不会被回收，
 * 这时候堆内存充足，但本地内存可能已经使用光了，再次尝试分配本地内存就会出现OutOfMemoryError，那程序就直接崩溃了。
 * @ProjectName: summary
 * @Package: com.liushang.jvm.oom
 * @ClassName: DirectBufferMemoryDemo
 * @Author: LiuShang
 * @Description:
 * @Date: 2020/11/19 16:33
 * @Version: 1.0
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory:"+(sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"MB");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //配置的-XX:MaxDirectMemorySize=5m 实际使用的6m，故意使坏
        ByteBuffer bb = ByteBuffer.allocateDirect(6*1024*1024);
    }
}
