package com.bobe.commons;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;

import org.junit.Test;

import weblogic.management.mbeanservers.domainruntime.internal.MBeanServerConnectionManager;
import weblogic.management.mbeanservers.internal.RuntimeServiceImpl;

public class MyConnection {
	private static MBeanServerConnection connection = null;
	private static JMXConnector connector = null;
	private static ObjectName service;
	
	 /*
	   * Initialize connection to the Domain Runtime MBean Server.
	   */
	   @SuppressWarnings("rawtypes")
	public static MBeanServerConnection initConnection(String hostname, String portString,
	      String username, String password) throws IOException,
	      MalformedURLException {
	       String protocol = "t3";
	      Integer portInteger = Integer.valueOf(portString);
	      int port = portInteger.intValue();
	      String jndiroot = "/jndi/";
	      String mserver = "weblogic.management.mbeanservers.domainruntime";
	       JMXServiceURL serviceURL = new JMXServiceURL(protocol, hostname, port,
	      jndiroot + mserver);
	       Hashtable h = new Hashtable();
	      h.put(Context.SECURITY_PRINCIPAL, username);
	      h.put(Context.SECURITY_CREDENTIALS, password);
	      h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
	         "weblogic.management.remote");
	      h.put("jmx.remote.x.request.waiting.timeout", new Long(10000));
	      connector = JMXConnectorFactory.connect(serviceURL, h);
	      connection = connector.getMBeanServerConnection();
	      return connection;
	   }
	   
	   public static void main(String[] args) throws Exception {
		      String hostname = "localhost";
		      String portString = "7001";
		      String username = "admin";
		      String password = "admin95599";
		      MyConnection c= new MyConnection();
		      connection = initConnection(hostname, portString, username, password);
		      String[] domains = connection.getDomains();
		      ObjectName server1 = new ObjectName(
		              "com.bea:Name=JVMRuntime,Type=weblogic.management.runtime.JVMRuntimeMBean");
		     connection.getAttribute(server1, "");
		   
            System.out.println("11");
		      connector.close();
		   }
	   
	   
	   @Test
	   public void testBean() throws Throwable{
		   JMXConnector jmxCon = null;
	        try {
	            JMXServiceURL serviceUrl = 
	                new JMXServiceURL(
	         "service:jmx:iiop://127.0.0.1:7001/jndi/weblogic.management.mbeanservers.runtime");
	            System.out.println("Connecting to: " + serviceUrl);
	            Hashtable env = new Hashtable();
	            env.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES, 
	                         "weblogic.management.remote");
	            env.put(javax.naming.Context.SECURITY_PRINCIPAL, "admin");
	            env.put(javax.naming.Context.SECURITY_CREDENTIALS, "admin95599");
	            jmxCon = JMXConnectorFactory.newJMXConnector(serviceUrl, env);
	            jmxCon.connect();
	            MBeanServerConnection con = jmxCon.getMBeanServerConnection();
	            Set<ObjectName> mbeans = con.queryNames(null, null);
	            for (ObjectName mbeanName : mbeans) {
	              System.out.println(mbeanName);
	            }
	            ObjectName ser = (ObjectName)con.getAttribute(service,
	  	    		  "ServerRuntime");
	           ObjectName on3 = (ObjectName)con.getAttribute(ser, "JDBCServiceRuntime");
	        }
	        finally {
	            if (jmxCon != null)
	                jmxCon.close();
	        }
	   }

}
