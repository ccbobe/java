package com.bobe.commons;

import java.beans.PropertyDescriptor;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.FileDataSource;
import javax.net.ssl.ManagerFactoryParameters;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import sun.management.HotspotMemoryMBean;
import sun.management.HotspotRuntimeMBean;
import sun.management.ManagementFactoryHelper;
import sun.management.snmp.jvmmib.JvmRuntimeMBean;

import com.sun.corba.se.impl.ior.ByteBuffer;

public class BeanUtils1 {
    public static void main(String[] args) throws Exception {
    	/**
    	 * @author bobe
    	 * 通过BeanUtils反射javaBean
    	 */
    	Teacher t = new Teacher();
    	t.setId(1);
    	t.setMajor("英语");
    	t.setQq(new int[]{1,2,3,4,5,6,7,12});
    	List<String> list=new LinkedList<>();
    	list.add("erguo");
    	list.add("bobe");
    	list.add("coce");
    	t.setBaby(list);
    	
    	
		Integer teacher =(Integer) PropertyUtils.getSimpleProperty(t, "id");
		System.out.println(teacher);
		String major = (String) PropertyUtils.getSimpleProperty(t, "major");
		System.out.println(major);
		int[] qq = (int[]) PropertyUtils.getSimpleProperty(t, "qq");
		for (int i : qq) {
			System.out.println(i);
		}
		
		
		@SuppressWarnings("unchecked")
		List<String> l=(List<String>)PropertyUtils.getSimpleProperty(t, "baby");
		for (String s : l) {
			System.out.println(s);
		}
	
		//设置属性值，通过setProperty()方法设置
		PropertyUtils.setProperty(t, "qq", new int[]{1,2,4,7});
		System.out.println(t.getQq());
		for(int i=0;i<t.getQq().length;i++){
			System.out.println(t.getQq()[i]);
		}
		
		int[] num = (int[])PropertyUtils.getProperty(t, "qq");
		System.out.println("qq[1]=====>"+num);
		double i=0;
		
			for( i=0;i<10.0;i++){
			     try {
				     System.out.println(10/i);
				     if(i==2){
					     throw new RuntimeException("hello");
				     }
			      } catch (Exception e) {
				       // TODO Auto-generated catch block
				       e.printStackTrace();
			      }
			}
	}
    @Test
    public void testBeanUtils() throws Throwable{
    	Teacher t1 = new Teacher();
    	t1.setAddr("java");
        t1.setId(123);
    	Teacher t2 = new Teacher();
    	BeanUtils.copyProperties(t2, t1);
        Map<String, String> map = BeanUtils.describe(t1);
        map.get(t2);
        System.out.println(map.get(t2));
    }
    @Test
    public void testConvertUtils() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
    	Converter converter=new MyConvert();
    	ConvertUtils.register(converter, String.class);
    	Teacher t=new Teacher();
    	t.setAddr("world");
    	String str = BeanUtils.getSimpleProperty(t, "addr");
    	System.out.println(str);
		
    }
    @Test
    public void testCollections(){
    	OrderedMap map =new LinkedMap();
    	map.put(new Integer(1), "壹");
    	map.put(new Integer(2), "贰");
    	map.put(new Integer(3), "叁");
    	map.put(new Integer(4), "肆");
    	
    	Iterator it = map.entrySet().iterator();
    	while(it.hasNext()){
    		System.out.println(it.next());
    	}
    }
    
    @Test
    public  void testCommonsIO(){
    	try {//将指定文件移动到指定位置
			FileUtils.moveFile(new File("c:\\tmp\\input.txt"), new File("c:\\tmp\\output.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    @Test
    public void testCommonsIO1(){
    	Collection<String> lines = new HashSet<>();
    	lines.add("hello world");
    	lines.add("php");
    	lines.add("go start 支持并发");
    	try {
			FileUtils.writeLines(new File("C:\\tmp\\1.txt"), lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    @Test
    public void testFileUtils1() throws IOException, IOException{
    	//将url地址文件复制在指定文件中
    	FileUtils.copyURLToFile(new URL("https://www.baidu.com"), new File("C:\\tmp\\baidu.html"));
    }
    @Test
    public void testFileUtils2() throws IOException{
    	FileInputStream input = FileUtils.openInputStream(new File("C:\\tmp\\1.txt"));
        FileUtils.copyInputStreamToFile(input, new File("c:\\tmp\\2.txt"));
    }
    
    @Test
    public void testFileUtils3() throws IOException{
    	//将文件追加在已有文件的后面
    	FileUtils.writeStringToFile(new File("c:\\tmp\\2.txt"),  new String("我在学习java commons io"), "UTF-8", true);
    }
    
    @Test
    public void testPipedIO() throws IOException{
    	final PipedInputStream input = new PipedInputStream();
    	final PipedOutputStream output = new PipedOutputStream();
    	output.connect(input);
    	new Thread(){
    		@SuppressWarnings("deprecation")
			public void run() {
    			byte[] buf = new byte[1024];
    			try {
				     buf = IOUtils.toByteArray("hello world");
				     output.write(buf, MIN_PRIORITY, MIN_PRIORITY);
				     output.flush();
				     Thread.sleep(1);
    			     output.close();
    			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		};
    	}.start();
    	
    	new Thread(){
    		
    		public void run() {
    			try {
					int read = input.read();
					Thread.sleep(6000);
					//input.close();
					System.out.println(read+"j");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    		};
    		
    		
    	}.start();
    	
    }
    @Test
    public void testIOUtils(){
    	String date = DateUtil.formatDate(new Date());
    	System.out.println(date);
    	String date2 = DateUtil.formatDate(new Date(),DateUtil.PATTERN_ASCTIME);
    	System.out.println(date2);
    }
    
    @Test
    public void testCommons() throws IOException{
         FileInputStream stream = new FileInputStream(new File("D:\\1.txt")); 
    	 FileChannel fChannel = stream.getChannel();
    	 System.out.println("------");
    	 java.nio.ByteBuffer buf = java.nio.ByteBuffer.allocate(1024);
    	 fChannel.read(buf);//将流读入Buffer
    	 int i = fChannel.read(buf);
         while(i!= -1){
        	 buf.flip();
        	 while(buf.hasRemaining()){
        		 System.out.print((char)buf.get());
        	 }
        	// buf.clear(); 
        	// i = fChannel.read(buf);
         }
         stream.close();
    }
    
    @Test
    public void testNIO() throws IOException{
    	Process exec = Runtime.getRuntime().exec("jconsole");
    	InputStream in = exec.getInputStream();
    	File file = new File("c:\\tmp\\out.txt");
    	FileUtils.copyInputStreamToFile(in, file);
    }
    @Test
    public void testJVM1() throws IOException{
    	Map<String, String> map = System.getenv();
    	for (Iterator iterator =map.values().iterator(); iterator.hasNext();) {
			String type = (String) iterator.next();
			System.out.println(type);
		}
    }
    
    @Test
    public void testJVM2() throws IOException{
    	Properties props = System.getProperties();
    	System.out.println(props);
    	
    	String jvmName = System.getProperty("java.vm.name");
    	System.out.println(jvmName);
    }
    
    @Test
    public void testJVM3() throws IOException{
    	MemoryMXBean memorybean = ManagementFactory.getMemoryMXBean();
    	MemoryUsage memoryUsage = memorybean.getHeapMemoryUsage();
    	
    	System.out.println(memoryUsage.getMax());
    	
    	System.out.println(memoryUsage.getMax()/(1024));
    	
    	System.out.println(memoryUsage.getInit()/(1024*1024.0));
    	
    	RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
    	System.out.println(mxBean.getSystemProperties());
    	
    	ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    	System.out.println(threadMXBean.getTotalStartedThreadCount());
    }
    
    @Test
    public void testJVM4(){
    	ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    	System.out.println(threadMXBean.getDaemonThreadCount());
    	ClassLoadingMXBean loadingMXBean = ManagementFactory.getClassLoadingMXBean();
    	System.out.println(loadingMXBean.getUnloadedClassCount());
    }
    
    @Test
    public void testJVM5(){
    	HotspotRuntimeMBean mBean = ManagementFactoryHelper.getHotspotRuntimeMBean();	
    	System.out.println(mBean.getInternalRuntimeCounters());		
    }
    
}