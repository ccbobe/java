package com.bobe.commons;

public class Task implements Runnable{
	public void run() {
		System.out.println("hello"+Thread.currentThread().getName());
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0;i < 100;i ++){
			System.out.println(i);
		}
	}
	

}
