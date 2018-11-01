# [mysql优化(一)–数据库表设计](http://www.daydaytc.com/mysql/531.html)

**第一范式**：如果每列(或者每个属性)都是不可再分的最小数据单元(也称为最小的原子单元),则满足第一范式.比如一个工人的基本信息表，里面有工人的工号，性别，年龄，这些属性都是不可分割的，所以这个表就符合了第一范式。

**第二范式：** 就是在第一范式的基础上延伸，使之表里的每个字段都与主键发生关系。假如一个关系满足第一范式,并且除了主键以外的其它字段,都依赖于该主键,则满足第二范式.
   例如:订单表(订单编号、产品编号、定购日期、价格、……)，”订单编号”为主键，”产品编号”和主键列没有直接的关系，即”产品编号”列不依赖于主键列，这个列我们就可以把它删除。

**第三范式：**在第二范式的基础上更进一步，也就是为了实现表里的列都与主键列直接相关，不是间接相关。

3NF是对字段冗余性的约束，即任何字段不能由其他字段派生出来，它要求字段没有冗余。

其实在设计数据库的时候我们最多的要遵循的就是第三范式，但是并不是越满足第三范式数据库就设计的越完美，这种错误是错误的。有时候增加点冗余相反的会提高访问速率，因此在实际的设计过程中应降低对范式的要求。

反三范式的具体做法： 在概念数据模型设计时遵守第三范式，降低范式标准的工作放到物理数据模型设计时考虑。降低范式就是增加字段，允许冗余**。**



# [mysql优化(二)–show status](http://www.daydaytc.com/mysql/535.html)

mysql优化的一个重要的部分就是sql语句的优化，在进行sql语句优化的过程中，首先通过show status命令了解各种SQL的执行频率。定位执行效率较低的SQL语句-（重点select）；通过explain分析低效率的SQL语句的执行情况；确定问题并采取相应的优化措施；所以了解mysql服务器的状态非常关键。

一、show status 参数解释：

要查看MySQL运行状态，要优化MySQL运行效率都少不了要运行show status查看各种状态，下面是参考官方文档及网上资料整理出来的中文详细解释，不管你是初学mysql还是你是mysql专业级的dba，这都是值得看的.

