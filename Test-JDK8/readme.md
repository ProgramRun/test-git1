# DateTimeAPI

## 实体类介绍

### Instant

	Instant immutable and thread-safe,代表时间轴上某一唯一的时间点(最小单位纳秒);为了表示时间的准确性,使用long记录秒值(以1970-01-01T00:00:00Z为0计算,之前为负,之后为正),使用int记录纳秒值;

### LocalDate

	LocalDate immutable and thread-safe, 代表ISO-8601日历体系中*无时区*日期(例2018-09-05,最小单位为日);可用于表示生日

### LocalDateTime

	LocalDateTime immutable and thread-safe,代表ISO-8601日历体系中*有时区*的的时间(例2007-12-03T10:15:30,最小单位为秒)

### LocalTime

	LocalTime immutable and thread-safe,代表ISO-8601日历体系中*无时区*的的时间(例10:15:30)

### DateTimeFormatter

	格式化解析和打印date-time的工具类;可以通过Local,Chronology, ZoneId, and DecimalStyle创建;
	
	主要的方法有两个,format(格式化)和parse(\解析)





# Lambda表达式

参考文献: 

1. https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/Lambda-QuickStart/index.html







# 垃圾回收器

参考文献:

1. https://www.cnblogs.com/andy-zcx/p/5522836.html

## 判断对象是否被引用--根搜索算法(GC Root)

