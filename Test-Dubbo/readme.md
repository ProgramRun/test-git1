# Dubbo

	dubbo是一款高性能,轻量级的开源Java RPC框架,它提供了三大核心能力：面向接口的远程方法调用，智能容错和负载均衡，以及服务自动注册和发现.

## 架构

![dubbo-architucture](http://dubbo.apache.org/docs/zh-cn/user/sources/images/dubbo-architecture.jpg)

### 节点角色说明

provider -- The provider exposes remote services; 

consumer -- The consumer calls the remote services; 

registry -- The registry is responsible for service discovery and configuration; 

monitor -- The monitor counts the number of service invocations and time-consuming; container -- The container manages the services's lifetime

### 调用关系说明

1. 服务容器负责启动，加载，运行服务提供者。
2. 服务提供者在启动时，向注册中心注册自己提供的服务。
3. 服务消费者在启动时，向注册中心订阅自己所需的服务。
4. 注册中心返回服务提供者地址列表给消费者，如果有变更，注册中心将基于长连接推送变更数据给消费者。
5. 服务消费者，从提供者地址列表中，基于软负载均衡算法，选一台提供者进行调用，如果调用失败，再选另一台调用。
6. 服务消费者和提供者，在内存中累计调用次数和调用时间，定时每分钟发送一次统计数据到监控中心。

### 连通性

- 注册中心负责服务地址的注册与查找，相当于目录服务，服务提供者和消费者只在启动时与注册中心交互，注册中心不转发请求，压力较小
- 监控中心负责统计各服务调用次数，调用时间等，统计先在内存汇总后每分钟一次发送到监控中心服务器，并以报表展示
- 服务提供者向注册中心注册其提供的服务，并汇报调用时间到监控中心，此时间不包含网络开销
- 服务消费者向注册中心获取服务提供者地址列表，并根据负载算法直接调用提供者，同时汇报调用时间到监控中心，此时间包含网络开销
- 注册中心，服务提供者，服务消费者三者之间均为长连接，监控中心除外
- 注册中心通过长连接感知服务提供者的存在，服务提供者宕机，注册中心将立即推送事件通知消费者
- 注册中心和监控中心全部宕机，不影响已运行的提供者和消费者，消费者在本地缓存了提供者列表
- 注册中心和监控中心都是可选的，服务消费者可以直连服务提供者



### 健壮性

		

- 监控中心宕掉不影响使用，只是丢失部分采样数据
- 数据库宕掉后，注册中心仍能通过缓存提供服务列表查询，但不能注册新服务
- 注册中心对等集群，任意一台宕掉后，将自动切换到另一台
- 注册中心全部宕掉后，服务提供者和服务消费者仍能通过本地缓存通讯
- 服务提供者无状态，任意一台宕掉后，不影响使用
- 服务提供者全部宕掉后，服务消费者应用将无法使用，并无限次重连等待服务提供者恢复



### 伸缩性



- 注册中心为对等集群，可动态增加机器部署实例，所有客户端将自动发现新的注册中心
- 服务提供者无状态，可动态增加机器部署实例，注册中心将推送新的服务提供者信息给消费者



### 升级性



### dubbo 集群容错

1. failover 

 失败自动切换，当出现失败，重试其他服务器，通常用于查询操作，但重试会带来更长的延迟，可以通过retries=n来设置重试次数，不包含第一次 

  2. failfast 

 快速失败，只发起一次调用，失败就报错，通常用于新增记录的操作，比如数据同步。 

  3. failsafe 

 失败安全，出现异常直接忽略，也就是对数据的完整性要求不高，通常用于写入审计日志等操作 

  4. failback 

 失败自动恢复，失败后后台记录失败的请求，定时重发，通常用于实时性要求不高的通知类操作 

  5. forking 

 并行调用，同时调用多个服务器，只要有一个返回成功即可，通常用于实时性要求高的查询操作，但是对资源的浪费更大，具体并行几个可以根据forks=n设置 

  6. broadcast 

 广播调用所有提供者，逐个调用，有一台报错就报错，通常用于更新所有提供者缓存或者日志等本地资源信息，用的机会较少，并且更新失败的话对系统影响很小的资源





## XML 配置





### 配置覆盖关系



以 timeout 为例，显示了配置的查找顺序，其它 retries, loadbalance, actives 等类似：

- 方法级优先，接口级次之，全局配置再次之。
- 如果级别一样，则消费方优先，提供方次之。

其中，服务提供方配置，通过 URL 经由注册中心传递给消费方。

![dubbo-config-override](http://dubbo.apache.org/docs/zh-cn/user/sources/images/dubbo-config-override.jpg)

建议由服务提供方设置超时，因为一个方法需要执行多长时间，服务提供方更清楚，如果一个消费方同时引用多个服务，就不需要关心每个服务的超时设置。

理论上 ReferenceConfig 的非服务标识配置，在 ConsumerConfig，ServiceConfig, ProviderConfig 均可以缺省配置.





```
log4j配置
https://blog.csdn.net/zzjvslove/article/details/74905468
```





## Dubbo容错机制

​	Failover Cluster --失败自动切换，当出现失败，重试其它服务器; 可通过retries="2"来设置重试次数(不含第一次)。正是文章刚开始说的那种情况

​	Failfast Cluster -- 快速失败，只发起一次调用，失败立即报错; 通常用于非幂等性的写操作，比如新增记录

​	Failsafe Cluster -- 失败安全，出现异常时，直接忽略; 通常用于写入审计日志等操作

​	Failback Cluster -- 失败自动恢复，后台记录失败请求，定时重发; 通常用于消息通知操作

​	Forking Cluster -- 并行调用多个服务器，只要一个成功即返回

​	Broadcast Cluster -- 广播调用所有提供者，逐个调用，任意一台报错则报错 ; 通常用于通知所有提供者更新缓存或日志等本地资源信息







## 使用注意事项

1. 当使用hessian协议发布服务时,provider端需要引入hessian jar 和jetty jar; consumer端需要引入hessian jar
2. 当使用dubbo 注解对外发布服务时.在xml文件中需要配置<dubbo:annotation package=""/> 来扫描



## Dubbo服务只注册

​	只对外提供服务,不调用其他服务(一般在有多个注册中心时使用)

<dubbo:registry subscribe="false"/>



## Dubbo服务只订阅

​	只调用外部服务,而不对外发布服务(当开发新功能时,如果使用到原有功能,则需要此设置)

<dubbo:registry register="false"/>

## Dubbo负载均衡

Random LoadBalance(默认) 

RoundRobin LoadBalance 轮询:可通过设置权重设置轮询比例 

LeastActive LoadBalance 最少活跃调用,对于响应快的节点优先调用

ConsistentHash LoadBalance 一致性hash