| **状态名**                        | **作用域** | **详细解释**                                                 |
| --------------------------------- | ---------- | ------------------------------------------------------------ |
| Aborted_clients                   | Global     | 由于客户端没有正确关闭连接导致客户端终止而中断的连接数       |
| Aborted_connects                  | Global     | 试图连接到MySQL服务器而失败的连接数                          |
| Binlog_cache_disk_use             | Global     | 使用临时二进制日志缓存但超过binlog_cache_size值并使用临时文件来保存事务中的语句的事务数量 |
| Binlog_cache_use                  | Global     | 使用临时二进制日志缓存的事务数量                             |
| Bytes_received                    | Both       | 从所有客户端接收到的字节数。                                 |
| Bytes_sent                        | Both       | 发送给所有客户端的字节数。                                   |
| com*                              |            | 各种数据库操作的数量                                         |
| Compression                       | Session    | 客户端与服务器之间只否启用压缩协议                           |
| Connections                       | Global     | 试图连接到(不管是否成功)MySQL服务器的连接数                  |
| Created_tmp_disk_tables           | Both       | 服务器执行语句时在硬盘上自动创建的临时表的数量               |
| Created_tmp_files                 | Global     | mysqld已经创建的临时文件的数量                               |
| Created_tmp_tables                | Both       | 服务器执行语句时自动创建的内存中的临时表的数量。如果Created_tmp_disk_tables较大，你可能要增加tmp_table_size值使临时 表基于内存而不基于硬盘 |
| Delayed_errors                    | Global     | 用INSERT DELAYED写的出现错误的行数(可能为duplicate key)。    |
| Delayed_insert_threads            | Global     | 使用的INSERT DELAYED处理器线程数。                           |
| Delayed_writes                    | Global     | 写入的INSERT DELAYED行数                                     |
| Flush_commands                    | Global     | 执行的FLUSH语句数。                                          |
| Handler_commit                    | Both       | 内部提交语句数                                               |
| Handler_delete                    | Both       | 行从表中删除的次数。                                         |
| Handler_discover                  | Both       | MySQL服务器可以问NDB CLUSTER存储引擎是否知道某一名字的表。这被称作发现。Handler_discover说明通过该方法发现的次数。 |
| Handler_prepare                   | Both       | A counter for the prepare phase of two-phase commit operations. |
| Handler_read_first                | Both       | 索引中第一条被读的次数。如果较高，它建议服务器正执行大量全索引扫描；例如，SELECT col1 FROM foo，假定col1有索引。 |
| Handler_read_key                  | Both       | 根据键读一行的请求数。如果较高，说明查询和表的索引正确。     |
| Handler_read_next                 | Both       | 按照键顺序读下一行的请求数。如果你用范围约束或如果执行索引扫描来查询索引列，该值增加。 |
| Handler_read_prev                 | Both       | 按照键顺序读前一行的请求数。该读方法主要用于优化ORDER BY … DESC。 |
| Handler_read_rnd                  | Both       | 根据固定位置读一行的请求数。如果你正执行大量查询并需要对结果进行排序该值较高。你可能使用了大量需要MySQL扫描整个表的查询或你的连接没有正确使用键。 |
| Handler_read_rnd_next             | Both       | 在数据文件中读下一行的请求数。如果你正进行大量的表扫描，该值较高。通常说明你的表索引不正确或写入的查询没有利用索引。 |
| Handler_rollback                  | Both       | 内部ROLLBACK语句的数量。                                     |
| Handler_savepoint                 | Both       | 在一个存储引擎放置一个保存点的请求数量。                     |
| Handler_savepoint_rollback        | Both       | 在一个存储引擎的要求回滚到一个保存点数目。                   |
| Handler_update                    | Both       | 在表内更新一行的请求数。                                     |
| Handler_write                     | Both       | 在表内插入一行的请求数。                                     |
| Innodb_buffer_pool_pages_data     | Global     | 包含数据的页数(脏或干净)。                                   |
| Innodb_buffer_pool_pages_dirty    | Global     | 当前的脏页数。                                               |
| Innodb_buffer_pool_pages_flushed  | Global     | 要求清空的缓冲池页数                                         |
| Innodb_buffer_pool_pages_free     | Global     | 空页数。                                                     |
| Innodb_buffer_pool_pages_latched  | Global     | 在InnoDB缓冲池中锁定的页数。这是当前正读或写或由于其它原因不能清空或删除的页数。 |
| Innodb_buffer_pool_pages_misc     | Global     | 忙的页数，因为它们已经被分配优先用作管理，例如行锁定或适用的哈希索引。该值还可以计算为Innodb_buffer_pool_pages_total – Innodb_buffer_pool_pages_free – Innodb_buffer_pool_pages_data。 |
| Innodb_buffer_pool_pages_total    | Global     | 缓冲池总大小（页数）。                                       |
| Innodb_buffer_pool_read_ahead_rnd | Global     | InnoDB初始化的“随机”read-aheads数。当查询以随机顺序扫描表的一大部分时发生。 |
| Innodb_buffer_pool_read_ahead_seq | Global     | InnoDB初始化的顺序read-aheads数。当InnoDB执行顺序全表扫描时发生。 |
| Innodb_buffer_pool_read_requests  | Global     | InnoDB已经完成的逻辑读请求数。                               |
| Innodb_buffer_pool_reads          | Global     | 不能满足InnoDB必须单页读取的缓冲池中的逻辑读数量。           |
| Innodb_buffer_pool_wait_free      | Global     | 一般情况，通过后台向InnoDB缓冲池写。但是，如果需要读或创建页，并且没有干净的页可用，则它还需要先等待页面清空。该计数器对等待实例进行记数。如果已经适当设置缓冲池大小，该值应小。 |
| Innodb_buffer_pool_write_requests | Global     | 向InnoDB缓冲池的写数量。                                     |
| Innodb_data_fsyncs                | Global     | fsync()操作数。                                              |
| Innodb_data_pending_fsyncs        | Global     | 当前挂起的fsync()操作数。                                    |
| Innodb_data_pending_reads         | Global     | 当前挂起的读数。                                             |
| Innodb_data_pending_writes        | Global     | 当前挂起的写数。                                             |
| Innodb_data_read                  | Global     | 至此已经读取的数据数量（字节）。                             |
| Innodb_data_reads                 | Global     | 数据读总数量。                                               |
| Innodb_data_writes                | Global     | 数据写总数量。                                               |
| Innodb_data_written               | Global     | 至此已经写入的数据量（字节）。                               |
| Innodb_dblwr_pages_written        | Global     | 已经执行的双写操作数量                                       |
| Innodb_dblwr_writes               | Global     | 双写操作已经写好的页数                                       |
| Innodb_log_waits                  | Global     | 我们必须等待的时间，因为日志缓冲区太小，我们在继续前必须先等待对它清空 |
|                                   |            |                                                              |
| Innodb_log_write_requests         | Global     | 日志写请求数。                                               |
| Innodb_log_writes                 | Global     | 向日志文件的物理写数量。                                     |
| Innodb_os_log_fsyncs              | Global     | 向日志文件完成的fsync()写数量。                              |
| Innodb_os_log_pending_fsyncs      | Global     | 挂起的日志文件fsync()操作数量。                              |
| Innodb_os_log_pending_writes      | Global     | 挂起的日志文件写操作                                         |
| Innodb_os_log_written             | Global     | 写入日志文件的字节数。                                       |
| Innodb_page_size                  | Global     | 编译的InnoDB页大小(默认16KB)。许多值用页来记数；页的大小很容易转换为字节。 |
| Innodb_pages_created              | Global     | 创建的页数。                                                 |
| Innodb_pages_read                 | Global     | 读取的页数。                                                 |
| Innodb_pages_written              | Global     | 写入的页数。                                                 |
| Innodb_row_lock_current_waits     | Global     | 当前等待的待锁定的行数。                                     |
| Innodb_row_lock_time              | Global     | 行锁定花费的总时间，单位毫秒。                               |
| Innodb_row_lock_time_avg          | Global     | 行锁定的平均时间，单位毫秒。                                 |
| Innodb_row_lock_time_max          | Global     | 行锁定的最长时间，单位毫秒。                                 |
| Innodb_row_lock_waits             | Global     | 一行锁定必须等待的时间数。                                   |
| Innodb_rows_deleted               | Global     | 从InnoDB表删除的行数。                                       |
| Innodb_rows_inserted              | Global     | 插入到InnoDB表的行数。                                       |
| Innodb_rows_read                  | Global     | 从InnoDB表读取的行数。                                       |
| Innodb_rows_updated               | Global     | InnoDB表内更新的行数。                                       |
| Key_blocks_not_flushed            | Global     | 键缓存内已经更改但还没有清空到硬盘上的键的数据块数量。       |
| Key_blocks_unused                 | Global     | 键缓存内未使用的块数量。你可以使用该值来确定使用了多少键缓存 |
| Key_blocks_used                   | Global     | 键缓存内使用的块数量。该值为高水平线标记，说明已经同时最多使用了多少块。 |
| Key_read_requests                 | Global     | 从缓存读键的数据块的请求数。                                 |
| Key_reads                         | Global     | 从硬盘读取键的数据块的次数。如果Key_reads较大，则Key_buffer_size值可能太小。可以用Key_reads/Key_read_requests计算缓存损失率。 |
| Key_write_requests                | Global     | 将键的数据块写入缓存的请求数。                               |
| Key_writes                        | Global     | 向硬盘写入将键的数据块的物理写操作的次数。                   |
| Last_query_cost                   | Session    | 用查询优化器计算的最后编译的查询的总成本。用于对比同一查询的不同查询方案的成本。默认值0表示还没有编译查询。 默认值是0。Last_query_cost具有会话范围。 |
| Max_used_connections              | Global     | 服务器启动后已经同时使用的连接的最大数量。                   |
| ndb*                              |            | ndb集群相关                                                  |
| Not_flushed_delayed_rows          | Global     | 等待写入INSERT DELAY队列的行数。                             |
| Open_files                        | Global     | 打开的文件的数目。                                           |
| Open_streams                      | Global     | 打开的流的数量(主要用于记录)。                               |
| Open_table_definitions            | Global     | 缓存的.frm文件数量                                           |
| Open_tables                       | Both       | 当前打开的表的数量。                                         |
|                                   |            |                                                              |
| Opened_files                      | Global     | 文件打开的数量。不包括诸如套接字或管道其他类型的文件。 也不包括存储引擎用来做自己的内部功能的文件。 |
| Opened_table_definitions          | Both       | 已经缓存的.frm文件数量                                       |
| Opened_tables                     | Both       | 已经打开的表的数量。如果Opened_tables较大，table_cache 值可能太小。 |
| Prepared_stmt_count               | Global     | 当前的预处理语句的数量。 (最大数为系统变量: max_prepared_stmt_count) |
| Qcache_free_blocks                | Global     | 查询缓存内自由内存块的数量。                                 |
| Qcache_free_memory                | Global     | 用于查询缓存的自由内存的数量。                               |
| Qcache_hits                       | Global     | 查询缓存被访问的次数。                                       |
| Qcache_inserts                    | Global     | 加入到缓存的查询数量。                                       |
| Qcache_lowmem_prunes              | Global     | 由于内存较少从缓存删除的查询数量。                           |
| Qcache_not_cached                 | Global     | 非缓存查询数(不可缓存，或由于query_cache_type设定值未缓存)。 |
| Qcache_queries_in_cache           | Global     | 登记到缓存内的查询的数量。                                   |
| Qcache_total_blocks               | Global     | 查询缓存内的总块数。                                         |
| Queries                           | Both       | 服务器执行的请求个数，包含存储过程中的请求。                 |
| Questions                         | Both       | 已经发送给服务器的查询的个数。                               |
| Rpl_status                        | Global     | 失败安全复制状态(还未使用)。                                 |
| Select_full_join                  | Both       | 没有使用索引的联接的数量。如果该值不为0,你应仔细检查表的索引 |
| Select_full_range_join            | Both       | 在引用的表中使用范围搜索的联接的数量。                       |
| Select_range                      | Both       | 在第一个表中使用范围的联接的数量。一般情况不是关键问题，即使该值相当大。 |
| Select_range_check                | Both       | 在每一行数据后对键值进行检查的不带键值的联接的数量。如果不为0，你应仔细检查表的索引。 |
| Select_scan                       | Both       | 对第一个表进行完全扫描的联接的数量。                         |
| Slave_heartbeat_period            | Global     | 复制的心跳间隔                                               |
| Slave_open_temp_tables            | Global     | 从服务器打开的临时表数量                                     |
| Slave_received_heartbeats         | Global     | 从服务器心跳数                                               |
| Slave_retried_transactions        | Global     | 本次启动以来从服务器复制线程重试次数                         |
| Slave_running                     | Global     | 如果该服务器是连接到主服务器的从服务器，则该值为ON。         |
| Slow_launch_threads               | Both       | 创建时间超过slow_launch_time秒的线程数。                     |
| Slow_queries                      | Both       | 查询时间超过long_query_time秒的查询的个数。                  |
| Sort_merge_passes                 | Both       | 排序算法已经执行的合并的数量。如果这个变量值较大，应考虑增加sort_buffer_size系统变量的值。 |
| Sort_range                        | Both       | 在范围内执行的排序的数量。                                   |
| Sort_rows                         | Both       | 已经排序的行数。                                             |
| Sort_scan                         | Both       | 通过扫描表完成的排序的数量。                                 |
| ssl＊                             |            | ssl连接相关                                                  |
| Table_locks_immediate             | Global     | 立即获得的表的锁的次数。                                     |
| Table_locks_waited                | Global     | 不能立即获得的表的锁的次数。如果该值较高，并且有性能问题，你应首先优化查询，然后拆分表或使用复制。 |
| Threads_cached                    | Global     | 线程缓存内的线程的数量。                                     |
| Threads_connected                 | Global     | 当前打开的连接的数量。                                       |
| Threads_created                   | Global     | 创建用来处理连接的线程数。如果Threads_created较大，你可能要增加thread_cache_size值。缓存访问率的计算方法Threads_created/Connections。 |
| Threads_running                   | Global     | 激活的（非睡眠状态）线程数。                                 |
| Uptime                            | Global     | 服务器已经运行的时间（以秒为单位）。                         |
| Uptime_since_flush_status         | Global     | 最近一次使用FLUSH STATUS 的时间（以秒为单位）。              |

 

