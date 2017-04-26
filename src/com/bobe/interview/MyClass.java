package com.bobe.interview;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public abstract class MyClass {
	public int constint=5;
	//中科软第一题A
	//第二题:B
	//第三题:C 考察变量作用域
	//i 作用域在if语句块中，所以编译错误
	// 第四题选择:C
	//第五题:AB
	//第六题:A考察运算之间自动转换，将int转换为String，创建一个新对象，为52
	//第七题:选D重定向发生两次请求，request作用域发生在一次响应中,参数丢失
	//第八题:BC
	//第九题:C
	//第十题:
	//cascode;设置操作中的级联策略，这个属性可以选择的值有：all,在所有的操作的情况下均进行级联，none，
	//在所有操作的情况下均不进行级联操作，
	//save-update，在执行更新操作时级联，
	//delete，在执行删除操作时级联，all-delete-orphan,当被关联对象失去关联宿主时，将被删除
	public abstract void method(int a);
	
	public void method1(){
		
		for (int i = 0; i < 3; i++) {
			System.out.println(i);
		}
		//System.out.println(i);
	} 
	
	@Test
	public void testGetMapValues(){
		Map<Integer,String> map = new HashMap<>();
		map.put(1, "one");
		map.put(1, "one");
		map.put(1, "one");
		map.put(1, "one");
		for(int i=0;i<map.size();i++){
			System.out.println(map.get(i));
		}
	}
	
}
