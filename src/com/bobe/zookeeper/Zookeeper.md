#  Zookeeper系列学习教程

## Zookeeper四字命令

| zookeeper四字命令 | 命令描述信息                                   |
| ------------- | ---------------------------------------- |
| conf          | 输出相关服务配置信息                               |
| cons          | 列出详细连接在服务器客户端完全的详细信息                     |
| dump          | 列出未经处理的会话和临时节点                           |
| envi          | 列出详细的环境配置信息                              |
| reqs          | 列出未经处理的请求信息                              |
| ruok          | 列举出测试服务相关信息  如果返回imok  成功， 否则没有任何回应。     |
| stat          | 输出关于性能和连接客户端的列表                          |
| wchs          | 列出服务器watch详细信息。                          |
| wchc          | 通过 session 列出服务器 watch 的详细信息，它的输出是一个与 watch 相关的会话的列表。 |
| wchp          | 通过路径列出服务器 watch 的详细信息。它输出一个与 session 相关的路径。 |
|               |                                          |

```shell
命令使用实例
echo ruok | nc localhost 2181 :显示本地主机下zookeeper运行状态
echo stat | nc 192.168.241.128 2181:显示服务器连接状态
echo conf | nc 192.168.241.128 2181:显示zookeeper服务配置信息。
echo wchs|nc 192.168.241.128 2181
linux nc 命令说明
 想要连接到某处: nc [-options] hostname port[s] [ports] …
 绑定端口等待连接: nc -l port [-options] [hostname] [port]
```

## Zookeeper  简单api使用

Zookeeper API共包含五个包，分别为：

（1）org.apache.zookeeper

（2）org.apache.zookeeper.data

（3）org.apache.zookeeper.server

（4）org.apache.zookeeper.server.quorum

（5）org.apache.zookeeper.server.upgrade



zookeeper分布式锁实现java

```java
锁（Lock）

完全分布式锁是全局同步的，这意味着在任何时刻没有两个客户端会同时认为它们都拥有相同的锁，使用 Zookeeper 可以实现分布式锁，需要首先定义一个锁节点（lock root node）。

需要获得锁的客户端按照以下步骤来获取锁：

保证锁节点（lock root node）这个父根节点的存在，这个节点是每个要获取lock客户端共用的，这个节点是PERSISTENT的。
第一次需要创建本客户端要获取lock的节点，调用 create( )，并设置 节点为EPHEMERAL_SEQUENTIAL类型，表示该节点为临时的和顺序的。如果获取锁的节点挂掉，则该节点自动失效，可以让其他节点获取锁。

在父锁节点（lock root node）上调用 getChildren( ) ，不需要设置监视标志。 (为了避免“羊群效应”).

按照Fair竞争的原则，将步骤3中的子节点（要获取锁的节点）按照节点顺序的大小做排序，取出编号最小的一个节点做为lock的owner，判断自己的节点id
是否就为owner id，如果是则返回，lock成功。如果不是则调用 exists( )监听比自己小的前一位的id，关注它锁释放的操作（也就是exist watch）。

如果第4步监听exist的watch被触发，则继续按4中的原则判断自己是否能获取到lock。

释放锁：需要释放锁的客户端只需要删除在第2步中创建的节点即可。

注意事项：

一个节点的删除只会导致一个客户端被唤醒，因为每个节点只被一个客户端watch，这避免了“羊群效应”。
```

```shell
linux 中文件清空方法:
使用重定向的方式 true >  filename、  : >  filename
                echo -n '' > filename
使用 cat / cp/ dd 实用工具及 /dev/null 设备来清空文件
cat /dev/null > filename   将文件内容重新定向到空文件设备中
/dev/null 设备文件是一个特殊的文件，它将清空送到它这里来的所有输入，而它的输出则可被视为一个空文件。
cp /dev/null filename  将文件复制到空设备中。
 if 代表输入文件，of 代表输出文件  ： dd if=/dev/null of=access.log 


```