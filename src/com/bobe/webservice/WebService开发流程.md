##webService开发流程
加入注解包，

- 开发webservice接口：@WebService
- 开发webservice接口实现:@Webservice(endpointInterface="com.bobe.webservice.Add")
- 使用简单发布方式
  Endpoint.publish("http://localhost:8083/wsmc/", new AddImpl());
- 浏览器测试webService:http://localhost:8083/wsmc/?wsdl 

---
- 本机进行简单测试webservice

---

##开发web工程时，我们可以通过创建servlet类，在servlet初始化时发布，比较友好

---
  
##WebService基础
   [ 1 ] WebService是两个系统的远程调用，使两个系统进行数据交互，如应用：天气预报服务、银行ATM取款、使用邮箱账号登录各网站等。
   [ 2 ] WebService之间的调用是跨语言的调用。Java、.Net、php，发送Http请求，使用的数据格式是XML格式.
   [ 3 ] 有一些免费的服务，可以供我们使用
   
##应用基础 

##理解服务：现在的应用程序变得越来越复杂，甚至只靠单一的应用程序无法完成全部的工作。更别说只使用一种语言了。因此需要访问别人写的服务，以获得感兴趣的数据。
在写应用程序查询数据库时，并没有考虑过为什么可以将查询结果返回给上层的应用程序，甚至认为，这就是数据库应该做的，其实不然，这是数据库通过TCP/IP协议与另一个应用程序进行交流的结果，而上层是什么样的应用程序，是用什么语言，数据库本身并不知道，它只知道接收到了一份协议，这就是SQL92查询标准协议。

目前的云计算、云查杀都是一种服务，现在比较流行的说法是SOA(面向服务的框架)。

既然数据库可以依据某些标准对外部其他应用程序提供服务、而且不关心对方使用什么语言，那我们为什么就不能实现跨平台、跨语言的服务呢？

只要我们用Java写的代码，可以被任意的语言所调用，我们就实现了跨平台，跨语言的服务！---WebService

因此，WebService，顾名思义就是基于Web的服务。它使用Web(HTTP)方式，接收和响应外部系统的某种请求。从而实现远程调用.

我们可以调用互联网上查询天气信息Web服务，然后将它嵌入到我们的程序(C/S或B/S程序)当中来，当用户从我们的网点看到天气信息时，他会认为我们为他提供了很多的信息服务，但其实我们什么也没有做，只是简单调用了一下服务器上的一段代码而已。

学习WebService可以将你的服务(一段代码)发布到互联网上让别人去调用,也可以调用别人机器上发布的WebService,就像使用自己的代码一样。

（2），基础概念：XML

XML Extensible Markup Language －扩展性标记语言

XML，用于传输格式化的数据，是Web服务的基础。

namespace-命名空间。

（3），基础概念：WSDL

WSDL – WebService Description Language – Web服务描述语言。

通过XML形式说明服务在什么地方－地址。address location

通过XML形式说明服务提供什么样的方法 – 如何调用。operation

（4），基础概念：SOAP

SOAP-Simple Object Access Protocol(简单对象访问协议)

SOAP作为一个基于XML语言的协议用于网上传输数据。

SOAP = 在HTTP的基础上+XML数据。

SOAP是基于HTTP的。

SOAP的组成如下：

Envelope – 必须的部分。以XML的根元素出现。

Headers – 可选的。

Body – 必须的。在body部分，包含要执行的服务器的方法。和发送到服务器的数据。

传递的数据格式：

<Envelope>

      <Header></Header>

      <Body>

                <方法名>

                          方法参数

　　　　    </方法名>

      </Body>

</Envelope>
（5），请求示例：
          以下发出HTTP请求，但不同的是向服务器发送的是XML数据！
         说明：
         （1），因为是在HTTP上发数据，所以必须先遵循HTTP协议
         （2），XML部分即SOAP协议，必须包含Envelope和Body元素。

（6），响应示例：



1.3 应用说明
1，WebService通过HTTP协议完成远程调用