注意：

MySQL客户端连接成功后，通过使用show [session|global] status 命令可以提供服务器状态信息。其中的session来表示当前的连接的统计结果，global来表示自数据库上次启动至今的统计结果。默认是session级别的。下面的例子：show status like ‘Com_%’;其中Com_XXX表示XXX语句所执行的次数。重点注意：Com_select,Com_insert,Com_update,Com_delete通过这几个参数，可以容易地了解到当前数据库的应用是以插入更新为主还是以查询操作为主，以及各类的SQL大致的执行比例是多少。

还有几个常用的参数便于用户了解数据库的基本情况。
Connections：试图连接MySQL服务器的次数
Uptime：服务器工作的时间（单位秒）
Slow_queries：慢查询的次数 (默认是慢查询时间10s)

 

二、如何定位慢查询

在默认情况下mysql不记录慢查询日志，需要进入my.ini中mysqld中去设置：

log-slow-queries = D:\phpStudy\mysql\mysqlslowquery.log//指定慢查询的日志
long_query_time = 1//慢查询的时间

通过慢查询日志定位执行效率较低的SQL语句。慢查询日志记录了所有执行时间超过long_query_time所设置的SQL语句。show variables like ‘long_query_time’;set long_query_time=2;

1.Show variables like ‘long_query_time';

