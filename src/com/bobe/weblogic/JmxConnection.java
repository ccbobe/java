package com.bobe.weblogic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

import org.junit.Test;

public class JmxConnection {
	
	private static JMXConnector connector = null ;  
	  
	private static final ObjectName service = null;
	
	private static MBeanServerConnection connection = null;
	
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		 String protocol = "t3";  
	     Integer portInteger = Integer.valueOf("7001");  
	     int port = portInteger.intValue();  
	     String jndiroot = "/jndi/"; 
	     
	     String mserver = "weblogic.management.mbeanservers.domainruntime";  
	     JMXServiceURL serviceURL = new JMXServiceURL(protocol, "127.0.0.1", port,  
	                jndiroot + mserver);  
	        Hashtable h = new Hashtable();  
	        h.put(Context.SECURITY_PRINCIPAL, "weblogic");  
	        h.put(Context.SECURITY_CREDENTIALS, "weblogic557");  
	        h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,  
	                "weblogic.management.remote");  
	        connector = JMXConnectorFactory.connect(serviceURL, h);  
	        connection = connector.getMBeanServerConnection(); 
	        
	        ObjectName ob = new ObjectName("com.bea:Name=WTCStatisticsRuntimeMBean,ServerRuntime=myserver,Location=myserver,Type=WTCStatisticsRuntime,WTCRuntime=WTCService");
	        Set<ObjectInstance> set = connection.queryMBeans(ob, null);
	        
	        for (ObjectInstance objectInstance : set) {
				System.out.println(objectInstance);
			}
	        
	        
	        System.out.println(ob.getDomain());
	        
	        
	        
	}
	
	
	
	
	
	
	@Test
	public void testJmcExec() throws Throwable{
		String str = null;
		Process process = Runtime.getRuntime().exec(" java -version");
		ProcessBuilder pb = new ProcessBuilder("java -version");
		BufferedReader buff = new BufferedReader(new InputStreamReader(process.getErrorStream()));
		
		while((str = buff.readLine()) != null){
			System.out.println(str+pb);
		}
		
	}

}
