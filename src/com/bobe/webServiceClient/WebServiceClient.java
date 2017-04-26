package com.bobe.webServiceClient;

import javax.xml.ws.Endpoint;

import com.bobe.webservice.AddImpl;

public class WebServiceClient {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8083/wsmc/", new AddImpl());
	}

}
