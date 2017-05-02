package com.bobe.system;

import java.util.Map;

import org.junit.Test;

public class TestSystem {
	
	@Test
	public void testSystem(){
		Map<String, String> getenv = System.getenv();
		//System.console();
		System.out.println(getenv.get("OneDrive"));
		System.out.println(getenv.get("Ps*"));
	}
	
	@Test
	public void test1(){
		SecurityManager manager = System.getSecurityManager();
		if(manager==null){
			manager=new SecurityManager();
		}
		
		System.setSecurityManager(manager);
		
		
		System.out.println(manager);
	}
	
	@Test
	public void testSys(){
		System.loadLibrary("rt.jar");
	}
}
 