## hessian相关开发技巧

---

hessian是一个采用二进制格式传输的服务框架，相对传统soap web service，更轻量，更快速。

Hessian是二进制的web service协议，官方网站提供Java、Flash/Flex、Python、C++、.NET C#等实现。Hessian和Axis、XFire都能实现web service方式的远程方法调用，区别是Hessian是二进制协议，Axis、XFire则是SOAP协议，所以从性能上说Hessian远优于后两者，并且Hessian的JAVA使用方法非常简单。Hessian由于没有WSDL这种服务描述文件去对实现进行规定，似乎更适合内部分布式系统之间的交互，对外提供服务还是使用后两者更体面些。hessian采用的是二进制RPC协议，因为采用了二进制协议，所以它很适合于发送二进制数据，Hessian主要作面向对象的消息通信。Hessian的初衷就是支持动态类型，格式紧凑，跨语言Hessian是使用自己的序列化机制实现的编组和反编组，其支持的数据类型是有限制的，不支持复杂的对象，可以穿透防火墙，在这里不得不说一下RMI：RMI是一组用户开发分布式应用程序的API。他使用的是java序列化机制实现调用及返回值的编组于反编组。它使用Java语言接口定义了远程对象，它集合了Java序列化和Java远程方法协议（Java Remote Method Protocol）。他可以被看做是RPC的Java版本，因为传统的RPC并不能很好的应用于分布式对象系统。而Java RMI则支持存储于不同地址空间的程序级对象之间彼此进行通信，实现远程对象之间的无缝远程调用。他也有它的缺点，他只能通过RMI协议来进行访问无法通过HTTP协议访问，无法穿透防火墙。

hessian开发步骤

- 导入Hessian的Jar包

- 设计接口

- 实现接口：必须继承HessianServlet，接口参数对象必须实现序列化

- 配置web.xml

  ```xml
  <servlet>
          <!-- 配置 HessianServlet，Servlet的名字随便配置，例如这里配置成ServiceServlet-->
          <servlet-name>ServiceServlet</servlet-name>
          <servlet-class>com.caucho.hessian.server.HessianServlet</servlet-class>
          
          <!-- 配置接口的具体实现类 -->
          <init-param>
              <param-name>service-class</param-name>
              <param-value>com.hessian.service.impl.ServiceImpl</param-value>
          </init-param>
      </servlet>
      <!-- 映射 HessianServlet的访问URL地址-->
      <servlet-mapping>
          <servlet-name>ServiceServlet</servlet-name>
          <url-pattern>/ServiceServlet</url-pattern>
      </servlet-mapping>
  ```

Hessian主要作面向对象的消息通信

Hessian的初衷就是支持动态类型，格式紧凑，跨语言Hessian是使用自己的序列化机制实现的编组和反编组，其支持的数据类型是有限制的，不支持复杂的对象，可以穿透防火墙。

## RMI

---

