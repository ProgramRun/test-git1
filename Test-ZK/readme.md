分布式锁

https://www.jianshu.com/p/31335efec309

znode介绍

https://www.cnblogs.com/Coolkaka/p/6101273.html

https://blog.csdn.net/mayp1/article/details/51926195



# ZooKeeper

​	ZK是一个用于维护配置信息,命名,提供分布式同步,提供群服务的中心服务.

## Overview

​	ZK的核心是分布式的应用可以相互协调是通过共享的层级目录(即ZK的目录结构znodes,类似于文件系统,相同路径下不可能存在两个路径名相同的znode).而与文件系统不同的是,ZK提供了客户端高吞吐量,低延时,高可用且严格有序的访问znodes,这些特点使得ZK被应用于大型分布式系统中.

​	每一个znode都是一个路径,以"/"开头,都有父节点.而最顶级目录即为"/",无父节点. 含有子节点的znode必选在删除子节点后才可以删除.每个节点都可以存储信息(状态信息,配置,路径信息等),最大能到1M

​	当ZK配置为集群时,ZK集群启动时,会自动选举leader,follower(observer为配置产生),只有leader才有写入信息的权利,follower自对外提供信息,并将写请求转发给leader;follower和observer的区别在于follower参与选举



## Znode

​	znode分为持有节点,临时节点,有序节点