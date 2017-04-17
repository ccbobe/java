package com.bobe.interview;

import org.junit.Test;

public class StringLoop {
	@Test
	public void testLoop(){
		
		String s="01X34XXX890";
		int j=0;
		for(int i=0;i<s.length();i++){
			
			String str = s.substring(i, i+1);
			
			if(str=="X"){
				j++;
			}
			System.out.println(str);
			System.out.println(j);
		}
	}
}
