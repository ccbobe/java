package com.bobe.webservice;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TestService {
	public static void main(String[] args) {
		try {
			//创建url地址
			URL url=new URL("http://localhost:8083/wsmc/?wsdl");
			//创建Qname
			QName qName=new QName("http://webservice.bobe.com/", "AddImplService");
			//创建service
			Service service = Service.create(url,qName);			
			//调用服务
			Add add = service.getPort(Add.class);
			System.out.println(add.add(1.7, 3.0));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
	}
}
