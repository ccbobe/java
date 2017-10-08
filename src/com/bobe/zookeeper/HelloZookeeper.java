package com.bobe.zookeeper;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.io.IOUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeperMain;
import org.apache.zookeeper.client.ZooKeeperSaslClient;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

public class HelloZookeeper {
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);  
	//zookeeper api 使用
	
	private static final String HOST_URL= "192.168.241.128:2181";
	
	private static final int SESSION_TIME_OUT = 5000 ;
	
	@Test
	public void testZooKeeperConnection() throws Throwable{
		ZooKeeper zk = new ZooKeeper("192.168.241.128:2181", 5000, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				System.out.println();
				if (KeeperState.SyncConnected == event.getState()) {  
                    connectedSemaphore.countDown();  
                }  
				
			}
		});
		 System.out.println(zk.getState());  
		 
		 connectedSemaphore.await();
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testCreatePathInZookeeper() throws Throwable{
		
		ZooKeeper zk = new ZooKeeper("192.168.241.128:2181", 5000, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event);
			}
		});
		String data = "zk_data";
		//在虚拟机上注意防火墙
	/**
	 * CentOS Linux开启和关闭防火墙命令有两种，一种是临时的，重启即复原；另外一种是永久性的，重启不会复原。  
     *  1） 临时生效，重启后复原  
     * 开启： service iptables start  
     * 关闭： service iptables stop  
     * 2） 永久性生效，重启后不会复原  
     * 开启： chkconfig iptables on  
     * 关闭： chkconfig iptables off  
	 */
		//zk.create("/data", data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		//connectedSemaphore.await();
		byte[] bs = zk.getData("/test", false, null);
		
		String pathValue = IOUtils.toString(bs);
		System.out.println(pathValue);
	}
	
	@Test
	public void testDeleteZookeeperData() throws Throwable{
		ZooKeeper zk = new ZooKeeper("192.168.241.128:2181", 5000, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event);
			}
		});
		
		zk.delete("/hello/java",-1);
	}
	
	@Test
	public void testZookeeperAdmin() throws Throwable{
		
		ZooKeeper zk = new ZooKeeper(HOST_URL, SESSION_TIME_OUT, new Watcher() {
			
			@Override
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event);
				try {
					connectedSemaphore.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}); 
		
	}
	
	
	

}
