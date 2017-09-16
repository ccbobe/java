package com.bobe.json;

import java.math.BigDecimal;

import javax.json.Json;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.orsoncharts.data.JSONUtils;
import com.orsoncharts.util.json.JSONArray;

public class JsonDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User  user = new User();
		user.setAge(25);
		user.setSex("22");
		user.setUserId("2013Nd12");
		user.setAccount(new BigDecimal(46));
		//将简单对象转换为json字符串
		Object json = JSON.toJSON(user);
		System.out.println(json);
		System.out.println(">>>"+user);
		
	}
	@Test
	public void testJson1(){
		User user = new User();
		user.setAge(12);
		user.setUserName("bobe");
		//将字符串串换为json对象
		String user2 = "'{'sex':22,'userId':2013Nd12,'account':46,'age':25}'";
		
		String user3 = "{'sex':22,'userId':2013Nd12,'account':46,'age':25}";
		
		//Object json = JSON.parse(user3);
		
		//System.out.println(json);
		
		System.out.println(user3);
		
		Object json2 = JSON.toJSON(user2);
		System.out.println(json2);
		
		//Object parse = JSON.parse("{sex:22,userId:seds,account:34,age:23}");
		//System.out.println(parse);
	}
	@Test
	public void testJSON2(){
		
		
	}

}
