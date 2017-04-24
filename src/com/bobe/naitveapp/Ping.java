package com.bobe.naitveapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.junit.Test;

public class Ping {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		process();
	}
	
	public static void process() throws Exception{
		
		Process process = Runtime.getRuntime().exec("ping www.baidu.com");
		BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));  
		//获取输入信息
		while(true){
			String sb = br.readLine();
			 if(sb==null){
				 break;
			 }
			 System.out.println(sb);
		} 
	
	}
	
	@Test
	public void testCal() throws IOException{
		Process exec = Runtime.getRuntime().exec("cmd /c start C:\\WINDOWS\\system32\\calc");
		
		
	}
	
	@Test
	public void testPrint() throws Exception{
		Runtime.getRuntime().exec("D:\\Program Files (x86)\\Notepad++\\notePad++");
		
	}

}