2.可以重新设置 set long_query_time=2

 

****在默认情况下，mysql不会记录慢查询的语句，需要启用这个选项，

该慢查询日志会放在data目录下[在mysql5.0这个版本中时放在 mysql安装目录/data/下],在 mysql5.5.19下是需要查看

my.ini 的 **datadir=”C:/Documents and Settings/All Users/Application Data/MySQL/MySQL Server 5.5/Data/“**

**来确定****.**

另外启动慢查询有两种方法针对 mysql5.5

bin\mysqld.exe – -safe-mode  – -slow-query-log

也可以在my.ini 文件中配置:

[mysqld]

\# The TCP/IP Port the MySQL Server will listen on

port=3306

slow-query-log

 

三、show status常用参考：



#show SESSION status like "Com_insert";
#INSERT INTO dept values(1,'name','beijing');
#show status like 'connections';
#show status like 'uptime';
#show global status like 'slow_queries';
#Show variables like 'long_query_time';
#explain select *  from emp where emp.empno=123456;
#select *  from emp;

#show VARIABLES;查看数据库服务器的配置信息
#show global status;查看mysql服务器从开启到现在的各种状态值
#show VARIABLES like '%slow%';通过查询服务器配置信息来查看慢查询日志的开启状况，慢查询时间以及日志的存放位置
#show global status like '%slow%';通过查询服务器的运行状态来查询慢查询的次数
#show VARIABLES like 'max_connections';查看服务器配置的最大连接数A
#show global status like 'Max_used_connections';服务器并发连接数B
#理想值为85% B/A*100%





3.1  关于key_buffer_size:

在服务器的配置参数中有一个key_buffer_size，其实对MyISAM表影响最大的一个参数，使用

​	#   show variables like 'key_buffer_size';

可以查询此参数的信息。具体意思是索引缓冲区的大小。

mysql&gt; show global status like 'key_read%';

+-------------------+----------+

| Variable_name     | Value    |

+-------------------+----------+

| Key_read_requests | 39393115 |

| Key_reads         | 174565   |

+-------------------+----------+

2 rows in set (0.23 sec)

上面有2个结果值，这里解释一下它们各代表什么意思：

​	Key_read_requests：表示从缓存读取索引的请求次数

​	Key_reads：从磁盘读取索引的请求次数

让我们来计算一下索引未命中缓存的概率：

​	key_cache_miss_rate = Key_reads / Key_read_requests * 100%

通过上面的公式我们计算上面数据索引为命中缓存的概率值为：0.4431%，约250个索引读取请求就有一个直接读硬盘，这个值好像不是很理想。key_cache_miss_rate在0.1%以下都很好(每1000个请求有一个直接读硬盘)，所以理论来上来说，这个比值越小越好，但过小的话，难免造成内存浪费。

 

3.2 关于临时表优化：

1. mysql> show global status like ‘created_tmp%’;
2. +————————-+———+
3. | Variable_name           | Value   |
4. +————————-+———+
5. | Created_tmp_disk_tables | 4184337 |
6. | Created_tmp_files       | 4124    |
7. | Created_tmp_tables      | 4215028 |
8. +————————-+———+

每次创建临时表，Created_tmp_tables增加，如果是在磁盘上创建临时表，Created_tmp_disk_tables也增加,Created_tmp_files表示MySQL服务创建的临时文件文件数：
Created_tmp_disk_tables / Created_tmp_tables * 100% ＝ 99% （理想值<= 25%）

参考临时表解决问题文章：http://blog.csdn.net/chenchaoxing/article/details/25214397

3.3 open table 的情况

 

1. mysql> show global status like ‘open%tables%’;
2. +—————+——-+
3. | Variable_name | Value |
4. +—————+——-+
5. | Open_tables   | 1024  |
6. | Opened_tables | 1465  |
7. +—————+——-+

Open_tables 表示打开表的数量，Opened_tables表示打开过的表数量，如果Opened_tables数量过大，说明配置中 table_cache(5.1.3之后这个值叫做table_open_cache)值可能太小，我们查询一下服务器table_cache值

 

1. mysql> show variables like ‘table_cache’;
2. +—————+——-+
3. | Variable_name | Value |
4. +—————+——-+
5. | table_cache   | 1024  |
6. +—————+——-+

