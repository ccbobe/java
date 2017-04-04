package com.bobe.java8;

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
	
}
