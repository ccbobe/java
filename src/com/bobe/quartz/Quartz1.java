package com.bobe.quartz;




import java.util.Date;

import org.junit.Test;
import org.quartz.DateBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quartz1 {
	private static Logger logger = LoggerFactory.getLogger(Quartz1.class);
	@Test
	public void testDemoScheduler() throws SchedulerException {

		// 创建调度器工厂
		SchedulerFactory factory = new StdSchedulerFactory();
		// 从调度器工厂获取调度器
		Scheduler scheduler = factory.getScheduler();

		

		// 创建job
		JobDetail job = JobBuilder.newJob(HelloJob.class)
				.withIdentity("job1", "group1")
				.build();

		// 创建时间
		Date date = DateBuilder.evenMinuteDate(new Date());

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")
				.startAt(date)
				.build();
		
		
		scheduler.scheduleJob(job, trigger);
		
		// 启动调度器
			scheduler.start();
			logger.info("start::::开始");
		 
			 try {
					Thread.sleep(65L * 1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		// 结束
		scheduler.shutdown(true);
		logger.info("scheduler::::结束");
		
		 //scheduler.addJob(job, true);
		//

		// 1.dubbo 2.quartz 3.hessian 4.webservice 5.struts1
		// 6.rmi 7.ejb 8.jfreechart 9.echart 10.ehcat 11.jms

	}
	
	
}
