# Kafka

## 简介

​	Kafka是一个开源流处理平台,由Scala和Java语言编写.Kafka是一种高吞吐量的分布式发布订阅消息系统.Kafka包括4个核心API:Producer(应用可以通过该API发布消息到topic中),Consumer(应用可以通过该API订阅一个或多个topic并处理消息流),Streams(应用可以使用该API作为stream processor,消费input stream和产生output stream,将input stream 转为output stream),Connecter(构建并运行可重复使用的producers或consumers). 在Kafka中,客户端与服务端之间的交流是通过语言无关的TCP协议,该协议是有版本的,可以向后和老版本兼容.

## Topics 和 Logs

​	Topic是记录发布的目录或目录名.Topic始终有多个订阅者(0-N).对于每个topic,Kafka节点都维护一个分区日志.

![img](http://kafka.apache.org/20/images/log_anatomy.png)

每一个分区都是有序不可变的消息序列.每一个分区中的消息都被分配了序列id被称为offset用于区分分区中的消息

