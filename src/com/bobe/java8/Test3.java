package com.bobe.java8;

public class Test3 {

	public static void main(String[] args) {
		
		methodA(0);
		System.out.println(j);
	}
	
	
	private static int j=0;
	
	public static boolean methodB(int k){
		j+=k;
		return true;
	}
	public static void methodA(int i){
		boolean b;
		b=i<10|methodB(4);//
		b=i<10||methodB(8);
	}

}
