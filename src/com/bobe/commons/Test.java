package com.bobe.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import com.sun.rmi.rmid.ExecOptionPermission;

public class Test {
	
	private static ExecutorService es = null;
	
	@org.junit.Test
	public void testThreadPool(){
		
		
		ExecutorService es = Executors.newWorkStealingPool(2);
		
		
		
	    
		
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		Task task = new Task();
		ExecutorService pool = Test.scheduledThreadPool();
		
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		
		//pool.shutdown();
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		
		
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		//pool.shutdown();
	}
	
	public static ExecutorService scheduledThreadPool(){
		
		if(es==null){
			synchronized (Test.class) {
				es = Executors.newScheduledThreadPool(3);
			}
			return es;
		}
	    return null;
	} 
	@org.junit.Test
	public void testMP3() throws IOException, UnsupportedAudioFileException{
		File file = new File("C:/123.wav");
		
		AudioInputStream in = AudioSystem.getAudioInputStream(file);
		AudioPlayer.player.start(in);
		
	}
	

}
