package com.bobe.java8;

public class TestSubString {
	public static void main(String[] args) {
		String foo="ABCDE";
		foo.substring(3);
		foo.concat("XYZ");
		System.out.println(foo);//不可变性
				
	}
}
