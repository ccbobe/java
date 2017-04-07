package com.bobe.java8;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class Lambda {
	@Test
	public void test(){
		
		//查找字符串
		String str1="abcdsfg";
		String str2="ds";
		int index = str1.indexOf(str2);
		String sub1 = str1.substring(0,index);
		String sub2 = str1.substring(index+str2.length(), str1.length());
		String str3=sub1+sub2;
		System.out.println(str3);
	}
	
	
	public void subStr(String str1,String str2){
		
		
	}
	
	  private static void testMethod(){
	        System.out.println("testMethod");
	   }
	   public static void main(String[] args) {
	        ((Lambda)null).testMethod();
	        HashMap<Integer, String> map=new HashMap<>(10);
	        map.put(1, "one");
	        map.put(2, "two");
	        map.put(3, "three");
	        map.put(4, "four");
	        map.put(5, "five");
	        Object[] array =map.values().toArray();
	       for(int i=0;i<array.length;i++){
	    	   System.out.println(array[i]);
	       }
	       
	       boolean b = map.containsKey(new Integer(3));
	       boolean b1 = map.containsKey(new Integer(3));
	       System.out.println(b);
	       
	       ConcurrentHashMap<Integer, String> hashMap=new ConcurrentHashMap<>();
	       hashMap.put(1, "111");
	       hashMap.put(2, "222");
	       hashMap.put(3, "333");
	       hashMap.put(4, "444");
	       hashMap.put(5, "555");
	       
	       boolean c = hashMap.contains("111");
	       
	       System.out.println("++++++++++++++++++++++++");
	       System.out.println(c);
	   }
	   
	   
	
}