（1），WebService只采用HTTP POST方式传输数据，不使用GET方式;  -- 握手，WSDL-get,（基于soap协议，传输数据格式是XML）

普通http post的contentType为

application/x-www-form-urlencoded

WebService的contentType为－即在Http的基础上发SOAP协议

text/xml 这是基于soap1.1协议。

application/soap+xml 这是基于soap1.2协议。

 （2），WebService从数据传输格式上作了限定。WebService所使用的数据均是基于XML格式的。目前标准的WebService在数据格式上主要采用SOAP协议。SOAP协议实际上就是一种基于XML编码规范的文本协议。

 （3），SOAP – Simple Object Access protocol 简单对像访问协议。是运行在HTTP协议基础之上的协议。其实就是在HTTP协议是传输XML文件，就变成了SOAP协议。

 （4），SOAP1.1和SOAP1.2的 namespace不一样。可以通过查看类

javax.xml.ws.soap.SOAPBinding来查看里面的常量

默认情况下，Jdk1.6只支持soap1.1

即：@BindingType(value=javax.xml.ws.soap.SOAPBinding.SOAP11HTTP_BINDING)

##WebService与Web的区别与联系 

     可以把WebService看作是Web服务器上应用；反过来说，Web服务器是WebService运行时所必需的容器。这就是它们的区别和联系。

WebService的特点
       WebService通过HTTP POST方式接受客户的请求（如果基于soap协议，传输数据格式是XML），只能是POST方式，因为GET方式没有请求体。

WebService与客户端之间一般使用SOAP协议传输XML数据.

它本身就是为了跨平台或跨语言而设计的。

（1） SOAP1.2注意：当使用SOAP12以后，wsimport和Eclipse和WSExplorer都不可以正常使用了，必须使用cxf提供的wsdl2java工具生成本地代码。

（2） 客户端最好发送1.1请求，而服务端最好使用1.2高版本。

与Web服务相关的类，都位于javax.jws.*包中。
@WebService － 它是一个注解，用在类上指定将此类发布成一个ws。
Endpoint – 此类为端点服务类，它的方法publish用于将一个已经添加了@WebService注解对象绑定到一个地址的端口上。
③服务发布成功后，在客户端调用：
启动服务后，在浏览器中输入绑定的服务地址+”?wsdl”即可查看服务的说明书。wsdl- WebService　Description　Language,是以XML文件形式来描述WebService的”说明书”,有了说明书,我们才可以知道如何使用或是调用这个服务.
④使用wsimport –s . http://124.205.244.130:5678/hello?wsdl
即可生成客户端代码。（包含.class文件和.java文件）

此处注意：是生成而不是下载，服务器上并没有所生成的所有的类和方法。

wsimport是jdk自带的,可以根据wsdl文档生成客户端调用代码.当然,无论服务器端的WebService是用什么语言写的,都将在客户端生成Java代码。服务器端用什么写的并不重要。

wsimport.exe位于JAVA_HOME\bin目录下.

常用参数为:

-d<目录>  - 将生成.class文件。默认参数。

-s<目录> - 将生成.java文件。

-p<生成的新包名> -将生成的类，放于指定的包下，自定义包结构。

(wsdlurl) - http://server:port/service?wsdl，必须的参数。

注意：-s不能分开，-s后面有个小点，用于指定源代码生成的目录。点即当前目录。

如果使用了-s参数则会在目录下生成两份代码，一份为.class代码。一份为.java代码。

.class代码，可以经过打包以后使用。.java代码可以直接Copy到我们的项目中运行。

2，通过wsimport生成本地代码，调用网络上的web服务，比如手机号码归属地服务。

进入xml.com.cn找到手机号码归属地服务的wsdl：

客户端调用WebService的方式
 - 通过wsimport生成客户端代码

 - 通过客户端编程的方式调用

 - 通过ajax调用 (js+XML)

 - 通过URLConnection调用

通过客户端编程的方式调用