Open_tables / Opened_tables * 100% =69% 理想值 （>= 85%）
Open_tables / table_cache * 100% = 100% 理想值 (<= 95%)

3.4 进程使用情况

 

1. mysql> show global status like ‘Thread%’;
2. +——————-+——-+
3. | Variable_name     | Value |
4. +——————-+——-+
5. | Threads_cached    | 31    |
6. | Threads_connected | 239   |
7. | Threads_created   | 2914  |
8. | Threads_running   | 4     |
9. +——————-+——-+

如果我们在MySQL服务器配置文件中设置了thread_cache_size，当客户端断开之后，服务器处理此客户的线程将会缓存起来以响应下一个客户而不是销毁（前提是缓存数未达上限）。Threads_created表示创建过的线程数，如果发现Threads_created值过大的话，表明 MySQL服务器一直在创建线程，这也是比较耗资源，可以适当增加配置文件中thread_cache_size值，查询服务器 thread_cache_size配置：

1. mysql> show variables like ‘thread_cache_size’;
2. +——————-+——-+
3. | Variable_name     | Value |
4. +——————-+——-+
5. | thread_cache_size | 32    |
6. +——————-+——-+

3.5 查询缓存(query cache)

 

1. mysql> show global status like ‘qcache%’;
2. +————————-+———-+
3. | Variable_name           | Value    |
4. +————————-+———-+
5. | Qcache_free_blocks      | 2226     |
6. | Qcache_free_memory      | 10794944 |
7. | Qcache_hits             | 5385458  |
8. | Qcache_inserts          | 1806301  |
9. | Qcache_lowmem_prunes    | 433101   |
10. | Qcache_not_cached       | 4429464  |
11. | Qcache_queries_in_cache | 7168     |
12. | Qcache_total_blocks     | 16820    |
13. +————————-+———-+

Qcache_free_blocks：缓存中相邻内存块的个数。数目大说明可能有碎片。FLUSH QUERY CACHE会对缓存中的碎片进行整理，从而得到一个空闲块。
Qcache_free_memory：缓存中的空闲内存。
Qcache_hits：每次查询在缓存中命中时就增大
Qcache_inserts：每次插入一个查询时就增大。命中次数除以插入次数就是不中比率。
Qcache_lowmem_prunes：缓存出现内存不足并且必须要进行清理以便为更多查询提供空间的次数。这个数字最好长时间来看；如果这个数字在不断增长，就表示可能碎片非常严重，或者内存很少。（上面的          free_blocks和free_memory可以告诉您属于哪种情况）
Qcache_not_cached：不适合进行缓存的查询的数量，通常是由于这些查询不是 SELECT 语句或者用了now()之类的函数。
Qcache_queries_in_cache：当前缓存的查询（和响应）的数量。
Qcache_total_blocks：缓存中块的数量。

我们再查询一下服务器关于query_cache的配置：

1. mysql> show variables like ‘query_cache%’;
2. +——————————+———-+
3. | Variable_name                | Value    |
4. +——————————+———-+
5. | query_cache_limit            | 33554432 |
6. | query_cache_min_res_unit     | 4096     |
7. | query_cache_size             | 33554432 |
8. | query_cache_type             | ON       |
9. | query_cache_wlock_invalidate | OFF      |
10. +——————————+———-+

各字段的解释：

query_cache_limit：超过此大小的查询将不缓存
query_cache_min_res_unit：缓存块的最小大小
query_cache_size：查询缓存大小
query_cache_type：缓存类型，决定缓存什么样的查询，示例中表示不缓存 select sql_no_cache 查询
query_cache_wlock_invalidate：当有其他客户端正在对MyISAM表进行写操作时，如果查询在query cache中，是否返回cache结果还是等写操作完成再读表获取结果。

query_cache_min_res_unit的配置是一柄”双刃剑”，默认是4KB，设置值大对大数据查询有好处，但如果你的查询都是小数据查询，就容易造成内存碎片和浪费。

查询缓存碎片率 = Qcache_free_blocks / Qcache_total_blocks * 100%

如果查询缓存碎片率超过20%，可以用FLUSH QUERY CACHE整理缓存碎片，或者试试减小query_cache_min_res_unit，如果你的查询都是小数据量的话。

查询缓存利用率 = (query_cache_size – Qcache_free_memory) / query_cache_size * 100%

查询缓存利用率在25%以下的话说明query_cache_size设置的过大，可适当减小；查询缓存利用率在80％以上而且Qcache_lowmem_prunes > 50的话说明query_cache_size可能有点小，要不就是碎片太多。

查询缓存命中率 = (Qcache_hits – Qcache_inserts) / Qcache_hits * 100%

示例服务器 查询缓存碎片率 ＝ 20.46％，查询缓存利用率 ＝ 62.26％，查询缓存命中率 ＝ 1.94％，命中率很差，可能写操作比较频繁吧，而且可能有些碎片。

3.6 排序使用情况



1. mysql> show global status like ‘sort%’;
2. +——————-+———-+
3. | Variable_name     | Value    |
4. +——————-+———-+
5. | Sort_merge_passes | 2136     |
6. | Sort_range        | 81888    |
7. | Sort_rows         | 35918141 |
8. | Sort_scan         | 55269    |
9. +——————-+———-+

