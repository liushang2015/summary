//package com.liushang.jvm.oom;
//
//import org.springframework.cglib.proxy.Enhancer;
//import org.springframework.cglib.proxy.MethodInterceptor;
//import org.springframework.cglib.proxy.MethodProxy;
//
//import java.lang.reflect.Method;
//
///**
// * JVM参数
// * -XX:MetaspaceSize=8 -XX:MaxMetaspaceSize=8
// *
// * @ProjectName: summary
// * @Package: com.liushang.jvm.oom
// * @ClassName: MetaspceOOMDemo
// * @Author: LiuShang
// * @Description: java8 以之后的版本使用Metaspace来替代永久代
// * Metaspace是方法区在HotSpot中实现的，它与持久带最大的区别在于Metaspace并不在虚拟中内存中而是使用本地
// * 也即在java8中,classe metadata(the virtual machines internal presentation of java class),被存储在叫做
// * Metaspace的natice memory
// * Exception: java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "main"
// * <p>
// * 永久代（java8后被元空间Metaspace取代了）存放了以下信息
// * <p>
// * 虚拟机加载的类信息
// * 常量池
// * 静态变量
// * 即时编译后的代码
// * <p>
// * 模拟Mataspace空间溢出，我们不断生产往元空间罐，类占据的时间总是会超过Metespace指定的空间大小
// * @Date: 2020/11/19 17:32
// * @Version: 1.0
// */
//public class MetaspceOOMDemo {
//    static class OOMTest {
//    }
//
//    public static void main(final String[] args) {
//        int i = 0;
//        try {
//
//
//            while (true) {
//                i++;
//                Enhancer enhancer = new Enhancer();
//                enhancer.setSuperclass(OOMTest.class);
//                enhancer.setUseCache(false);
//                enhancer.setCallback(new MethodInterceptor(){
//                    @Override
//                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                        return methodProxy.invokeSuper(o,args);
//                    }
//                });
//                enhancer.create();
//            }
//        } catch (Throwable e) {
//            System.out.println("********多少次后会发生异常" + i);
//            e.printStackTrace();
//        }
//    }
//}
