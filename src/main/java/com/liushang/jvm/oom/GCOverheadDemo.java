package com.liushang.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: summary
 * @Package: com.liushang.jvm.oom
 * @ClassName: GCOverheadDemo
 * @Author: LiuShang
 * @Description: Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 * JVM 参数配置演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 * GC回收时间过长会抛出OutOfMemroyError.过长的定义是，超过98%的时间用来做GC并且回收不到2%的堆内存
 *   连续多次GC都只回收了不到2%的极端情况下才会抛出。假如不跑出GC Overhead limit 错误会发生什么情况呢?
 *   那就是清理的这么点内存很快再次填满，迫使GC再次执行，这样就形成了恶性循环，
 *   CPU使用率一直都是100%，而GC却没有任何成果
 * @Date: 2020/11/19 15:50
 * @Version: 1.0
 */
public class GCOverheadDemo {
    public static void main(String[] args) throws Throwable {
        int i=0;
        List<String> list = new ArrayList<String>();

        try {
          while (true){
              list.add(String.valueOf(++i).intern());
          }
        }catch (Throwable e){
            System.out.println("***********i:"+i);
            e.printStackTrace();
            throw e;
        }
    }
}
