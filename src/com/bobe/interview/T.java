package com.bobe.interview;

public class T extends Thread  implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("aaaa");
	}
	
	public static void main(String[] args) {
		 Thread t=new Thread(new T()); 
         t.start(); 
	}

}
