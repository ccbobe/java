## java程序调用本地程序一些方法

### java程序調用本地一些方法API文檔

 执行cmd命令，需要使用Runtime类和Process类，Rumtime类能够使应用程序预期运行环境相连接。Process可用于控制并获取相关信息，提供了进程的输入、输出、等待完成、检查退出状态和销毁的方法。

```java
package com.bobe.naitveapp;

import java.io.File;

public class NativeApp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//调用本地exe程序
		//程序名称
		//file程序绝对路劲,测试write.exe程序
		//测试ok
		File file=new File("c:\\Windows");
		Runtime.getRuntime().exec("write",null,file);
		
	}

}
```

```java
package com.bobe.naitveapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ping {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		process();
	}
	
	public static void process() throws Exception{
		
		Process process = Runtime.getRuntime().exec("ping www.baidu.com");
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));  
		//获取输入信息
		while(true){
			String sb = br.readLine();
			 if(sb==null){
				 break;
			 }
			 System.out.println(sb);
		} 
	
	}

}
/**
* 調用ping 程序測試www.baidu.com 連通性 
* 測試成功
*/
```