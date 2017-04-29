package com.bobe.hessian;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.oracle.ebp.hessian.HelloService;

public class HessianMainVClient {
	
	public static void main(String[] args) {
		String url = "http://localhost:8080/ebp/hessian";
		System.out.println(url);
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			HelloService service = (HelloService)factory.create(HelloService.class, url);
			System.out.println(service.helloWorld("tomcc"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

}
