# RMI

参考资料  

 https://www.cnblogs.com/qujiajun/p/4065857.html

# 

# 通信协议

## 三次握手和四次挥手

参考资料

 https://blog.csdn.net/qzcsu/article/details/72861891

http://blog.51cto.com/jinlong/2065461



![TCPä¸æ¬¡æ¡æååæ¬¡æ¥æä"¥å11ç§ç¶æ](http://i2.51cto.com/images/blog/201801/26/cf32189db2b594d6f239ea75d4f7b652.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)



三次握手过程说明： 
1、由客户端发送建立TCP连接的请求报文，其中报文中包含seq序列号，是由发送端随机生成的，并且将报文中的SYN字段置为1，表示需要建立TCP连接。（SYN=1，seq=x，x为随机生成数值）
2、由服务端回复客户端发送的TCP连接请求报文，其中包含seq序列号，是由回复端随机生成的，并且将SYN置为1，而且会产生ACK字段，ACK字段数值是在客户端发送过来的序列号seq的基础上加1进行回复，以便客户端收到信息时，知晓自己的TCP建立请求已得到验证。（SYN=1，ACK=x+1，seq=y，y为随机生成数值）这里的ack加1可以理解为是确认和谁建立连接。
3、客户端收到服务端发送的TCP建立验证请求后，会使自己的序列号加1表示，并且再次回复ACK验证请求，在服务端发过来的seq上加1进行回复。（SYN=1，ACK=y+1，seq=x+1）

![TCPä¸æ¬¡æ¡æååæ¬¡æ¥æä"¥å11ç§ç¶æ](http://i2.51cto.com/images/blog/201801/26/7b0328d95ac0eb055d1e38e0e27e0ba8.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk=)





四次挥手过程说明： 
1、客户端发送断开TCP连接请求的报文，其中报文中包含seq序列号，是由发送端随机生成的，并且还将报文中的FIN字段置为1，表示需要断开TCP连接。（FIN=1，seq=x，x由客户端随机生成）

2、服务端会回复客户端发送的TCP断开请求报文，其包含seq序列号，是由回复端随机生成的，而且会产生ACK字段，ACK字段数值是在客户端发过来的seq序列号基础上加1进行回复，以便客户端收到信息时，知晓自己的TCP断开请求已经得到验证。（FIN=1，ACK=x+1，seq=y，y由服务端随机生成）
3、服务端在回复完客户端的TCP断开请求后，不会马上进行TCP连接的断开，服务端会先确保断开前，所有传输到A的数据是否已经传输完毕，一旦确认传输数据完毕，就会将回复报文的FIN字段置1，并且产生随机seq序列号。（FIN=1，ACK=x+1，seq=z，z由服务端随机生成）
4、客户端收到服务端的TCP断开请求后，会回复服务端的断开请求，包含随机生成的seq字段和ACK字段，ACK字段会在服务端的TCP断开请求的seq基础上加1，从而完成服务端请求的验证回复。（FIN=1，ACK=z+1，seq=h，h为客户端随机生成） 
至此TCP断开的4次挥手过程完毕



## OSI有哪些层

应用层(确定进程之间通信的性质以满足用户需要以及提供网络与用户应用)
-表示层(主要解决拥护信息的语法表示问题，如加密解密)
-会话层(提供包括访问验证和会话管理在内的建立和维护应用之间通信的机制，如服务器验证用户登录便是由会话层完成的)
-运输层(实现网络不同主机上用户进程之间的数据通信，可靠与不可靠的传输，传输层的错误检测，流量控制等)
-网络层(提供逻辑地址（IP）、选路，数据从源端到目的端的传输)
-数据链路层(将上层数据封装成帧，用MAC地址访问媒介，错误检测与修正)
-物理层()

## TCP/IP有哪些层

应用层(TELNET FTP SMTP)
运输层(TCP UDP)
网络层(IP ICMP)
网络接口层(PPP)

## 一个http请求流程

DNS域名解析 –> 发起TCP的三次握手 –> 建立TCP连接后发起http请求 –> 服务器响应http请求，浏览器得到html代码 –> 浏览器解析html代码，并请求html代码中的资源（如javascript、css、图片等） –> 浏览器对页面进行渲染呈现给用户

