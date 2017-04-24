package com.bobe.naitveapp;

import java.io.File;

public class NativeApp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//调用本地exe程序
		//程序名称
		//file程序绝对路劲,测试write.exe程序
		//测试ok
		File file=new File("c:\\Windows");
		Runtime.getRuntime().exec("write",null,file);
		
	}

}