Sort_merge_passes 包括两步。MySQL 首先会尝试在内存中做排序，使用的内存大小由系统变量 Sort_buffer_size 决定，如果它的大小不够把所有的记录都读到内存中，MySQL 就会把每次在内存中排序的结果存到临时文件中，等 MySQL 找到所有记录之后，再把临时文件中的记录做一次排序。这再次排序就会增加 Sort_merge_passes。实际上，MySQL 会用另一个临时文件来存再次排序的结果，所以通常会看到 Sort_merge_passes 增加的数值是建临时文件数的两倍。因为用到了临时文件，所以速度可能会比较慢，增加 Sort_buffer_size 会减少 Sort_merge_passes 和 创建临时文件的次数。但盲目的增加 Sort_buffer_size 并不一定能提高速度，见 How fast can you sort data with MySQL?（引自http://qroom.blogspot.com/2007/09/mysql-select-sort.html）

另外，增加read_rnd_buffer_size(3.2.3是record_rnd_buffer_size)的值对排序的操作也有一点的好处，参见：http://www.mysqlperformanceblog.com/2007/07/24/what-exactly-is- read_rnd_buffer_size/

11.文件打开数(open_files)
mysql> show global status like ‘open_files’;

1. +—————+——-+
2. | Variable_name | Value |
3. +—————+——-+
4. | Open_files    | 821   |
5. +—————+——-+
6. 
7. mysql> show variables like ‘open_files_limit’;
8. +——————+——-+
9. | Variable_name    | Value |
10. +——————+——-+
11. | open_files_limit | 65535 |
12. +——————+——-+

比较合适的设置：Open_files / open_files_limit * 100% <= 75％

正常

3.7  表锁情况

1. mysql> show global status like ‘table_locks%’;
2. +———————–+———+
3. | Variable_name         | Value   |
4. +———————–+———+
5. | Table_locks_immediate | 4257944 |
6. | Table_locks_waited    | 25182   |
7. +———————–+———+

Table_locks_immediate 表示立即释放表锁数，Table_locks_waited表示需要等待的表锁数，如果 Table_locks_immediate / Table_locks_waited > 5000，最好采用InnoDB引擎，因为InnoDB是行锁而MyISAM是表锁，对于高并发写入的应用InnoDB效果会好些.

3.8  表扫描情况

 mysql> show global status like ‘handler_read%’;

1. +———————–+———–+
2. | Variable_name         | Value     |
3. +———————–+———–+
4. | Handler_read_first    | 108763    |
5. | Handler_read_key      | 92813521  |
6. | Handler_read_next     | 486650793 |
7. | Handler_read_prev     | 688726    |
8. | Handler_read_rnd      | 9321362   |
9. | Handler_read_rnd_next | 153086384 |
10. +———————–+———–+

各字段解释参见http://hi.baidu.com/thinkinginlamp/blog/item/31690cd7c4bc5cdaa144df9c.html，调出服务器完成的查询请求次数：

 mysql> show global status like ‘com_select’;

1. +—————+———+
2. | Variable_name | Value   |
3. +—————+———+
4. | Com_select    | 2693147 |
5. +—————+———+

计算表扫描率：

表扫描率 ＝ Handler_read_rnd_next / Com_select

如果表扫描率超过4000，说明进行了太多表扫描，很有可能索引没有建好，增加read_buffer_size值会有一些好处，但最好不要超过8MB。

参考文章：

http://www.ttlsa.com/mysql/mysql_show_status_descriptsions/

http://lxneng.iteye.com/blog/451985

http://blog.csdn.net/chenchaoxing/article/details/25214397



# [mysql优化（三）–explain分析sql语句执行效率](http://www.daydaytc.com/mysql/556.html)

Explain命令在解决数据库性能上是第一推荐使用命令，大部分的性能问题可以通过此命令来简单的解决，Explain可以用来查看SQL语句的执行效 果，可以帮助选择更好的索引和优化查询语句，写出更好的优化语句。

Explain语法：explain select … from … [where …]

例如：explain select * from news;

输出：
`+----+-------------+-------+-------+-------------------+---------+---------+-------+------| id | select_type | table | type | possible_keys | key | key_len | ref | rows | Extra |+----+-------------+-------+-------+-------------------+---------+---------+-------+------`

下面对各个属性进行了解：

1、id：这是SELECT的查询序列号

2、select_type：select_type就是select的类型，可以有以下几种：

> SIMPLE：简单SELECT(不使用UNION或子查询等)
>
> PRIMARY：最外面的SELECT
>
> UNION：UNION中的第二个或后面的SELECT语句
>
> DEPENDENT UNION：UNION中的第二个或后面的SELECT语句，取决于外面的查询
>
> UNION RESULT：UNION的结果。
>
> SUBQUERY：子查询中的第一个SELECT
>
> DEPENDENT SUBQUERY：子查询中的第一个SELECT，取决于外面的查询
>
> DERIVED：导出表的SELECT(FROM子句的子查询)

3、table：显示这一行的数据是关于哪张表的

4、type：这列最重要，显示了连接使用了哪种类别,有无使用索引，是使用Explain命令分析性能瓶颈的关键项之一。

> 结果值从好到坏依次是：
>
> system > const > eq_ref > ref > fulltext > ref_or_null > index_merge > unique_subquery > index_subquery > range > index > ALL
>
> 一般来说，得保证查询至少达到range级别，最好能达到ref，否则就可能会出现性能问题。

5、possible_keys：列指出MySQL能使用哪个索引在该表中找到行

6、key：显示MySQL实际决定使用的键（索引）。如果没有选择索引，键是NULL

7、key_len：显示MySQL决定使用的键长度。如果键是NULL，则长度为NULL。使用的索引的长度。在不损失精确性的情况下，长度越短越好

8、ref：显示使用哪个列或常数与key一起从表中选择行。

9、rows：显示MySQL认为它执行查询时必须检查的行数。

10、Extra：包含MySQL解决查询的详细信息，也是关键参考项之一。