（1），使用javax.xml.ws.Service类用于访问web服务

（2），关键类Service

方法create – 用户创建Service对像，提供wsdlurl和服务名。

getPort-用于通过指定namespace，portName和接口的范型。

在客户端需要一个与服务器接口完全相同的类。（仍然使用工具生成。但只需要一个接口。并需要简单修改。如果返回的是复杂数据类型如POJO，还需要将POJO一并放到项目中）。

         App.class文件：

     Service s =

Service.create(new URL(“http://192.168.1.108:5678/hello?wsdl”),

                     new QName(targetNamespace,serviceName)

);

HelloService hs = s.getPort(portName,serviceEndpointInterface);

(注意：这里portName=new QName(targetNamespace,portName))

String str = hs.sayHello(“Lisi”,10);

System.out.println(str);                    //打印hello Lisi

说明 ：关键类QName – 被称为完全限定名即：Qualified Name的缩写。

QName 的值包含名称空间 URI、本地部分和前缀。

客户端编程的方式不常用。

 

2.2．3 通过Ajax调用（js+XML）

（1），写一个页面，发送Ajax请求,请求的URL即服务的地址，请求方式是POST，另外，还需要设置请求头，以及手动构造请求体。

<body>

          <input type="text" id="msg" />

          <input type="button" onclick="sendAjaxWS();" value="通过ajax调用webservice服务"/>

</body>

 <head>

          <title>通过ajax调用webservice服务</title>

          <script>

               var xhr;

               function sendAjaxWS(){

                    xhr = new ActiveXObject("Microsoft.XMLHTTP");                 

                   //指定ws的请求地址

                    var wsUrl = "http://192.168.1.108:5678/hello";

                    //手动构造请求体

　　　　　　var requestBody = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" ' + ' 　　　　　　　　xmlns:q0="http://service.itcast.cn/" xmlns:xsd="http://www.w3.org/2001/XMLSchema "'+

' xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">'+

     '<soapenv:Body><q0:sayHello><arg0>'+

document.getElementById("msg").value+'</arg0> <arg1>10</arg1> </q0:sayHello></soapenv:Body></soapenv:Envelope>';

　　　　　　//打开连接

                 xhr.open("POST",wsUrl,true);

                //重新设置请求头       xhr.setRequestHeader("content-type","text/xml;charset=utf8");

               //设置回调函数

                xhr.onreadystatechange = _back;

                //发送请求

                xhr.send(requestBody);

            }

               //定义回调函数

               function _back(){

                    if(xhr.readyState == 4){

                         if(xhr.status == 200){

                              var ret = xhr.responseXML;

                              //解析xml

                              var eles = ret.getElementsByTagName("return")[0];

                              alert(eles.text);

                         }

                    }

               }

          </script>

     </head>

由于使用ajax – js调用web服务完成不同于使用java代码调用。所以，必须要对SOAP文件非常的了解。

一般使用ajax调用，应该是在已经获知了以下信息以后才去调用：

获知请求（request）的soap文本。

获知响应(response)的soap文本。

请求文件和响应文本格式，一般会随web服务的发布一同发布。

我们可以通过WSExplorer获取上面两段文本。

2.2．4 通过URLConnection调用

         1，指定WebService服务的请求地址：

        String wsUrl = "http:// 124.205.244.130:5678/hello";

 　　  2，创建URL：URL url = new URL(wsUrl);

    　  3，建立连接，并将连接强转为Http连接

URLConnection conn = url.openConnection();

          HttpURLConnection con = (HttpURLConnection) conn;

   　   4，设置请求方式和请求头：

          con.setDoInput(true);                  //是否有入参

          con.setDoOutput(true);                //是否有出参

          con.setRequestMethod("POST");   // 设置请求方式

          con.setRequestProperty("content-type", "text/xml;charset=UTF-8");

  　　  5，// 手动构造请求体

               String requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "

                         + " xmlns:q0=\"http://service.itcast.cn/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema \" "

                         + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"

                         + "<soapenv:Body><q0:sayHello><arg0>lisi</arg0> <arg1>10</arg1> </q0:sayHello></soapenv:Body></soapenv:Envelope>";

　　　6，通过流的方式将请求体发送出去：

         //获得输出流

         OutputStream out = con.getOutputStream();

         out.write(requestBody.getBytes());

         out.close();

　　  7，服务端返回正常：

　　 int code = con.getResponseCode();

       if(code == 200){//服务端返回正常

         InputStream is = con.getInputStream();

         byte[] b = new byte[1024];

         StringBuffer sb = new StringBuffer();

         int len = 0;

         while((len = is.read(b)) != -1){

             String str = new String(b,0,len,"UTF-8");

             sb.append(str);

        }

        System.out.println(sb.toString());

        is.close();

       }

       con.disconnect();

}


