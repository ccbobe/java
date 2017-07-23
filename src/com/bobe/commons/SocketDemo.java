package com.bobe.commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.net.SocketFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import sun.nio.ch.IOUtil;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import weblogic.jndi.Environment;



public class SocketDemo {
	@Test
	public void testSocket() throws SocketException{
		Socket socket = null;
		try {
			socket = new Socket("192.168.31.241",8888);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean keepAlive = socket.isConnected();
		System.out.println(keepAlive);
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testsslSocket() throws NamingException{
		 Environment env = new Environment();
		 env.setProviderURL("t3://localhost:7001");
		 Context ctx = env.getInitialContext();
		 
		 System.out.println(ctx);
		
		ctx.close();
	}
	
	@Test
	public void testsslSocket2() throws Throwable{
		InetAddress address  =InetAddress.getByName("localhost"); 
		System.out.println(address);
		System.out.println(address.getByName("localhost"));
		Socket socket = new Socket();
		socket.setKeepAlive(true);
		System.out.println(socket.isBound());
		
		InetSocketAddress socketAddress = new InetSocketAddress("localhost", 7001);
		Socket socket2 = SocketFactory.getDefault().createSocket("localhost",7001);
		socket2.getInputStream();
		System.out.println(socket2.getInputStream());
		
	}
	@Test
	public void testSSHConn(){
		//创建jsch对象
		JSch jSch = new JSch();
		String passwd = "root";
		String username ="root";
		String ip="192.168.241.133";
		int port=22;
		String cmd = "grep MemTotal /proc/meminfo;/proc/meminfo ";
		try {
            //创建session			
			Session session = jSch.getSession(username, ip, port);
			//授权连接
			session.setPassword(passwd);
			//停止检查
		    session.setConfig("StrictHostKeyChecking","no");
		    
		    session.setTimeout(6000);
		    session.connect();
		    ChannelExec exec = (ChannelExec) session.openChannel("exec");
		    exec.setCommand(cmd);
		    exec.setInputStream(null);
		    exec.setErrStream(System.err);
		    exec.connect();
		    
		    InputStream in = exec.getInputStream();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
		    String str = IOUtils.toString(reader);
		    System.out.println(str);
		    String[] strs = str.split("\\s+");
		    for (int i = 0; i < strs.length; i++) {
				System.out.println(strs[i]);
			}
		   
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	
	@Test
	public void testsshConn2() throws Exception{
		//远程主机信息
		String rmiName = "192.168.241.133";
		String port = "22";
		String username = "root";
		String passwd = "root";
		//创建jsch
		JSch jSch = new JSch();
		Session session = jSch.getSession(username, rmiName, 22);
		session.setPassword(passwd);
		
		//阻止授权检查
		session.setConfig("StrictHostKeyChecking", "no");
		
		session.connect();
		
		ChannelExec exec = (ChannelExec) session.openChannel("exec");

		exec.setInputStream(null);
        String cmd = "cat /proc/meminfo|grep 'MemFree'|awk {'print($2)'}";
        //cat /proc/meminfo|grep 'MemFree'|awk {'print($2)'}:内存剩余量
        //cat /proc/meminfo|grep 'MemFree'|awk {'print($2)'}:内存总量
        //cat /proc/meminfo|grep 'MemUsed'|awk {'print($2)'}:内存使用量
        //cat /proc/meminfo|grep 'Active'|awk {'print($2)'}:Active
        //cat /proc/meminfo|grep 'MemFree'|awk {'print($2)'}
        //cat /proc/meminfo|grep 'MemFree'|awk {'print($2)'}
        //cat /proc/meminfo|grep 'MemFree'|awk {'print($2)'}
        //cat /proc/meminfo|grep 'MemFree'|awk {'print($2)'}
        //cat /proc/meminfo|grep 'MemFree'|awk {'print($2)'}
        exec.setCommand(cmd);
        exec.connect();
        InputStream in = exec.getInputStream();
        
        //IOUtils.toString(in);
        System.out.println(IOUtils.toString(in));
		
		
	}

}
