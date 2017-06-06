package com.bobe.commons;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.junit.Test;

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
    
}
