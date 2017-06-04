package com.bobe.commons;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.PropertyUtils;

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
		
		
	}
}
