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


CPU占用过高的定位分析思路
1.先用top命令找出cpu占比最高的
2.ps -ef或者jps进一步定位，得知是一个怎样的后台程序
2.1 命令：(jps -l)
2.2 命令：(ps -ef |grep java |grep -v grep)
2.3 查询带的进程id是5101
3.定位到具体的线程代码
3.1 命令：(ps -mp 5101(进程id) -o THREAD,tid,time) 
3.2 查询到的tid是5102
3.3 参数解释：-m显示所有线程，-p pid进程使用cpu的时间，o该参数后是用户自定义格式
4.将需要的线程ID转换为16进制格式（英文小写格式）
4.1 5102转16进制是13EE
5.jstack 5101(进程id)  | grep tid 13EE(16进制线程ID小写英文) -A60
5.1 可以看到定位的出问题的代码行数