RMI:远程方法调用(Remote Method Invocation)。能够让在某个[Java](http://lib.csdn.net/base/javase)虚拟机上的对象像调用本地对象一样调用另一个java 虚拟机中的对象上的方法。

RMI远程调用步骤：

1，客户对象调用客户端辅助对象上的方法

2，客户端辅助对象打包调用信息（变量，方法名），通过网络发送给服务端辅助对象

3，服务端辅助对象将客户端辅助对象发送来的信息解包，找出真正被调用的方法以及该方法所在对象

4，调用真正服务对象上的真正方法，并将结果返回给服务端辅助对象

5，服务端辅助对象将结果打包，发送给客户端辅助对象

6，客户端辅助对象将返回值解包，返回给客户对象

7，客户对象获得返回值

对于客户对象来说，步骤2-6是完全透明的

搭建一个RMI服务的过程分为以下7步;

1，创建远程方法接口，该接口必须继承自Remote接口

Remote 接口是一个标识接口，用于标识所包含的方法可以从非本地虚拟机上调用的接口，Remote接口本身不包含任何方法

```java
package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
	public String sayHello(String name) throws RemoteException;
}
```

由于远程方法调用的本质依然是网络通信，只不过隐藏了底层实现，网络通信是经常会出现异常的，所以接口的所有方法都必须抛出RemoteException以说明该方法是有风险的

2，创建远程方法接口实现类：

UnicastRemoteObject类的构造函数抛出了RemoteException，故其继承类不能使用默认构造函数，继承类的构造函数必须也抛出RemoteException

由于方法参数与返回值最终都将在网络上传输，故必须是可序列化的

```java
package server;  
  
import java.rmi.RemoteException;  
import java.rmi.server.UnicastRemoteObject;  
  
public class HelloImpl extends UnicastRemoteObject implements Hello {  
    private static final long serialVersionUID = -271947229644133464L;  
  
    public HelloImpl() throws RemoteException{  
        super();  
    }  
  
    public String sayHello(String name) throws RemoteException {  
        return "Hello,"+name;  
    }  
}  
```

3，利用java自带rmic工具生成sutb存根类(jdk1.5.0_15/bin/rmic)

jdk1.2以后的RMI可以通过反射API可以直接将请求发送给真实类，所以不需要skeleton类了

sutb存根为远程方法类在本地的代理，是在服务端代码的基础上生成的，需要HelloImpl.class文件，由于HelloImpl继承了Hello接口，故Hello.class文件也是不可少的

4，启动RMI注册服务(jdk1.5.0_15/bin/rmiregistry)

方式一：后台启动rmiregistry服务

5，编写服务端代码

```java
package server;  
  
import java.rmi.Naming;  
import java.rmi.registry.LocateRegistry;  
  
public class HelloServer {  
    public static void main(String[] args) {  
        try{  
            Hello h = new HelloImpl();  
              
            //创建并导出接受指定port请求的本地主机上的Registry实例。  
            //LocateRegistry.createRegistry(12312);  
              
            /** Naming 类提供在对象注册表中存储和获得远程对远程对象引用的方法 
             *  Naming 类的每个方法都可将某个名称作为其一个参数， 
             *  该名称是使用以下形式的 URL 格式（没有 scheme 组件）的 java.lang.String: 
             *  //host:port/name 
             *  host：注册表所在的主机（远程或本地)，省略则默认为本地主机 
             *  port：是注册表接受调用的端口号，省略则默认为1099，RMI注册表registry使用的著名端口 
             *  name：是未经注册表解释的简单字符串 
             */  
            //Naming.bind("//host:port/name", h);  
            Naming.bind("rmi://192.168.58.164:12312/Hello", h);  
            System.out.println("HelloServer启动成功");  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
}  
```

先创建注册表，然后才能在注册表中存储远程对象信息

6，运行服务端

7，编写客户端代码

```java
package client;  
  
import java.net.MalformedURLException;  
import java.rmi.Naming;  
import java.rmi.NotBoundException;  
import java.rmi.RemoteException;  
  
import server.Hello;  
  
public class HelloClient {  
    public static void main(String[] args) {  
        try {  
            Hello h = (Hello)Naming.lookup("rmi://192.168.58.164:12312/Hello");  
            System.out.println(h.sayHello("zx"));  
        } catch (MalformedURLException e) {  
            System.out.println("url格式异常");  
        } catch (RemoteException e) {  
            System.out.println("创建对象异常");  
            e.printStackTrace();  
        } catch (NotBoundException e) {  
            System.out.println("对象未绑定");  
        }  
    }  
}  
```

# RPC、RMI、SOAP的区别详解

---

**RPC：**（Remote Procedure Call） 
　　被设计为在应用程序间通信的平台中立的方式，它不理会操作系统之间以及语言之间的差异。 支持多语言。

**RMI：**（Remote Method Invocation） 
RPC 的Java版本，EJB的基础技术 
RMI 采用JRMP（Java Remote Method Protocol）通讯协议，是构建在TCP/IP协议上的一种远程调用方法。 
RMI 采用stubs和skeletons来进行远程对象的通讯。 
　　stub充当远程对象的客户端代理，有着和远程对象相同的远程接口。 
　　远程对象的调用实际是通过调用该对象的客户端代理对象stub来完成的。

​    创建远程方法调用的5个步骤：
​    1）定义一个扩展了Remote接口的接口，该接口中的每一个方法必须声明它将产生一个RemoteException异常；
​    2）定义一个实现该接口的类；
​    3）使用rmic程序生成远程实现所需的存根和框架；
​    　　(例如，在demo.rmi.EchoServer.java所在目录运行: rmic demo.rmi.EchoServer)
​    4）创建一个客户程序和服务器进行RMI调用；
​    5）启动rmiregistry并运行自己的服务程序和客户程序。

**RMI与RPC的区别在于： **
1）方法是如何被调用的
　　对RMI来说，如果一个方法在服务器上执行，但是没有相匹配的签名被添加到这个远程接口上，那么这个新方法就不能被RMI客户方所调用。 
　　而在RPC中，当一个请求到达RPC服务器时，请求包含一个参数集和一个文本值，通常为“classname.methodname”形式。 
　　这表明，请求的方法在“classname”类中，名叫“methodname”。 
　　然后，RPC服务器就去搜索与之相匹配的类和方法，并把它作为那种方法参数类型的输入。 
　　这里的参数类型是与RPC请求中的类型匹配的。 一旦匹配成功，方法就被调用了，其结果被编码后返回客户方。
2）对传递信息的限制
　　RMI 调用远程对象方法，允许方法返回 Java 对象以及基本数据类型。 
　　而RPC不允许传递对象，RPC服务的消息由外部数据表示（External Data Representation，XDR）语言来表示。
3）采用的协议不同
​    RPC不支持对象，采用http协议。RMI支持传输对象，采用TCP/IP协议
4）RIM只限于Java语言，而RPC跨语言
另外，RMI优于RPC或SOAP的一点是：在程序开发过程中因为对象或方法不匹配造成的错误可以在编译期被发现，而不用等到运行期。

============================================================================
**RPC, SOAP, WSDL的关系**
============================================================================
RPC, SOAP, WSDL都是web service的关键词，这里描述一下他们的关系，下面的解释可能比较狭义，主要为了帮助理解这三者的关系。
**1.RPC**

如果要调用远端的一个方法，可以使用RMI和RPC，这是2种截然不同的风格。
RMI: (Remote Method Invocation) 直接获取远端方法的签名，进行调用。优点是强类型、编译期可检查错误；缺点是只限于java语言
RPC: (Remote Procedure Call) 采用客户端/服务器方式(请求/响应)，发送请求到服务器端，服务端执行方法后返回结果。优点是跨语言跨平台，缺点是编译期无法排错，只能在运行时检查。
**2.SOAP**

为了包装RPC的请求信息，推出了XML-RPC，但XML-RPC只能使用有限的数据类型种类和一些简单的数据结构。于是就出现了SOAP(Simple Object Access Protocol)。SOAP最主要的工作是使用标准的XML描述了RPC的请求信息(URI/类/方法/参数/返回值)。理论上，SOAP就是一段xml，你可以通过http,smtp等发送它(复制到软盘上，叫快递公司送去也行?)。同样SOAP也是跨语言的。
**3.WSDL**

WSDL(Web Services Description Language)是描述web服务的，是描述怎样访问web服务的。WSDL是用来描述SOAP的，换句话说，WSDL 文件告诉你调用 SOAP 所需要知道的一切。WSDL也是一段xml。现在各个语言对wsdl的支持都很成熟，可以根据同一份wsdl文件生成自己语言的客户端。

