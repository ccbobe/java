package com.bobe.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob2 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
    	// TODO Auto-generated method stub
    	System.out.println("我是job");
    }
}