![img](http://ww1.sinaimg.cn/mw690/7178f37egw1etbmyb4jugj20ku0ajmy0.jpg)

	从一个节点GC ROOT开始，寻找对应的引用节点，找到这个节点以后，继续寻找这个节点的引用节点，当所有的引用节点寻找完毕之后，剩余的节点则被认为是没有被引用到的节点，即无用的节点.
	
	java中可作为GC Root的对象有
	
	1.虚拟机栈中引用的对象（本地变量表）
	
	2.方法区中静态属性引用的对象
	
	3.方法区中常量引用的对象
	
	4.本地方法栈中引用的对象（Native对象）

## 垃圾回收算法

### 标记-清除算法

![img](http://ww1.sinaimg.cn/mw690/7178f37egw1etbmyakm6pj20fi0crmxm.jpg)

	标记-清除算法采用从根集合进行扫描，对存活的对象对象标记，标记完毕后，再扫描整个空间中未被标记的对象，进行回收，如上图所示。标记-清除算法不需要进行对象的移动，并且仅对不存活的对象进行处理，在存活对象比较多的情况下极为高效，但由于标记-清除算法直接回收不存活的对象，因此会造成内存碎片.

### 标记整理算法

![img](http://ww3.sinaimg.cn/mw690/7178f37egw1etbmybx9qij20gy0g7js1.jpg)

	标记-整理算法采用标记-清除算法一样的方式进行对象的标记，但在清除时不同，在回收不存活的对象占用的空间后，会将所有的存活对象往左端空闲空间移动，并更新对应的指针。标记-整理算法是在标记-清除算法的基础上，又进行了对象的移动，因此成本更高，但是却解决了内存碎片的问题。在基于Compacting算法的收集器的实现中，一般增加句柄和句柄表。



### 复制算法

![img](http://ww2.sinaimg.cn/mw690/7178f37egw1etbmybcowsj20g308l3yp.jpg)

	该算法的提出是为了克服句柄的开销和解决堆碎片的垃圾回收。它开始时把堆分成 一个对象 面和多个空闲面， 程序从对象面为对象分配空间，当对象满了，基于copying算法的垃圾 收集就从根集中扫描活动对象，并将每个 活动对象复制到空闲面(使得活动对象所占的内存之间没有空闲洞)，这样空闲面变成了对象面，原来的对象面变成了空闲面，程序会在新的对象面中分配内存。一种典型的基于coping算法的垃圾回收是stop-and-copy算法，它将堆分成对象面和空闲区域面，在对象面与空闲区域面的切换过程中，程序暂停执行。

### 分代算法

![img](http://ww3.sinaimg.cn/mw690/7178f37egw1etbmycakylj20fn08kgmb.jpg)

**年轻代（Young Generation）**

1.所有新生成的对象首先都是放在年轻代的。年轻代的目标就是尽可能快速的收集掉那些生命周期短的对象。

2.新生代内存按照8:1:1的比例分为一个eden区和两个survivor(survivor0,survivor1)区。一个Eden区，两个 Survivor区(一般而言)。大部分对象在Eden区中生成。回收时先将eden区存活对象复制到一个survivor0区，然后清空eden区，当这个survivor0区也存放满了时，则将eden区和survivor0区存活对象复制到另一个survivor1区，然后清空eden和这个survivor0区，此时survivor0区是空的，然后将survivor0区和survivor1区交换，即保持survivor1区为空， 如此往复。

3.当survivor1区不足以存放 eden和survivor0的存活对象时，就将存活对象直接存放到老年代。若是老年代也满了就会触发一次Full GC，也就是新生代、老年代都进行回收

4.新生代发生的GC也叫做Minor GC，MinorGC发生频率比较高(不一定等Eden区满了才触发)

**年老代（Old Generation）**

1.在年轻代中经历了N次垃圾回收后仍然存活的对象，就会被放到年老代中。因此，可以认为年老代中存放的都是一些生命周期较长的对象。

2.内存比新生代也大很多(大概比例是1:2)，当老年代内存满时触发Major GC即Full GC，Full GC发生频率比较低，老年代对象存活时间比较长，存活率标记高。

**持久代（Permanent Generation）**

用于存放静态文件，如Java类、方法等。持久代对垃圾回收没有显著影响，但是有些应用可能动态生成或者调用一些class，例如Hibernate 等，在这种时候需要设置一个比较大的持久代空间来存放这些运行过程中新增的类。

### 垃圾收集器

新生代收集器使用的收集器：Serial、PraNew、Parallel Scavenge

老年代收集器使用的收集器：Serial Old、Parallel Old、CMS

![img](http://ww1.sinaimg.cn/mw690/7178f37egw1etbmycjfvoj20e40engmi.jpg)

**Serial收集器（复制算法)**

![ä¸²è¡æ¶éå¨ç"å](https://c1.staticflickr.com/5/4603/28345836579_8dff90eb76_z.jpg)

新生代单线程收集器，标记和清理都是单线程，优点是简单高效。

**Serial Old收集器(标记-整理算法)**

老年代单线程收集器，Serial收集器的老年代版本。

**ParNew收集器(停止-复制算法)**　

新生代收集器，可以认为是Serial收集器的多线程版本,在多核CPU环境下有着比Serial更好的表现。

**Parallel Scavenge收集器(停止-复制算法)**

![å¹¶è¡æ¶éå¨ç"å](https://c1.staticflickr.com/5/4662/28345836389_55e8402324_z.jpg)

并行收集器，追求高吞吐量，高效利用CPU。吞吐量一般为99%， 吞吐量= 用户线程时间/(用户线程时间+GC线程时间)。适合后台应用等对交互相应要求不高的场景。

**Parallel Old收集器(停止-复制算法)**

Parallel Scavenge收集器的老年代版本，并行收集器，吞吐量优先

**CMS(Concurrent Mark Sweep)收集器（标记-清理算法）**

![å¹¶åæ è®°æ¸é¤æ¶éå¨ç"å](https://c1.staticflickr.com/5/4740/40093687062_7383cd1b49_z.jpg)

高并发、低停顿，追求最短GC回收停顿时间，cpu占用比较高，响应时间快，停顿时间短，多核cpu 追求高响应时间的选择

**G1收集器(Garbage first)**

参考文献:

1. https://blog.csdn.net/coderlius/article/details/79272773

### GC执行机制

由于对象进行了分代处理，因此垃圾回收区域、时间也不一样。GC有两种类型：Scavenge GC和Full GC。

**Scavenge GC**

一般情况下，当新对象生成，并且在Eden申请空间失败时，就会触发Scavenge GC，对Eden区域进行GC，清除非存活对象，并且把尚且存活的对象移动到Survivor区。然后整理Survivor的两个区。这种方式的GC是对年轻代的Eden区进行，不会影响到年老代。因为大部分对象都是从Eden区开始的，同时Eden区不会分配的很大，所以Eden区的GC会频繁进行。因而，一般在这里需要使用速度快、效率高的算法，使Eden去能尽快空闲出来。

**Full GC**

对整个堆进行整理，包括Young、Tenured和Perm。Full GC因为需要对整个堆进行回收，所以比Scavenge GC要慢，因此应该尽可能减少Full GC的次数。在对JVM调优的过程中，很大一部分工作就是对于FullGC的调节。有如下原因可能导致Full GC：

1.年老代（Tenured）被写满

2.持久代（Perm）被写满

3.System.gc()被显示调用

4.上一次GC之后Heap的各域分配策略动态变化





# Java并发

## 线程的生命周期

参考文献: 

1. https://blog.csdn.net/pange1991/article/details/53860651



![img](https://images0.cnblogs.com/i/426802/201406/232002051747387.jpg)

 

在枚举类java.lang.Thread.State 中,表明了Thread的状态(NEW , RUNNABLE , BLOCKED , WAITING , TIMED_WAITING , TERMINATED)

```
/**
 * A thread state.  A thread can be in one of the following states:
 * <ul>
 * <li>{@link #NEW}<br>
 *     A thread that has not yet started is in this state.
 *     </li>
 * <li>{@link #RUNNABLE}<br>
 *     A thread executing in the Java virtual machine is in this state.
 *     </li>
 * <li>{@link #BLOCKED}<br>
 *     A thread that is blocked waiting for a monitor lock
 *     is in this state.
 *     </li>
 * <li>{@link #WAITING}<br>
 *     A thread that is waiting indefinitely for another thread to
 *     perform a particular action is in this state.
 *     </li>
 * <li>{@link #TIMED_WAITING}<br>
 *     A thread that is waiting for another thread to perform an action
 *     for up to a specified waiting time is in this state.
 *     </li>
 * <li>{@link #TERMINATED}<br>
 *     A thread that has exited is in this state.
 *     </li>
 * </ul>
 *
 * <p>
 * A thread can be in only one state at a given point in time.
 * These states are virtual machine states which do not reflect
 * any operating system thread states.
 *
 * @since   1.5
 * @see #getState
 */
```



**新建状态（New）：**当线程对象对创建后，即进入了新建状态，如：Thread t = new MyThread();

**就绪状态（Runnable）：**当调用线程对象的start()方法（t.start();），线程即进入就绪状态。处于就绪状态的线程，只是说明此线程已经做好了准备，随时等待CPU调度执行，并不是说执行了t.start()此线程立即就会执行；

**运行状态（Running）：**当CPU开始调度处于就绪状态的线程时，此时线程才得以真正执行，即进入到运行状态。注：就     绪状态是进入到运行状态的唯一入口，也就是说，线程要想进入运行状态执行，首先必须处于就绪状态中；

**阻塞状态（Blocked）：**处于运行状态中的线程由于某种原因，暂时放弃对CPU的使用权，停止执行，此时进入阻塞状态，直到其进入到就绪状态，才 有机会再次被CPU调用以进入到运行状态。根据阻塞产生的原因不同，阻塞状态又可以分为三种：

1.等待阻塞：运行状态中的线程执行wait()方法，使本线程进入到等待阻塞状态；

2.同步阻塞 -- 线程在获取synchronized同步锁失败(因为锁被其它线程所占用)，它会进入同步阻塞状态；

3.其他阻塞 -- 通过调用线程的sleep()或join()或发出了I/O请求时，线程会进入到阻塞状态。当sleep()状态超时、join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。

**死亡状态（Dead）：**线程执行完了或者因异常退出了run()方法，该线程结束生命周期。



# ConcurrentApi

参考资料:

1. https://blog.csdn.net/javazejian/article/details/76167357
2. https://blog.csdn.net/javazejian/article/details/72828483
3. https://blog.csdn.net/javazejian/article/details/75043422
4. https://blog.csdn.net/javazejian/article/details/77410889

## LinkedBlockingQueue与ArrayBlockingQueue

参考资料:

1. https://blog.csdn.net/javazejian/article/details/77410889?locationNum=1&fps=1

# AtomicInteger

参考资料:

1. https://www.cnblogs.com/rever/p/8215743.html


# NIO

参考资料:

1. https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
2. http://tutorials.jenkov.com/java-nio/index.html

# Guava 简介
## Basic Utilities: Make using the Java language more pleasant.
+ 使用Optional避免null
+ 使用Preconditions进行预检查
+ JDK1.7及以后直接使用 Objects.equals() 和 Objects.compare()方法,避免空指针
+ Ordering增强版Comparator
+ Throwables: 用于传递和检查exceptions和errors

## Collections: Guava's extensions to the JDK collections ecosystem. These are some of the most mature and popular parts of Guava.
1. Immutable collections: 用于防御性编程,常量集合,提高开发效率
2. New Collection Types:
    1. Multiset 工具类,可以用于count每个对象出现的次数
    2. SortedMultiset
    3. Multimap: 同一个key可以对应list的扩展map,例子 a -> [1,2,4]
    4. Bimap: key 和value 互换的map
    5. Table:保存了3个值,Table<R,C,V> 可以用于坐标系
    6. ClassToInstanceMap
3. Collection工具类扩展
    1. Guava提供了静态构造函数,简介明了
    2. Iterables:提供了众多实用的方法
        1. concat(Iterable<Iterable>) -> concat(Iterable...) 拼接多个Iterable
        2. frequency(Iterable, Object) -> 统计Object在Iterable中出现的次数
        3. partition(Iterable,int) 将Iterable按照int切割为多个List
        4. getFirst(Iterable,T default) 返回Iterable中第一个元素,否则返回default
        5. getLast(Iterable,T default)
        6. elementsEqual(Iterable,Iterable):返回true如果2个Iterable含有相同次序的元素
        7. unmodifiableIterable(Iterable):返回不可变iterable
        8. limit(Iterable,int):返回指定个数Iterable
        9. 
4. 
## Graphs: a library for modeling graph-structured data, that is, entities and the relationships between them.
## Caches: Local caching, done right, and supporting a wide variety of expiration behaviors.
## Functional Utilities: 
## Concurrency: Powerful, simple abstractions to make it easier to write correct concurrent code.
## Strings: A few extremely useful string utilities: splitting, joining, padding, and more.

1. Joiner: 拼接工具类
2. Splitter: 剪切工具类
3. CharMatcher: 字符匹配工具
4. Charsets:
5. CaseFormat: string不同命名空间之间的转换(如驼峰->下划线)

## Primitives: Used sparingly, Guava's functional idioms can significantly simplify code.

1. 基本数组的工具类(以Ints为例)

   1. ```java
      Ints.asList(1, 2, 3);
      ```

   2. ```java
      Ints.toArray(l1);
      ```

   3. ```java
      Ints.concat(new int[]{1, 2, 3,}, new int[]{1, 2, 3,});
      ```

   4. ```java
       Ints.contains(new int[]{1, 2, 3,}, 3);
      ```

   5. ```java
      Ints.indexOf(new int[]{1, 2, 3,}, 3);
      ```

   6. ```java
      Ints.lastIndexOf(new int[]{1, 2, 3,}, 3);
      ```

   7. ```java
      Ints.min(1, 2, 4);
      ```

   8. ```java
      Ints.max(1, 2, 4);
      ```

   9. ```java
      Ints.join(",", 1, 2, 4)
      ```

2. 通用工具类

   1. ```java
      Ints.compare(1, 2);
      ```

   2. ```java
      Ints.checkedCast(1); //校验数字范围是否属于int 
      ```

   3. ```java
      Ints.saturatedCast(11); // 输入超过Integer最大值,则返回Integer.MAX_VALUE;
      ```

3. Byte转换方法

   1. ```java
      Ints.fromByteArray(new byte[]{1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0});
      ```

   2. ```java
      Ints.toByteArray(5);
      ```

   3. ```java
      Ints.fromBytes((byte) 0, (byte) 1, (byte) 1, (byte) 1);
      ```

   

## Ranges: Guava's powerful API for dealing with ranges on Comparable types, both continuous and discrete.

1. ```java
   Range.open(1, 2);
   Range.closed(1, 2);
   Range.downTo(10, BoundType.CLOSED);
   Range.range(1, BoundType.CLOSED, 4, BoundType.OPEN);
   ```

2. ```java
   Range.closedOpen(4, 4).isEmpty(); // returns true
   Range.openClosed(4, 4).isEmpty(); // returns true
   Range.closed(4, 4).isEmpty(); // returns false
   Range.open(4, 4).isEmpty(); // Range.open throws IllegalArgumentException
   
   Range.closed(3, 10).lowerEndpoint(); // returns 3
   Range.open(3, 10).lowerEndpoint(); // returns 3
   Range.closed(3, 10).lowerBoundType(); // returns CLOSED
   Range.open(3, 10).upperBoundType(); // returns OPEN
   ```

3. ```java
   Range.closed(3, 5).isConnected(Range.open(5, 10)); // returns true
   Range.closed(0, 9).isConnected(Range.closed(3, 4)); // returns true
   Range.closed(0, 5).isConnected(Range.closed(3, 9)); // returns true
   Range.open(3, 5).isConnected(Range.open(5, 10)); // returns false
   Range.closed(1, 5).isConnected(Range.closed(6, 10)); // returns false
   ```

4. ```java
   Range.closed(3, 5).intersection(Range.open(5, 10)); // returns (5, 5]
   Range.closed(0, 9).intersection(Range.closed(3, 4)); // returns [3, 4]
   Range.closed(0, 5).intersection(Range.closed(3, 9)); // returns [3, 5]
   Range.open(3, 5).intersection(Range.open(5, 10)); // throws IAE
   Range.closed(1, 5).intersection(Range.closed(6, 10)); // throws IAE
   ```

## I/O: Simplified I/O operations, especially on whole I/O streams and files, for Java 5 and 6.
## Hashing: Tools for more sophisticated hashes than what's provided by Object.hashCode(), including Bloom filters.
## EventBus: Publish-subscribe-style communication between components without requiring the components to explicitly register with one another.
## Math: Optimized, thoroughly tested math utilities not provided by the JDK.
## Reflection: Guava utilities for Java's reflective capabilities.


参考资料:

1. https://github.com/google/guava/wiki