> Distinct
> 一旦MYSQL找到了与行相联合匹配的行，就不再搜索了
>
> Not exists
> MYSQL 优化了LEFT JOIN，一旦它找到了匹配LEFT JOIN标准的行，
>
> 就不再搜索了
>
> Range checked for each
>
> Record（index map:#）
> 没有找到理想的索引，因此对于从前面表中来的每一 个行组合，MYSQL检查使用哪个索引，并用它来从表中返回行。这是使用索引的最慢的连接之一
>
> Using filesort
> 看 到这个的时候，查询就需要优化了。MYSQL需要进行额外的步骤来发现如何对返回的行排序。它根据连接类型以及存储排序键值和匹配条件的全部行的行指针来 排序全部行
>
> Using index
> 列数据是从仅仅使用了索引中的信息而没有读取实际的行动的表返回的，这发生在对表 的全部的请求列都是同一个索引的部分的时候
>
> Using temporary
> 看到这个的时候，查询需要优化了。这 里，MYSQL需要创建一个临时表来存储结果，这通常发生在对不同的列集进行ORDER BY上，而不是GROUP BY上
>
> Using where
> 使用了WHERE从句来限制哪些行将与下一张表匹配或者是返回给用户。如果不想返回表中的全部行，并且连接类型ALL或index， 这就会发生，或者是查询有问题

其他一些Tip：

1. 当type 显示为 “index” 时，并且Extra显示为“Using Index”， 表明使用了覆盖索引。

来源：http://www.cnblogs.com/hailexuexi/archive/2011/11/20/2256020.html



# [mysql优化（四）–索引](http://www.daydaytc.com/mysql/558.html)

一、 四种索引类型：

主键索引，唯一索引，全文索引，普通索引

二、  为什么建立索引比较快

一般的数据表是按照行来存储的，字段多、有长字段的表的记录就会长，就要占用更多的空间来存储，而索引是建立在一个或少数几个字段上的特殊数据结构，一个索引项的存储开销和表的记录相比是很小的。
所谓查询速度更快，其实发生在计算机内部的动作有三大步骤，即输入、处理和输出，完成整套动作之后，你才能体会到快慢，而在这三个动作中，输入通常就是从硬盘上装载数据到内存，它耗时最甚，那么读取索引和读取表的全部行的数据量差异就大大影响最终性能。另外，索引是排好序的，能够实施二分查找算法，比其行扫描（读取表的所有记录，逐行判断是否满足条件）这种顺序查找算法来说，效率提升也以数量级计。

三、 各种索引的增删查改

3.1 增加

3.1.1 主键索引添加

当一张表，把某个列设为主键的时候，则该列就是主键索引
create table aaa
(id int unsigned primary key auto_increment ,
name varchar(32) not null defaul ‘’);
这是id 列就是主键索引.

如果你创建表时，没有指定主键索引，也可以在创建表后，在添加, 指令:

alter table 表名 add primary key (列名);

举例:
create table bbb (id int , name varchar(32) not null default ‘’);

alter table bbb add primary key (id);

3.1.2 普通索引

一般来说，普通索引的创建，是先创建表，然后在创建普通索引
比如:
create table ccc(
id int unsigned,
name varchar(32)
)

create index 索引名 on 表 (列1,列名2);

3.1.3 全文索引

全文索引，主要是针对文件，文本的检索, 比如文章, 全文索引针对MyISAM有用.

创建 ：

CREATE TABLE articles (

id INT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,

title VARCHAR(200),

body TEXT,

FULLTEXT (title,body)

)engine=myisam charset utf8;

 

如何使用全文索引:

错误用法:

select * from articles where body like ‘%mysql%’; 【不会使用到全文索引】

mysql> explain select * from articles where body like ‘mysql%’\G

*************************** 1. row ***************************

id: 1

select_type: SIMPLE

table: articles

type: ALL

possible_keys: NULL

key: NULL

key_len: NULL

ref: NULL

rows: 6

Extra: Using where

1 row in set (0.00 sec)

证明:

explain  select * from articles where body like ‘%mysql%’

 

正确的用法是:

select * from articles where match(title,body) against(‘database’); 【可以】

mysql> explain select * from articles where match(title,body) against ( ‘mysql’)\G

*************************** 1. row ***************************

id: 1

select_type: SIMPLE

table: articles

type: fulltext

possible_keys: title

key: title

key_len: 0

ref: NULL

rows: 1

Extra: Using where

1 row in set (0.00 sec)

☞ 说明:

1. 在mysql中fulltext 索引只针对 myisam生效
2. mysql自己提供的fulltext针对英文生效->sphinx (coreseek) 技术处理中文
3. 使用方法是 match(字段名..) against(‘关键字’)
4. 全文索引：停止词, 因为在一个文本中，创建索引是一个无穷大的数，因此，对一些常用词和字符，就不会创建，这些词，称为停止词.比如（a，b，mysql，the）

3.1.4 唯一索引

①当表的某列被指定为unique约束时，这列就是一个唯一索引

create table ddd(id int primary key auto_increment , name varchar(32) unique);

这时, name 列就是一个唯一索引.

unique字段可以为NULL,并可以有多NULL, 但是如果是具体内容，则不能重复，

但是不能存有重复的空字符串’’

主键字段，不能为NULL,也不能重复.

②在创建表后，再去创建唯一索引

create table eee(id int primary key auto_increment, name varchar(32));

create unique index 索引名  on 表名 (列表..);

 

3.2 索引查询

desc 表名 【该方法的缺点是：　不能够显示索引名.】

show index(es) from 表名

show keys from 表名

 

3.3 索引删除

alter table 表名 drop index 索引名;

如果删除主键索引。