在程序中，使用webservice的方法，总体来说有两种方法。

	1、使用soap的POST请求的方法，发送xml的soap格式的消息，然后获得xml格式的结果，
	然后在做parse，就可以完成相关内容的处理了

Web Service概述 
Web Service的定义 
W3C组织对其的定义如下，它是一个软件系统，为了支持跨网络的机器间相互操作交互而设计。
Web Service服务通常被定义为一组模块化的API，
它们可以通过网络进行调用，来执行远程系统的请求服务。 	

这里我们从一个程序员的视角来观察web service。
在传统的程序编码中，存在这各种的函数方法调用。
通常，我们知道一个程序模块M中的方法A，
向其发出调用请求，并传入A方法需要的参数P，方法A执行完毕后，返回处理结果R。
这种函数或方法调用通常发生在同一台机器上的同一程序语言环境下。
现在的我们需要一种能够在不同计算机间的不同语言编写的应用程序系统中，
通过网络通讯实现函数和方法调用的能力，
而Web service正是应这种需求而诞生的。 

最普遍的一种说法就是，Web Service = SOAP + HTTP + WSDL。
其中，SOAP Simple Object Access Protocol）协议是web service的主体，
它通过HTTP或者SMTP等应用层协议进行通讯，自身使用XML文件来描述程序的函数方法和参数信息，
从而完成不同主机的异构系统间的计算服务处理。这里的WSDL（Web Services Description Language）
web 服务描述语言也是一个XML文档，它通过HTTP向公众发布，公告客户端程序关于某个具体的 Web service服务的URL信息、方法的命名，参数，返回值等。 
下面，我们先来熟悉一下SOAP协议，看看它是如何描述程序中的函数方法、参数及结果对象的。 

SOAP协议简介 

什么是SOAP 
SOAP 指简单对象访问协议，它是一种基于XML的消息通讯格式，用于网络上，不同平台，不同语言的应用程序间的通讯。可自定义，易于扩展。一条 SOAP 消息就是一个普通的 XML 文档，包含下列元素： 
• Envelope 元素，标识XML 文档一条 SOAP 消息 
• Header 元素，包含头部信息的XML标签 
• Body 元素，包含所有的调用和响应的主体信息的标签 
• Fault 元素，错误信息标签。 

以上的元素都在 SOAP的命名空间http://www.w3.org/2001/12/soap-envelope中声明； 
SOAP的语法规则 
• SOAP 消息必须用 XML 来编码 
• SOAP 消息必须使用 SOAP Envelope 命名空间 
• SOAP 消息必须使用 SOAP Encoding 命名空间 
• SOAP 消息不能包含 DTD 引用 
• SOAP 消息不能包含 XML 处理指令 

SOAP 消息的基本结构 



Java代码 
<? xml version="1.0"?>   
<soap:Envelope   
xmlns:soap="http://www.w3.org/2001/12/soap-envelope"  
soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">   
<soap:Header>   
  ...   
  ...   
 </soap:Header>   
 <soap:Body>   
   ...   
   ...   
   <soap:Fault>   
     ...   
     ...   
   </soap:Fault>   
 </soap:Body>   
 </soap:Envelope>  


