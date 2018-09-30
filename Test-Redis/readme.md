## 安装操作

1. 从官网下载redis安装包<https://redis.io/download>

2. 使用指令解压     tar zxvf redis-4.0.11.tar.gz

3.  yum安装gcc依赖    yum install gcc (**\*rpm -qa|grep gcc*** 验证gcc是否安装成功)

4. cd /usr/redis-4.0.11

5. make MALLOC=libc

6. cd src && make install

7. 测试是否安装成功 cd src  ||   ./redis-server



