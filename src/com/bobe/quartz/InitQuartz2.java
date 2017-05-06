package com.bobe.quartz;

import java.util.Date;

import org.junit.Test;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitQuartz2 {

	public static Logger logger=LoggerFactory.getLogger(InitQuartz2.class);
	
	@Test
	public void testForDemo() throws Exception{
		
		logger.info("创建SchedulerFactory>>>>");
		
		SchedulerFactory factory=new StdSchedulerFactory();
		
		logger.info("通过工厂创建Scheduler>>>>");
		
		Scheduler scheduler=factory.getScheduler();
		
		//job创建在scheduler.start()之前
		
		Date date = DateBuilder.evenMinuteDate(new Date());
		
		JobDetail detail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("job1", "group1")
				.build();
		//创建Trigger对象
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1")
				.startAt(date)
				.build();
		scheduler.scheduleJob(detail, trigger);
		
		scheduler.start();
		logger.info("调度器启动>>>>>");
		
		Thread.sleep(65L*1000L);
		scheduler.shutdown(true);
		logger.info("scheduler>>>>关闭");
	}
	
	@Test
	public void testForDemo3() throws Exception{
		
		logger.info("创建SchedulerFactory>>>>");
		
		SchedulerFactory factory=new StdSchedulerFactory();
		
		logger.info("通过工厂创建Scheduler>>>>");
		
		Scheduler scheduler=factory.getScheduler();
		
		//job创建在scheduler.start()之前
		
		Date date = DateBuilder.evenMinuteDate(new Date());
		
		JobDetail detail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("job1", "group1")
				.build();
		
		//创建Trigger对象
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1")
				.startAt(date)
				.build();
		scheduler.scheduleJob(detail, trigger);
		
		scheduler.start();
		logger.info("调度器启动>>>>>");
		
		Thread.sleep(65L*1000L);
		SchedulerMetaData metaData = scheduler.getMetaData();
		
		logger.info("metaData"+metaData.getSchedulerName());
		
		scheduler.shutdown(true);
		
		logger.info("scheduler>>>>关闭");
	}
	
	@Test
	public void testForDemo4() throws Exception{
		logger.info("创建SchedulerFactory>>>>");
		
		SchedulerFactory factory=new StdSchedulerFactory();
		
		logger.info("通过工厂创建Scheduler>>>>");
		
		Scheduler scheduler=factory.getScheduler();
		
		//job创建在scheduler.start()之前
		
		Date date = DateBuilder.evenSecondDate(new Date());
		
		/*JobDetail detail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("job1", "group1")
				.build();*/
		JobDetail detail = null;
		for(int i=0;i<10;i++){
			detail = JobBuilder.newJob(HelloJob.class)
					.withIdentity("job"+i, "group"+i)
					.build();
		}
		
		//创建Trigger对象
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1")
				.startAt(date)
				.build();
		scheduler.scheduleJob(detail, trigger);
		
		scheduler.start();
		logger.info("调度器启动>>>>>");
		
		Thread.sleep(65L*1000L);
		SchedulerMetaData metaData = scheduler.getMetaData();
		
		logger.info("metaData"+metaData.getSchedulerName());
		
		scheduler.shutdown(true);
		
		logger.info("scheduler>>>>关闭");
		
	}
	
}
