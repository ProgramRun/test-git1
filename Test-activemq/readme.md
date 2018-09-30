# ActiveMQ

​	ActiveMQ是一个MOM(message oriented middlerware,用于分布式应用或系统中的异步,解耦合,可靠性,可扩展性及安全通信的一类软件)



## 基本概念

​	provider  :  纯java语言编写的JMS接口实现(例如ActiveMQ)

​	domains : 消息传递方式,包括点对点(P2P),发布订阅(Pub/Sub)两种

​	connection factory : 客户端使用连接工厂来创建与JMS provider的连接

​	destiantion : 消息被寻址,发送,及接收的对象

​	P2P （点对点）消息域使用 queue 作为 Destination，消息可以被同步或异步的发送和接收，每个消息只会给一个 Consumer 传送一次; 	Consumer 可以使用 messageConsumer.receive() 同步地接收消息，也可以通过使用messageConsumer.setMessageListener() 注册一个 MessageListener 实现异步接收;