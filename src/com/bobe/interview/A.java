package com.bobe.interview;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class A {
	public A() {
		
	}
	
	public int method(int a,int b){
		return 0;
	}
	
	//答案选：C
	/*
	protected int method(int a,int b){
		return 0;
	}
	*/
	public int method(int a,long b){
		return 0;
	}
	
	@Test
	public void testGetMapValues(){
		Map<Integer,String> map = new HashMap<>();
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "one");
		for(int i=1;i<=map.size();i++){
			System.out.println(map.get(i));
		}
	}
	
	
	
}