alter table 表名 drop primary key

 

3.4 修改索引

先删除索引再重新创建索引

 

四、索引的代价

占用磁盘空间、使得dml 增删改操作变慢。索引主要用于查操作变快。

 

五、哪些列上适合加索引

5.1 较频繁的作为查询条件的字段应该创建索引

5.2 唯一性太差的字段不适合创建索引

5.3 更新非常频繁的字段不适合创建索引

5.4 不会出现在where字句中的字段不适合创建索引

总结: 满足以下条件的字段，才应该创建索引.

a: 肯定在where条件经常使用 b: 该字段的内容不是唯一的几个值(sex) c: 字段内容不是频繁变化.

 

六、查看索引的使用情况

如果索引正在工作，Handler_read_key的值将很高，这个值代表了一个行被索引值读的次数，很低的值表明增加索引得到的性能改善不高，因为索引并不经常使用。

Handler_read_rnd_next的值高则意味着查询运行低效，并且应该建立索引补救。这个值的含义是在数据文件中读下一行的请求数。如果你正进行大量的表扫描，该值较高。通常说明表索引不正确或写入的查询没有利用索引。

语法：    mysql> show status like ‘Handler_read%';

七、使用索引的几个注意点

7.1  对于创建的多列索引，只要查询条件使用了最左边的列，索引一般就会被使用。如果只使用右边的列，索引不会被使用。

7.2 对于使用like的查询，查询如果是 ‘%aaa’ 不会使用到索引。‘aaa%’ 会使用到索引。在like查询时，关键的 ‘关键字’ , 最前面，不能使用 % 或者 _这样的字符.， 如果一定要前面有变化的值，则考虑使用 全文索引->sphinx.

7.3  如果条件中有or，即使其中有条件带索引也不会使用。换言之，就是要求使用的所有字段，都必须建立索引, 我们建议大家尽量避免使用or 关键字

7.4  如果列类型是字符串，那一定要在条件中将数据使用引号引用起来。否则不使用索引。(添加时,字符串必须’’), 也就是，如果列是字符串类型，就一定要用 ‘’ 把他包括起来.

7.5  如果mysql估计使用全表扫描要比使用索引快，则不使用索引。

 

八、 如何选择mysql的存储引擎

8.1 在开发中，我们经常使用的存储引擎 myisam / innodb/ memory
myisam 存储: 如果表对事务要求不高，同时是以查询和添加为主的，我们考虑使用myisam存储引擎. ,比如 bbs 中的 发帖表，回复表.

INNODB 存储: 对事务要求高，保存的数据都是重要数据，我们建议使用INNODB,比如订单表，账号表.

8.2 MyISAM 和 INNODB的区别

1. 事务安全（MyISAM不支持事务，INNODB支持事务）
2. 查询和添加速度（MyISAM批量插入速度快）
3. 支持全文索引（MyISAM支持全文索引，INNODB不支持全文索引）
4. 锁机制（MyISAM时表锁，innodb是行锁）
5. 外键 MyISAM 不支持外键， INNODB支持外键. (在PHP开发中，通常不设置外键，通常是在程序中保证数据的一致)

Memory 存储，比如我们数据变化频繁，不需要入库，同时又频繁的查询和修改，我们考虑使用memory, 速度极快. （如果mysql重启的话，数据就不存在了）

- 如果你的数据库的存储引擎是myisam,请一定记住要定时进行碎片整理，因为在myisam引擎中，如果删除数据，空间不会被回收，索引你会发现虽然数据删除很多，但是空间占用依然很厉害，索引需要使用optimize语句进行空间的回收整理。



# [mysql优化(五)–sql语句优化小技巧](http://www.daydaytc.com/mysql/561.html)

一  对于大批量的数据插入操作：

在myisam引擎中，可以先关闭索引，然后进行插入操作，最后再开启索引。这样插入速度会加快。

对于innodb引擎，

1，将要导入的数据按照主键排序
2，set unique_checks=0,关闭唯一性校验。
3，set autocommit=0,关闭自动提交。

 

二 优化group by 语句

默认情况，MySQL对所有的group by col1,col2进行排序。这与在查询中指定order by col1, col2类似。如果查询中包括group by但用户想要避免排序结果的消耗，则可以使用order by null禁止排序

三、连接代替子查询

有些情况下，可以使用连接来替代子查询。因为使用join，MySQL不需要在内存中创建临时表。

Mysql4.1开始支持SQL的子查询。这个技术可以使用SELECT语句来创建一个单列的查询结果，然后把这个结果作为过滤条件用在另一个查询中。使用子查询可以一次性的完成很多逻辑上需要多个步骤才能完成的SQL操作，同时也可以避免事务或者表锁死，并且写起来也很容易。但是，有些情况下，子查询可以被更有效率的连接JOIN替代。

假设我们要将所有没有订单记录的用户取出来，可以用下面这个查询完成：
SELECT * FROM customerinfo WHERE CustomerID NOT in (SELECT CustomerID FROM salesinfo )

如果使用连接JOIN来完成这个查询工作，速度将会快很多。尤其是当salesinfo表中对CustomerID建有索引的话，性能将会更好，查询如下：
SELECT * FROM customerinfo
LEFT JOIN salesinfoON customerinfo.CustomerID=salesinfo.CustomerID
WHERE salesinfo.CustomerID IS NULL

连接JOIN之所以更有效率一些，是因为 MySQL不需要在内存中创建临时表来完成这个逻辑上的需要两个步骤的查询工作。

四、or查询

如果想要在含有or的查询语句中利用索引，则or之间的每个条件列都必须用到索引，如果没有索引，则应该考虑增加索引