package com.bobe.java8;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalAgeByIdCardNumber {
	 
	public static void main(String[] args) {
		calcAgeByIDNumber("642223199110281614");
	}
	
	public static void calcAgeByIDNumber(String idNumber){
		//6422231991110281614
		//获取生日年份
		String age = idNumber.substring(6, 14);
		//计算今年和生日之间转换
		
		Date d=new Date();   
		   SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
		   
		   
		System.out.println(age);
		System.out.println(df.format(d));
		
		System.out.println();
	}
	
}
