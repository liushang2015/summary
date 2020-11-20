本项目为java进阶训练
1.OutOfMemoryError异常的制造6种过程
包名：com.liushang.jvm.oom 

1.1 StackOverfolwErrorDemo.java 
报错信息：java.lang.StackOverflowError
解释：如果某个线程的线程栈空间被耗尽，没有足够资源分配给新创建的栈帧，就会抛出

1.2 JavaHeapSpaceDemo.java 
报错信息：java.lang.OutOfMemoryError: Java heap space
解释：堆内存不足

1.3 MetaspceOOMDemo.java 
报错信息：java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread
解释：占据的时间总是会超过Metespace指定的空间大小

1.4 UnableCreateNewThreadDemo.java
报错信息：java.lang.OutOfMemoryError: unable to create new native thread
解释：创建的线程太多，超过系统承载的极限

1.5 DirectBufferMemoryDemo.java
报错信息:java.lang.OutOfMemoryError: Direct buffer memory
解释：分配OS本地内存已经使用光了