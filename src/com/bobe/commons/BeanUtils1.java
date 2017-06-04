package com.bobe.commons;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.WrapDynaBean;
import org.junit.Test;

import com.mchange.v2.codegen.bean.PropertyBeanGenerator;

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
    
    @Test
    public void testPropertyUtilbs() throws Throwable, InvocationTargetException, NoSuchMethodException{
    	//使用BeanUtils复制对象
    	Teacher t = new Teacher();
    	t.setAge(25);
    	t.setId(1);
    	t.setQq(new int[]{1,2,3,4,5,6,7,8});
    	Teacher teacher =(Teacher)BeanUtils.cloneBean(t);
    	System.out.println(teacher.toString());
    	
    	Map<String, String> map = BeanUtils.describe(t);
    	
    	System.out.println(map.isEmpty());
        //获取数组元素值
    	String property = BeanUtils.getIndexedProperty(t, "qq[3]");
    	System.out.println(property);
    }
    
    @Test
    public void testForMap(){
    	Map<Integer,Integer> map =new HashMap<>();
    	map.put(1, 10);
    	map.put(2, 20);
    	map.put(3, 30);
    	map.put(4, 40);
    	map.put(5, 50);
    	for (Iterator it = map.values().iterator(); it.hasNext();) {
			Integer count = (Integer) it.next();
			System.out.println(count);
		}
    	
    }
    @Test
    public void testBeanUtils() throws Exception{
    	Teacher t = new Teacher();
    	t.setAge(25);
    	t.setId(1);
    	t.setQq(new int[]{1,2,3,4,5,6,7,8});
    	Teacher teacher=new Teacher();
    	try {
			BeanUtils.copyProperties(teacher, t);
			System.out.println(teacher.getQq()[1]);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
    	
    	Object bean = BeanUtils.cloneBean(t);
    	Teacher tt=(Teacher)bean;
    	System.out.println(tt.getId());
    	
        boolean b = PropertyUtils.isWriteable(t, "qq");
        System.out.println(b);
        
        System.out.print(t.getQq()[2]);
    }
    
    @Test
    public void testMethodUtils() throws Throwable, IllegalAccessException, InvocationTargetException{
    	Teacher t= new Teacher();
    	
    	MethodUtils.invokeMethod(t, "setId", new Object[]{12});
    	MethodUtils.invokeMethod(t, "setName", new Object[]{"欧阳飞燕"});
    	
    	System.out.println(t);
    	
    	Class<?> clazz = MethodUtils.getPrimitiveWrapper(t.getClass().getDeclaringClass());
    	System.out.println(clazz);
    }
    
    @Test
    public void testDnyaBean(){
    	Teacher t= new Teacher();
    	t.setId(123);
    	t.setQq(new int[]{1,2,3,4,5,6,7});
    	DynaBean bean =new WrapDynaBean(t);
    	System.out.println(bean.get("qq"));
    	System.out.println(bean.get("id"));
    }
    
    @Test
    public void testConverter(){
    	Teacher t = new Teacher();
    	t.setAddr("北京培黎大学");
    	t.setAge(13);
    	t.setQq(new int[]{1,2,3,4,5,6,7,8,9,1000});
    	String str = ConvertUtils.convert(t.getQq());
    	//System.out.println(t);
    	System.out.println(str);
    	Object object = ConvertUtils.convert(t, String.class);
    	System.out.println(object);
    }
}
