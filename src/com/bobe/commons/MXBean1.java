package com.bobe.commons;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerFactory;
import javax.management.MBeanServerNotification;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.Notification;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.management.modelmbean.ModelMBeanInfo;
import javax.management.modelmbean.RequiredModelMBean;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.junit.Test;

import com.mchange.v2.management.ManagementUtils;
import com.sun.corba.se.spi.activation.Server;

import sun.management.ManagementFactoryHelper;
import sun.management.snmp.jvmmib.JvmRuntimeMBean;
import sun.management.snmp.util.JvmContextFactory;
import weblogic.health.HealthState;
import weblogic.j2ee.descriptor.wl.ConnectionPoolParamsBeanDConfigBeanInfo;
import weblogic.jdbc.rmi.internal.Connection;
import weblogic.management.MBeanHome;
import weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean;
import weblogic.management.mbeanservers.domainruntime.internal.DomainRuntimeServiceMBeanImpl;
import weblogic.management.mbeanservers.internal.MBeanTypeServiceImplBeanInfo;
import weblogic.management.runtime.AppRuntimeStateRuntimeMBean;
import weblogic.management.runtime.DomainRuntimeMBean;
import weblogic.t3.srvr.ServerRuntime;

@SuppressWarnings("deprecation")
public class MXBean1 {
    @Test
	public void testJvmRunTimeMBean(){
        //获取运行时信息
    	RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        List<String> list = runtimeMXBean.getInputArguments();
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String str = (String) iterator.next();
			System.out.println(str);
		}
        
	}
    
    
    @SuppressWarnings("unchecked")
	@Test
	public void testwlRunTimeMBean() throws IOException, InstanceNotFoundException, IntrospectionException, ReflectionException, Exception, Throwable, Throwable, MBeanException{
        //连接信息     
    	JMXConnector connector = null;
    	MBeanServerConnection connection = null;
        ObjectName name = null;
        String protocol = "t3";  
        Integer portInteger = 7001; 
        int port = portInteger.intValue();  
        String jndiroot = "/jndi/";  
        String mserver = "weblogic.management.mbeanservers.domainruntime";  
        JMXServiceURL serviceURL = new JMXServiceURL(protocol, "localhost", port,  
                jndiroot + mserver);  
        Hashtable<String, String> h = new Hashtable<String, String>();  
        h.put(Context.SECURITY_PRINCIPAL, "admin");  
        h.put(Context.SECURITY_CREDENTIALS, "admin95599");  
        h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,  
                "weblogic.management.remote");  
        connector = JMXConnectorFactory.connect(serviceURL, h);  
        connection = connector.getMBeanServerConnection(); 
        String domain = connection.getDefaultDomain();
        System.out.println(domain);
        connector.close();
       
	}
    @Test
    public void testBean() throws Throwable{
    	MBeanServerConnection connection = null;
    	JMXConnector connector = null;
    	ObjectName service = null;
    	service = new ObjectName(
    	           "com.bea:Name=EditService,Type=weblogic.management.mbeanservers.edit.EditServiceMBean");
    	String protocol = "t3";
        Integer portInteger = Integer.valueOf("7001");
        int port = portInteger.intValue();
        String jndiroot = "/jndi/";
        String mserver = "weblogic.management.mbeanservers.edit";
         JMXServiceURL serviceURL = new JMXServiceURL(protocol, "localhost", port,
        jndiroot + mserver);
         
         Hashtable<String, String> h = new Hashtable<String, String>();
         h.put(Context.SECURITY_PRINCIPAL, "admin");
         h.put(Context.SECURITY_CREDENTIALS, "admin95599");
         h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
            "weblogic.management.remote");
            connector = JMXConnectorFactory.connect(serviceURL, h);
            connection = connector.getMBeanServerConnection();
            MBeanInfo beanInfo = connection.getMBeanInfo(service);
            MBeanAttributeInfo[] attributes = beanInfo.getAttributes();
            for(int i = 0;i<attributes.length;i++){
            	System.out.println(attributes[i].isReadable());
			attributes[i].getDescriptor();
		}
		connector.close();
	}

	@Test
	public void testJVMRunTime() {// 监听被注册和取消注册的 MBean
		Notification notification = null;
		if (notification instanceof MBeanServerNotification) {
			MBeanServerNotification msnotification = (MBeanServerNotification) notification;

			// 获取 MBeanServerNotification 的值
			// 类型特性，包括
			// “JMX.mbean.registered”或“JMX.mbean.unregistered”
			String nType = msnotification.getType();
			// 获取已经注册或未注册的 MBean 的对象名
			ObjectName name = msnotification.getMBeanName();
			String key = name.getKeyProperty("Type");
			if (nType.equals("JMX.mbean.registered")) {
				System.out.println("A " + key + " has been created.");
				System.out.println("Full MBean name: " + name);
				System.out.println("Time: " + msnotification.getTimeStamp());
				if (key.equals("JDBCDataSourceRuntime")) {
					// 注册监听器到 ServerRuntimeMBean。
					// 通过定义“ListenToDelegate”类中的“registerwithServerRuntime”方法，

				}

			}
		}
	}
	@Test
	public void testMBServer() throws Throwable{
		//创建MBeanServer
		MBeanServer mBeanServer = MBeanServerFactory.createMBeanServer(); 
		RequiredModelMBean serverMBean = 
		(RequiredModelMBean) mBeanServer.instantiate( "javax.management.modelmbean.RequiredModelMBean"); 
		MBeanNotificationInfo[] info = serverMBean.getNotificationInfo();
	    
		
		for (int i = 0; i < info.length; i++) {
			System.out.println(info[i].getDescriptor());
		}
		serverMBean.sendNotification("hello");
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testJVM() throws Throwable{
		
		
	}
	@Test
	public  void initConnection() throws IOException,
	          MalformedURLException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException, MalformedObjectNameException {
		MBeanServerConnection connection = null;
    	JMXConnector connector = null;
    	ObjectName service = new ObjectName("com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");
	          String protocol = "t3";
	          Integer portInteger = Integer.valueOf("7001");
	          int port = portInteger.intValue();
	          String jndiroot = "/jndi/";
	          String mserver = "weblogic.management.mbeanservers.runtime";
	          JMXServiceURL serviceURL = new JMXServiceURL(protocol, "127.0.0.1",
	             port, jndiroot + mserver);
	          Hashtable h = new Hashtable();
	          h.put(Context.SECURITY_PRINCIPAL, "admin");
	          h.put(Context.SECURITY_CREDENTIALS, "admin95599");
	          h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
	             "weblogic.management.remote");
	          connector = JMXConnectorFactory.connect(serviceURL, h);
	          connection = connector.getMBeanServerConnection();
	         ObjectName attribute = (ObjectName) connection.getAttribute(service,
	                  "ServerRuntime");
	         ObjectName objThreadPool = null;
	         ObjectName serverRT = attribute;
	         objThreadPool = (ObjectName) connection.getAttribute(serverRT, "ThreadPoolRuntime");
	         double throughput  = Double.parseDouble(String.valueOf(connection.getAttribute(
	                   objThreadPool,"Throughput")));
	           System.out.println("throughput:" + throughput);
	           
	           int executeThreadTotalCount = Integer.parseInt(String.valueOf(connection.getAttribute(objThreadPool, "ExecuteThreadTotalCount")));
	           System.out.println("executeThreadTotalCount:" + executeThreadTotalCount);
	          
	         
	           int executeThreadIdleCount = Integer.parseInt(String.valueOf(connection.getAttribute(objThreadPool, "ExecuteThreadIdleCount")));
	           System.out.println("executeThreadIdleCount:" + executeThreadIdleCount);
	           
	           int StandbyThreadCount = Integer.parseInt(String.valueOf(connection.getAttribute(objThreadPool, "StandbyThreadCount")));
	           System.out.println("StandbyThreadCount:" + StandbyThreadCount);
	         
	         connector.close();
	       }
	
	@Test
	public void testJMXconnector() throws Throwable{
		MBeanServerConnection connection = null;
    	JMXConnector connector = null;
		Hashtable h = new Hashtable();
        h.put(Context.SECURITY_PRINCIPAL, "admin");
        h.put(Context.SECURITY_CREDENTIALS, "admin95599");
        h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
           "weblogic.management.remote");
        String protocol = "t3";
        Integer portInteger = Integer.valueOf("7001");
        int port = portInteger.intValue();
        String jndiroot = "/jndi/";
        String mserver = "weblogic.management.mbeanservers.runtime";
        JMXServiceURL serviceURL = new JMXServiceURL(protocol, "127.0.0.1",
	             port, jndiroot + mserver);
        connector = JMXConnectorFactory.connect(serviceURL,h);
        connection = connector.getMBeanServerConnection();
        ObjectName on = new ObjectName("com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");
        ObjectName on1 = (ObjectName) connection.getAttribute(on, "ServerRuntime");
        
        ObjectName on2 = (ObjectName) connection.getAttribute(on1, "JVMRuntime");
         System.out.print(on2);  
         String str2 =(String) connection.getAttribute(on2, "OSName");
         System.out.println(str2);
         
         long str3 =(long) connection.getAttribute(on2, "HeapSizeMax");
         System.out.println(str3);
       /*  
         String str4 =(String) connection.getAttribute(on2, "ThreadStackDump");
         System.out.println(str4);
         connector.close();*/
         
         
         connector.close();
         
        
	}
	
	
	@Test
	public void testRuntime() throws IOException, MalformedObjectNameException, Throwable, Throwable, MBeanException, ReflectionException{
		JMXConnector connector = null;
		 String protocol = "t3";
         Integer portInteger = Integer.valueOf("7001");
         int port = portInteger.intValue();
         String jndiroot = "/jndi/";
         String mserver = "weblogic.management.mbeanservers.runtime";

         JMXServiceURL serviceURL = new JMXServiceURL(protocol, "127.0.0.1", port,
         jndiroot + mserver);
         Hashtable h = new Hashtable();
         h.put(Context.SECURITY_PRINCIPAL, "admin");
         h.put(Context.SECURITY_CREDENTIALS, "admin95599");
         h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
            "weblogic.management.remote");
         connector = JMXConnectorFactory.connect(serviceURL,h);
         
         MBeanServerConnection con = connector.getMBeanServerConnection();
         
         ObjectName name = new ObjectName("com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");
         
         String str =  (String)con.getAttribute(name, "Name");
         System.out.println(str);
         
      ObjectName[] on = (ObjectName[]) con.getAttribute(name, "Services");
      for (int i = 0; i < on.length; i++) {
		System.out.println(on[i]);
	}
         connector.close();
	}
	
	
	
	
	@Test
	public void testRuntime2() throws IOException, MalformedObjectNameException, Throwable, Throwable, MBeanException, ReflectionException{
		JMXConnector connector = null;
		 String protocol = "t3";
         Integer portInteger = Integer.valueOf("7001");
         int port = portInteger.intValue();
         String jndiroot = "/jndi/";
         String mserver = "weblogic.management.mbeanservers.runtime";

         JMXServiceURL serviceURL = new JMXServiceURL(protocol, "127.0.0.1", port,
         jndiroot + mserver);
         Hashtable h = new Hashtable();
         h.put(Context.SECURITY_PRINCIPAL, "admin");
         h.put(Context.SECURITY_CREDENTIALS, "admin95599");
         h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
            "weblogic.management.remote");
         connector = JMXConnectorFactory.connect(serviceURL,h);
         
         MBeanServerConnection con = connector.getMBeanServerConnection();
        // ObjectName  on = new ObjectName("");
         
         
         connector.close();
	}
	
	@Test
	public  void initConnection3() throws IOException,
	          MalformedURLException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException, MalformedObjectNameException {
		MBeanServerConnection connection = null;
    	JMXConnector connector = null;
    	ObjectName service = new ObjectName("com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");//类型及其包名加类名
	          String protocol = "t3";
	          Integer portInteger = Integer.valueOf("7001");
	          int port = portInteger.intValue();
	          String jndiroot = "/jndi/";
	          String mserver = "weblogic.management.mbeanservers.runtime";//访问到包名
	          JMXServiceURL serviceURL = new JMXServiceURL(protocol, "127.0.0.1",
	          port, jndiroot + mserver);
	          Hashtable h = new Hashtable();
	          h.put(Context.SECURITY_PRINCIPAL, "admin");
	          h.put(Context.SECURITY_CREDENTIALS, "admin95599");
	          h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
	             "weblogic.management.remote");
	          connector = JMXConnectorFactory.connect(serviceURL, h);
	          connection = connector.getMBeanServerConnection();
              ObjectName on = (ObjectName)connection.getAttribute(service, "ServerRuntime");	        
	          
              String str= (String) connection.getAttribute(on, "Name");//获取服务名称
              
              System.out.println(str);
             
              connector.close();
	       }
	
	@Test
	public  void initConnection5() throws IOException,
	          MalformedURLException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException, MalformedObjectNameException {
		MBeanServerConnection connection = null;
    	JMXConnector connector = null;
    	ObjectName service = 
    new ObjectName("com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");//类型及其包名加类名
	          String protocol = "t3";
	          Integer portInteger = Integer.valueOf("7001");
	          int port = portInteger.intValue();
	          String jndiroot = "/jndi/";
	          String mserver = "weblogic.management.mbeanservers.runtime";//访问到包名
	          JMXServiceURL serviceURL = new JMXServiceURL(protocol, "127.0.0.1",
	          port, jndiroot + mserver);
	          Hashtable h = new Hashtable();
	          h.put(Context.SECURITY_PRINCIPAL, "admin");
	          h.put(Context.SECURITY_CREDENTIALS, "admin95599");
	          h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
	             "weblogic.management.remote");
	          connector = JMXConnectorFactory.connect(serviceURL, h);
	          connection = connector.getMBeanServerConnection();
              
	          ObjectName obn = new ObjectName( "com.bea:Name=myserver,Type=ServerRuntime"); //服务器运行状态
	          String obn1 = (String) connection.getAttribute(obn, "State");
	          
	          System.out.println(obn1);
	          
	          CompositeData cd    =(CompositeData) connection.getAttribute(obn, "OverallHealthStateJMX");
	          System.out.println(cd.get("HealthState"));//服务器运行情况
	          System.out.println(cd.get("ReasonCode"));
	          connector.close();
	       }
	
	
	@Test
	public  void initConnection4() throws IOException,
	          MalformedURLException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException, MalformedObjectNameException {
		MBeanServerConnection connection = null;
    	JMXConnector connector = null;
    	ObjectName service = 
    new ObjectName("com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");//类型及其包名加类名
	          String protocol = "t3";
	          Integer portInteger = Integer.valueOf("7001");
	          int port = portInteger.intValue();
	          String jndiroot = "/jndi/";
	          String mserver = "weblogic.management.mbeanservers.runtime";//访问到包名
	          JMXServiceURL serviceURL = new JMXServiceURL(protocol, "127.0.0.1",
	          port, jndiroot + mserver);
	          Hashtable h = new Hashtable();
	          h.put(Context.SECURITY_PRINCIPAL, "admin");
	          h.put(Context.SECURITY_CREDENTIALS, "admin95599");
	          h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
	             "weblogic.management.remote");
	          connector = JMXConnectorFactory.connect(serviceURL, h);
	          connection = connector.getMBeanServerConnection();
              
	          ObjectName obn = new ObjectName( "com.bea:ServerRuntime=myserver,Name=myserver,Type=JVMRuntime"); //服务器运行状态
	         
	          
	          long name = (long) connection.getAttribute(obn, "HeapSizeMax");//堆内存最大值
	          System.out.println(obn.getCanonicalName());//服务器运行情况
	          System.out.println(name);
	          
	          
	          ObjectName obn2 = new ObjectName( "com.bea:ServerRuntime=myserver,Name=myserver,Type=JDBCServiceRuntime"); //服务器运行状态
	          HealthState str2 = (HealthState) connection.getAttribute(obn2, "HealthState");   
	          System.out.println(str2.getState());
	        
	          connector.close();
	       }
	
	
	
	
}
    
    

