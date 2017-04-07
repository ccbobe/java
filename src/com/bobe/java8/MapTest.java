package com.bobe.java8;

import java.util.concurrent.ConcurrentHashMap;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class MapTest {
	private static final java.util.Map<Integer, String> map=new ConcurrentHashMap<Integer, String>();
	
	public static void main(String[] args) {
		
		new Thread(){
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					map.put(i, ""+i);
					System.out.println("++++++:   " +map.get(i));
				}
			};
		}.start();
		
		new Thread(){
			@Override
			public void run() {
			/*	try {
					//Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				for (int i = 10; i < 20; i++) {
					map.put(i, i+"");
					System.out.println("_______:   " +map.get(i));
				}
			};
		}.start();
		for(int i=0;i<map.size();i++){
			System.out.println(map.get(i));
		}
	}
	
	
	
}
