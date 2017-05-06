package com.bobe.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		 System.out.println("Hello, Quartz! - executing its JOB at "+ 
		            new Date() + " by " + context.getTrigger().getCalendarName());
		 System.out.println("hello java world");
	}

}
